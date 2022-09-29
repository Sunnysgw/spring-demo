package com.sgw.aop;

import com.sgw.annotions.DS;
import com.sgw.config.DatabaseConfiguration;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(1)
public class DatabaseSourceAOP {

    @Pointcut("@annotation(com.sgw.annotions.DS)")
    public void ds() {}

    @Before("ds()")
    public void beforeAdvice(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DS ds = method.getAnnotation(DS.class);
        DatabaseConfiguration.DatasourceHolder.setKey(ds.source());
    }

    @After("ds()")
    public void afterAdvice() {
        DatabaseConfiguration.DatasourceHolder.clearKey();
    }


}
