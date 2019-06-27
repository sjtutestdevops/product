package com.devops.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return userService.test();
    }

//    查看主要成分
	@RequestMapping(value = "/getmainindients", method = RequestMethod.GET)
    public Object getMainIndients(@RequestParam(value = "name", required = true) String name){
		String inditents = productService.getMainIndients(name);
		return inditents;
    }
    
//	查看详细成分
    @RequestMapping(value = "/getdetailedindients", method = RequestMethod.GET)
    public Object getDetailedIndients(@RequestParam(value = "name", required = true) String name){
    	String inditents = productService.getDetailedIndients(name);
		return inditents;
    }
    
}
