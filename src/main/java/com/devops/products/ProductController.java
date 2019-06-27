package com.devops.products;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
        try {
            return userService.test();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() throws Exception{
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        String url = "http://30472.grff4a78.9upmwa3t.590bc8.grapps.cn/test1";
        try {
            HttpGet httpGet = new HttpGet(url);

            client = HttpClients.createDefault();
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }

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
