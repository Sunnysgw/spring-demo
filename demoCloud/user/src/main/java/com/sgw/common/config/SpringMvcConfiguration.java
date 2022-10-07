package com.sgw.common.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author sunny
 */
@WebFilter
@Configuration
@Log
public class SpringMvcConfiguration implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("request arrive" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
