package com.ngsky.swaggerdemo.web;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <dl>
 * <dt>MyWebFilter</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/18/2018 5:00 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Order(1)
@WebFilter(filterName = "MSecurity",urlPatterns = {"*"})
public class MyWebFilter implements Filter{
        @Override
        public void init(FilterConfig filterConfig) {

        }
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response= (HttpServletResponse) servletResponse;
            System.out.println(request.getRequestURI());


            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {

        }
}
