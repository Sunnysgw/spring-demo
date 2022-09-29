package com.sgw;

import static org.junit.Assert.assertTrue;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static class Person {

        public void sayHello() {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Person());
        proxyFactory.addAdvisor(new PointcutAdvisor() {
            @Override
            public Pointcut getPointcut() {
                return new StaticMethodMatcherPointcut() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return method.getName().contains("say");
                    }
                };
            }

            @Override
            public Advice getAdvice() {
                return new MethodInterceptor() {
                    @Override
                    public Object invoke(MethodInvocation invocation) throws Throwable {
                        System.out.println("before involk");
                        Object returnVal = invocation.proceed();
                        System.out.println("after involk");
                        return returnVal;
                    }
                };
            }

            @Override
            public boolean isPerInstance() {
                return false;
            }
        });
        Person proxy = (Person) proxyFactory.getProxy();
        proxy.sayHello();

    }
}
