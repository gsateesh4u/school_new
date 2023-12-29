package com.amex.sms.school;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 20 Nov, 2023
 */
//@Component
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("****************53");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
