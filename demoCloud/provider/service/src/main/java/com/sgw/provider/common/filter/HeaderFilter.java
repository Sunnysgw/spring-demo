package com.sgw.provider.common.filter;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
@Component
@WebFilter(urlPatterns = "/userInfo/**")
@Log
public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String data = request.getHeader("data");
        String requestURI = request.getRequestURI();
        log.info(String.format("request url-%s, data-%s", requestURI, data));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
