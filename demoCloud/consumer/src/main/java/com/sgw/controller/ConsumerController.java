package com.sgw.controller;

import com.sgw.api.UserInfoControllerClient;
import com.sgw.common.properties.DemoProperties;
import com.sgw.provider.entity.UserInfo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunnys
 */
@RestController
@RequestMapping("userInfo")
@Log
public class ConsumerController {

    @Autowired
    private UserInfoControllerClient userInfoControllerClient;

    DemoProperties demoProperties;

    public ConsumerController(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserInfo> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userInfoControllerClient.queryById(id));
    }

    @GetMapping("refresh")
    public String getProperties() {
        return demoProperties.toString();
    }

}
