package com.ngsky.tools.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * <dl>
 * <dt>IndexController</dt>
 * <dd>Description: 基础过滤，部分请求直接放行(/user/login...)</dd>
 * <dd>CreateDate: 10/18/2018 1:21 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class BaseFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("******* baseFilter *******");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
