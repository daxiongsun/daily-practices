package com.ngsky.tools.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <dl>
 * <dt>DruidConfiguration</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/28/2018 12:36 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Configuration
public class DruidConfig {

    @Bean
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean statViewServlet(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP 白名单
        servletRegistrationBean.addInitParameter("allow", "192.168.0.101");
        // IP 黑名单
        servletRegistrationBean.addInitParameter("deny", "192.168.0.1");
        // 控制台管理用户
        servletRegistrationBean.addInitParameter("loginUsername", "druid");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");

        // 是否可重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean statFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
