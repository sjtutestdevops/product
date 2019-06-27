package com.devops.products.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "devops-users")
public interface UserService {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    String test();
    
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    String test2(@RequestParam(value = "name", required = true) String name);
    
    @RequestMapping(value = "/getuserid", method = RequestMethod.GET)
    Integer getUserId(@RequestParam(value = "name", required = true) String name);

}
