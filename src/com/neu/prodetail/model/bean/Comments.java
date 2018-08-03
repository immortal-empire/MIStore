package com.neu.prodetail.model.bean;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neu.util.model.bean.Customer;

public class Comments {
	
	private int commId;
	private int cid;
	private int proId;
	private int orderid;
	private int rank;
	private String comment;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
	private Timestamp commdate;
	private String commurl;
	private Customer customer;
	private List<String> commImageName;
	
	public int getCommId() {
		return commId;
	}
	public void setCommId(int commId) {
		this.commId = commId;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getCommdate() {
		return commdate;
	}
	public void setCommdate(Timestamp commdate) {
		this.commdate = commdate;
	}
	public String getCommurl() {
		return commurl;
	}
	public void setCommurl(String commurl) {
		this.commurl = commurl;
	}
	public List<String> getCommImageName() {
		return commImageName;
	}
	public void setCommImageName(List<String> commImageName) {
		this.commImageName = commImageName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
