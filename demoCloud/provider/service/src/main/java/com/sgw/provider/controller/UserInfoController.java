package com.sgw.provider.controller;

import com.sgw.provider.api.UserInfoControllerAPI;
import com.sgw.provider.entity.UserInfo;
import com.sgw.provider.service.UserInfoService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * (UserInfo)表控制层
 *
 * @author makejava
 * @since 2022-09-05 15:02:24
 */
@RestController
@RequestMapping("userInfo")
public class UserInfoController implements UserInfoControllerAPI {
    /**
     * 服务对象
     */
    @Resource
    private UserInfoService userInfoService;

    @Value("${}")

    private static final Integer ID = new Random().nextInt(100);

    /**
     * 分页查询
     *
     * @param userInfo 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    //@Override
    //public ResponseEntity<Page<UserInfo>> queryByPage(UserInfo userInfo, PageRequest pageRequest) {
    //    return ResponseEntity.ok(this.userInfoService.queryByPage(userInfo, pageRequest));
    //}

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Override
    public UserInfo queryById(@PathVariable("id") Integer id) {
        UserInfo userInfo = this.userInfoService.queryById(id);
        userInfo.setAge(ID);
        return userInfo;
    }

    /**
     * 新增数据
     *
     * @param userInfo 实体
     * @return 新增结果
     */
    @Override
    public ResponseEntity<UserInfo> add(UserInfo userInfo) {
        return ResponseEntity.ok(this.userInfoService.insert(userInfo));
    }

    /**
     * 编辑数据
     *
     * @param userInfo 实体
     * @return 编辑结果
     */
    @Override
    public ResponseEntity<UserInfo> edit(UserInfo userInfo) {
        return ResponseEntity.ok(this.userInfoService.update(userInfo));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Override
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.userInfoService.deleteById(id));
    }

}

