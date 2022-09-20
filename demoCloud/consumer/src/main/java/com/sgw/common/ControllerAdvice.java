package com.sgw.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<String> handleThrowable(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage());
    }

    @ExceptionHandler({BlockException.class})
    public ResponseEntity<String> handleBlockException(BlockException blockException) {
        byte[] message = "触发流控规则".getBytes(StandardCharsets.UTF_8);
        if (blockException instanceof FlowException) {
            message = "接口限流".getBytes(StandardCharsets.UTF_8);
        } else if (blockException instanceof DegradeException) {
            message = "服务降级".getBytes(StandardCharsets.UTF_8);
        } else if (blockException instanceof ParamFlowException) {
            message = "热点参数限流".getBytes(StandardCharsets.UTF_8);
        } else if (blockException instanceof SystemBlockException) {
            message = "触发系统保护规则".getBytes(StandardCharsets.UTF_8);
        } else if (blockException instanceof AuthorityException) {
            message = "授权规则不通过".getBytes(StandardCharsets.UTF_8);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new String(message, StandardCharsets.UTF_8));
    }

}
