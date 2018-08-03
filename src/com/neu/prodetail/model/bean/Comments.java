package com.neu.prodetail.model.bean;

import java.util.Date;

public class Comments {

	private int commId;
	private int cid;
	private String cName;
	private Product product;
	private int orderid;
	private int rank;
	private String comment;
	private Date commdate;
	private String commurl;
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
	public Date getCommdate() {
		return commdate;
	}
	public void setCommdate(Date commdate) {
		this.commdate = commdate;
	}
	public String getCommurl() {
		return commurl;
	}
	public void setCommurl(String commurl) {
		this.commurl = commurl;
	}
	public String getCname() {
		return cName;
	}
	public void setCname(String cname) {
		this.cName = cname;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "Comments [commId=" + commId + ", cid=" + cid + ", cname="
				+ cName + ", product=" + product.toString() + ", orderid=" + orderid
				+ ", rank=" + rank + ", comment=" + comment + ", commdate="
				+ commdate + ", commurl=" + commurl + "]";
	}

	
	
}
