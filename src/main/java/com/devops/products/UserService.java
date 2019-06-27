package com.devops.products;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name= "users")
public interface UserService {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    String test();
}
