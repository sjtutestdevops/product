package com.devops.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	public String getMainIndients(String name) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.174:3306/devops?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "ym19950823");
            Statement stat = conn.createStatement();
            
    		String sql = "select main from product where name = '" + name + "'";
    		System.out.println(sql);
            ResultSet result = stat.executeQuery(sql);
            result.beforeFirst();
            result.last();
            System.out.println(result.getRow());
            if (result.getRow() == 0) {
            	return "暂时还未收录该产品的成分";
            }
            String indients = result.getString("main");
            return indients;
        } catch (Exception e) {
            return e.getMessage();
        }
	}
	

	public String getDetailedIndients(String name) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.174:3306/devops?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "ym19950823");
            Statement stat = conn.createStatement();
            
    		String sql = "select details from product where name = '" + name + "'";
    		System.out.println(sql);
            ResultSet result = stat.executeQuery(sql);
            result.beforeFirst();
            result.last();
            System.out.println(result.getRow());
            if (result.getRow() == 0) {
            	return "暂时还未收录该产品的成分";
            }
            String indients = result.getString("details");
            return indients;
        } catch (Exception e) {
            return e.getMessage();
        }
	}
}
