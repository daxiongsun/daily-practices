package com.ngsky.microservice.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <dl>
 * <dt>ConsumerMovieApplication</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/11/2018 10:38 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@SpringBootApplication
@EnableEurekaClient
public class ConsumerMovieApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class, args);
    }
}
