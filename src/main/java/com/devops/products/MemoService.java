package com.devops.products;

import java.sql.Connection;
import java.util.Date;  
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MemoService {

	public String setMemo(String product_name, String user_id, Integer exp) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.174:3306/devops?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "ym19950823");
            Statement stat = conn.createStatement();
            
            String sql = "insert into memo (user_id, product_name, exp) value ('"+user_id+"', '"+product_name+"', '" + exp + "')";
            System.out.println(sql);
            stat.executeUpdate(sql);
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
	}
	
	public ArrayList<MemoEntity> getUserMemo(String user_id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.174:3306/devops?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "ym19950823");
            Statement stat = conn.createStatement();
            
            String sql = "select * from memo where user_id = '"+ user_id + "'";
            ResultSet result = stat.executeQuery(sql);
            
            ArrayList<MemoEntity> memo_lst = new ArrayList<MemoEntity>();
            while(result.next()) {
            	MemoEntity memo = new MemoEntity();
            	memo.setMemo_id(result.getString("memo_id"));
            	memo.setUser_id(result.getString("user_id"));
            	memo.setProduct_id(result.getString("product_name"));
            	memo.setStart_time(result.getDate("start_time"));
            	memo.setExp(result.getInt("exp"));
            	memo_lst.add(memo);
            }
            return memo_lst;
        } catch (Exception e) {
            return new ArrayList<MemoEntity>();
        }
	}
	
	public Object isExpired(String memo_id) {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://10.0.2.174:3306/devops?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
            Connection conn = DriverManager.getConnection(url, "root", "ym19950823");
            Statement stat = conn.createStatement();
            
            String sql = "select * from memo where memo_id = '"+ memo_id + "'";
            ResultSet result = stat.executeQuery(sql);
            
            if(result.next()) {
            	Date start_time = result.getDate("start_time");
            	Date current_time = new Date();
            	
            	Calendar cal_start = Calendar.getInstance();
            	Calendar cal_curr = Calendar.getInstance();
            	cal_start.setTime(start_time);
            	cal_curr.setTime(current_time);
            	
            	int days = ((int) (cal_curr.getTime().getTime() / 1000) - (int) (cal_start.getTime().getTime() / 1000)) / 3600 / 24;
            	System.out.println(days);
            	
            	Integer exp = result.getInt("exp");
            	if (days > exp*31) {
            		return "过期了";
            	}
            	return "还可使用";
            }
            return "不存在该备忘";
        } catch (Exception e) {
            return false;
        }
	}
}
