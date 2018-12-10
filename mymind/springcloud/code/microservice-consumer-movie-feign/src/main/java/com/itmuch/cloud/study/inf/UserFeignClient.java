package com.itmuch.cloud.study.inf;

import com.itmuch.cloud.study.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <dl>
 * <dt>UserFeignClient</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 12/7/2018 12:04 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
