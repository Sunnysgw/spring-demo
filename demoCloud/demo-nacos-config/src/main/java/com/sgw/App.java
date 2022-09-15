package com.sgw;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", "127.0.0.1:8848");
        properties.setProperty("username", "nacos");
        properties.setProperty("password", "nacos");
        NacosConfigService nacosConfigService = new NacosConfigService(properties);
        String config = nacosConfigService.getConfig("consumer", "DEFAULT_GROUP", 1000);
        System.out.println(config);
    }
}
