package com.ngsky.tools.config;

import com.ngsky.tools.gateway.controller.AttackFilter;
import com.ngsky.tools.gateway.controller.AuthFilter;
import com.ngsky.tools.gateway.controller.BaseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * <dl>
 * <dt>IndexController</dt>
 * <dd>Description: 过滤器配置</dd>
 * <dd>CreateDate: 10/18/2018 1:21 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Configuration
public class FilterConfig {
    @Bean
    public Filter baseFilter(){
        return new BaseFilter();
    }
    @Bean
    public Filter attackFilter(){
        return new AttackFilter();
    }
    @Bean
    public Filter authFilter(){
        return new AuthFilter();
    }
    @Bean
    public FilterRegistrationBean baseFilterBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(baseFilter());
//        filterRegistrationBean.addUrlPatterns("/yuefu/user/user/*");
        filterRegistrationBean.setOrder(1);//order的数值越小 则优先级越高
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean attackFilterBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(attackFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean authFilterBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(authFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(3);
        return filterRegistrationBean;
    }

}
