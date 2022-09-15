package com.sgw.common.configuration;

import com.sgw.common.CuratorBaseOperation;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: zk配置类
 * @author: sunnysgw
 * @since: 1.0
 **/
@Configuration
public class ZookeeperConfiguration {

    @Bean
    public CuratorFramework curatorFramework() {
        return CuratorBaseOperation.curatorFramework();
    }

}
