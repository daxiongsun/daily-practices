package com.itmuch.cloud.study.conf;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <dl>
 * <dt>RibbonConfiguration</dt>
 * <dd>Description: Ribbon 配置类
 * 注意：该类不应该在应用程序上下文 @ComponentScan 中
 * </dd>
 * <dd>CreateDate: 12/6/2018 11:08 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        // 负载均衡规则, 随机
        return new RandomRule();
    }

}
