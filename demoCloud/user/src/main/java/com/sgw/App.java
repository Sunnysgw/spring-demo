package com.sgw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sgw.dao"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class App 
{

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
