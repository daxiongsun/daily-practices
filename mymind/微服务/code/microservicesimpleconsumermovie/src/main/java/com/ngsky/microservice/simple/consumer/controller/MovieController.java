package com.ngsky.microservice.simple.consumer.controller;

import com.ngsky.microservice.simple.consumer.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <dl>
 * <dt>MovieController</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/11/2018 10:43 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@RestController
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
    }
}
