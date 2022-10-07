package com.sgw.common.lock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author sunny
 */
@Component
public class RedisLock implements Lock{

    private final static String LOCK_KEY = "lock_key";

    private final static Long MAX_KEEP_TIME = 10L;

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private DefaultListableBeanFactory beanFactory;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void lock() {
        for (;;) {
            if (Boolean.TRUE.equals(stringRedisTemplate.opsForValue()
                    .setIfAbsent(LOCK_KEY, applicationName + beanFactory.getSerializationId(),
                            MAX_KEEP_TIME, TimeUnit.SECONDS))) {
                return;
            }
        }
    }

    @Override
    public boolean tryLock(TimeUnit timeUnit, Long time) {
        return false;
    }


    @Override
    public void unLock() {
        stringRedisTemplate.delete(LOCK_KEY);
    }

}
