package com.sgw.common.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: sunnysgw
 * @since: 1.0
 **/
@Component
@WebFilter
public class ContentTypeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.addHeader("Content-Type", "application/json;charset=UTF-8");
        chain.doFilter(request, response);
    }
}
