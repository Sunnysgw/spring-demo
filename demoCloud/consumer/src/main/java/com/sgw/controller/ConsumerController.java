package com.sgw.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sgw.api.UserInfoControllerClient;
import com.sgw.common.configuration.SentinelConfiguration;
import com.sgw.common.constants.SentinelConstants;
import com.sgw.common.properties.DemoProperties;
import com.sgw.provider.entity.UserInfo;
import com.sgw.service.IWorkerService;
import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author sunnys
 */
@RestController
@RequestMapping("userInfo")
@RefreshScope
@Log
public class ConsumerController {
    @Value("${slow.flag:false}")
    private Boolean slowFlag;

    private UserInfoControllerClient userInfoControllerClient;

    private DemoProperties demoProperties;

    private IWorkerService workerService;

    public ConsumerController(DemoProperties demoProperties,
                              UserInfoControllerClient userInfoControllerClient,
                              IWorkerService workerService) {
        this.demoProperties = demoProperties;
        this.userInfoControllerClient = userInfoControllerClient;
        this.workerService = workerService;
    }

    @GetMapping(value = "{id}")
    @SentinelResource(value = SentinelConstants.QUERY_RESOURCE, blockHandler = "blockHandler")
    public ResponseEntity<UserInfo> queryById(@PathVariable("id") Integer id) {
        workerService.work();
        if (slowFlag) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignore) {
            }
        }
        return ResponseEntity.ok(userInfoControllerClient.queryById(id));
    }

    @GetMapping("refresh")
    public String getProperties() {
        workerService.work();
        return demoProperties.toString();
    }

    public ResponseEntity<UserInfo> blockHandler(Integer id) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS)
                .body(new UserInfo());
    }

}
