package com.devops.products;

import java.sql.Date;

public class MemoEntity {
	private String memo_id;
	private String user_id;
	private String product_id;
	private Date start_time;
	private Integer exp;
	
	public String getMemo_id() {
		return memo_id;
	}
	public void setMemo_id(String memo_id) {
		this.memo_id = memo_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	
}
