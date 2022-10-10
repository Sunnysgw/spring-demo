package com.sgw.service.impl;

import com.alibaba.fastjson.JSON;
import com.sgw.common.annotions.DS;
import com.sgw.common.constants.DatasourceConstants;
import com.sgw.entity.Role;
import com.sgw.dao.RoleDao;
import com.sgw.service.RoleService;
import lombok.extern.java.Log;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2022-09-29 09:36:18
 */
@Log
@Service("roleService")
public class RoleServiceImpl implements RoleService, InitializingBean {

    private static final String ROLE_KEY = "ROLE_KEY";

    private static final String ROLE_CREATE_CACHE_KEY = "ROLE_CREATE_CACHE_KEY";

    private static final String ROLE_UPDATE_CACHE_KEY = "ROLE_UPDATE_CACHE_KEY";

    private static final Integer MAX_WAIT_CREATE_CACHE_LOCK_TIME_MILLION_SECOND = 3000;

    private static final Integer ROLE_MAX_CACHE_TIME_HOUR = 1;

    @Resource
    private RoleDao roleDao;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private RReadWriteLock roleUpdateLock;

    private RLock roleCreateLock;

    @Override
    public void afterPropertiesSet() throws Exception {
        roleUpdateLock = redissonClient.getReadWriteLock(ROLE_UPDATE_CACHE_KEY);
        roleCreateLock = redissonClient.getLock(ROLE_CREATE_CACHE_KEY);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Transactional
    @Override
    @DS
    public Role queryById(Integer id) {
        String cache;
        if ((cache = stringRedisTemplate.opsForValue().get(ROLE_KEY + id)) != null) {
            return JSON.parseObject(cache, Role.class);
        }
        // 二次检查缓存前加锁，并行转串行
        // 超过一定时间还没拿到锁，并行转串行
        try {
            boolean getLock = roleCreateLock.tryLock(MAX_WAIT_CREATE_CACHE_LOCK_TIME_MILLION_SECOND, TimeUnit.MILLISECONDS);
            if (!getLock) {
                log.warning("尝试获取缓存创建锁过期");
            }
        } catch (InterruptedException ignore) {
        }
        Role role;
        try {
            if ((cache = stringRedisTemplate.opsForValue().get(ROLE_KEY + id)) != null) {
                return JSON.parseObject(cache, Role.class);
            }

            roleUpdateLock.writeLock().lock();
            try {
                role = roleDao.queryById(id);
                if (role == null) {
                    // 数据库中查不到内容，返回空对象，之后写入缓存
                    role = new Role();
                }
                cache = JSON.toJSONString(role);
                stringRedisTemplate.opsForValue().set(ROLE_KEY + id, cache);
                stringRedisTemplate.expire(ROLE_KEY + id, ROLE_MAX_CACHE_TIME_HOUR, TimeUnit.HOURS);
            } finally {
                roleUpdateLock.writeLock().unlock();
            }
        } finally {
            roleCreateLock.unlock();
        }
        return role;
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    @DS
    public Role insert(Role role) {
        roleUpdateLock.writeLock().lock();
        try {
            this.roleDao.insert(role);
            // todo 这里有问题，建表的时候id是自增主键，所以不会插id，应该选择表中的唯一索引做key
            stringRedisTemplate.opsForValue().set(ROLE_KEY + role.getId(),
                    JSON.toJSONString(role));
        } finally {
            roleUpdateLock.writeLock().unlock();
        }
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        roleUpdateLock.writeLock().lock();
        try {
            this.roleDao.update(role);
            stringRedisTemplate.opsForValue().set(ROLE_KEY + role.getId(),
                    JSON.toJSONString(role));
        } finally {
            roleUpdateLock.writeLock().unlock();
        }
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        roleUpdateLock.writeLock().lock();
        int delCount = 0;
        try {
            delCount = roleDao.deleteById(id);
            stringRedisTemplate.delete(ROLE_KEY + id);
        } finally {
            roleUpdateLock.writeLock().unlock();
        }
        return delCount > 0;
    }
}
