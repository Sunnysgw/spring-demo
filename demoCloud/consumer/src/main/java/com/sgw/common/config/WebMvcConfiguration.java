package com.sgw.common.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

/**
 * @author sunnys
 */
@Configuration
@EnableWebMvc
@Log
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static class MyInterceptor implements WebRequestInterceptor {

        @Override
        public void preHandle(WebRequest request) throws Exception {
            if (request instanceof DispatcherServletWebRequest) {
                DispatcherServletWebRequest webRequest = (DispatcherServletWebRequest) request;
                log.info("request " + webRequest.getRequest().getRequestURI() + "arrive");
            }
        }

        @Override
        public void postHandle(WebRequest request, ModelMap model) throws Exception {

        }

        @Override
        public void afterCompletion(WebRequest request, Exception ex) throws Exception {

        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(new MyInterceptor());
    }

}
