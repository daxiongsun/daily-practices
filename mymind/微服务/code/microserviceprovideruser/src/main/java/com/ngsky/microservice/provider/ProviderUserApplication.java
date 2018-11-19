package com.ngsky.microservice.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <dl>
 * <dt>ProviderUserApplication</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/11/2018 10:04 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class ProviderUserApplication {
    public static void main(String[] args){
        SpringApplication.run(ProviderUserApplication.class, args);
    }
}
