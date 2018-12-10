package com.itmuch.cloud.study.conf;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * <dl>
 * <dt>TestConfiguration</dt>
 * <dd>Description: 使用 RibbonClient,
 * 1.为特定name的RibbonClient自定义配置
 * 2.使用@RibbonClient的configuration属性，指定Ribbon的配置类
 * </dd>
 * <dd>CreateDate: 12/6/2018 11:13 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
@Configuration
public class TestConfiguration {

}
