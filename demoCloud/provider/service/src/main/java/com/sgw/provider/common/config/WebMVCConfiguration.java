package com.sgw.provider.common.config;

import com.sgw.provider.common.converter.StringToSortConverter;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

/**
 * @author sunnys
 */
@Configuration
@Log
public class WebMVCConfiguration implements WebMvcConfigurer {

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

    @Override
    public void addFormatters(FormatterRegistry registry) {
        StringToSortConverter stringToSortConverter = new StringToSortConverter((ConversionService) registry);
        registry.addConverter(stringToSortConverter);
    }

}
