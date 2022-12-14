package com.sgw.common.configuration;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import feign.Logger;
import feign.Request;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author sunnys
 */
@Configuration
//@EnableConfigurationProperties(DemoProperties.class)
public class NetworkConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule rule() {
        return new NacosRule();
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.BASIC;
    }

    //@Bean
    //public BasicAuthRequestInterceptor authRequestInterceptor(DemoProperties demoProperties) {
    //    return new BasicAuthRequestInterceptor(demoProperties.getUserName(), demoProperties.getPassword());
    //}

    @Bean
    public Request.Options feignOptions() {
        return new Request.Options(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, false);
    }

}
