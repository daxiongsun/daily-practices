package com.ngsky.microservice.de;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <dl>
 * <dt>DiscoveryEurekaApplication</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/12/2018 11:39 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryEurekaApplication {
    public static void main(String[] args){
        SpringApplication.run(DiscoveryEurekaApplication.class, args);
    }
}
