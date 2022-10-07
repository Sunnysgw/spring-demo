package com.sgw.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunny
 */
@Configuration
public class RedissonConfiguration {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("master1:6379", "master2:6379", "master3:6379");
        return Redisson.create(config);
    }

}
