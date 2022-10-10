package com.sgw.controller;

import com.sgw.common.lock.RedisLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.ref.PhantomReference;

/**
 * redis中分布式锁的一些尝试
 * @author sunny
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    private static final String KEY = "concurrent_key";

    private static final String REDISSON_LOCK_KEY = "redisson_lock_key";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisLock redisLock;

    @Resource
    private RedissonClient redissonClient;

    @PutMapping("decr")
    public ResponseEntity<String> decrease() {
        redisLock.lock();
        try {
            int value = Integer.parseInt(stringRedisTemplate.opsForValue().get(KEY));
            if (value > 0) {
                Long decrement = stringRedisTemplate.opsForValue().decrement(KEY);
                return ResponseEntity.ok("result is" + decrement);
            }
            return ResponseEntity.ok("decrease failed");
        } finally {
            redisLock.unLock();
        }
    }
}
