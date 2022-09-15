package com.sgw.common.properties;

import lombok.Data;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
@ConfigurationProperties(prefix = "demo")
@Component
@RefreshScope
@Data
@ToString
@Log
public class DemoProperties {

    private String userName;

    private String password;

    @PostConstruct
    public void init() {
        log.info("init");
    }

    @Scheduled(cron = "*/3 * * * * ?")
    public void execute() {
        log.info("定时任务正常运行");
    }

    //@Override
    //public void onApplicationEvent(RefreshEvent event) {
    //    //this.execute();
    //}
}
