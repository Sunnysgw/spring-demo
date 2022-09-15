package com.sgw;

import com.sgw.common.properties.DemoProperties;
import com.sgw.provider.api.UserInfoControllerAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class App 
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        run.getBean(DemoProperties.class);
        System.out.println("hello");
    }
}
