package com.devops.products.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import com.devops.products.mapper.ProductMapper;
import com.devops.products.model.Product;
import com.devops.products.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

	public String getMainIndients(String name) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andNameEqualTo(name);
        List<Product> productList = productMapper.selectByExample(productExample);
	    if (CollectionUtils.isEmpty(productList)) {
            return "暂时还未收录该产品的成分";
        } else {
	        return productList.get(0).getMain();
        }
	}
	

	public String getDetailedIndients(String name) {
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andNameEqualTo(name);
        List<Product> productList = productMapper.selectByExample(productExample);
        if (CollectionUtils.isEmpty(productList)) {
            return "暂时还未收录该产品的成分";
        } else {
            return productList.get(0).getDetails();
        }
	}
}
