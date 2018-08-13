package com.neu.seckill.model.bean;

import java.util.Date;

public class FlashPurchase {
	
	private int id;
	private int cId;
	private int proId;
	private double price;
	private Date addtime;
	private Date seckillStartTime;
	private Date seckillEndTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Date getSeckillStartTime() {
		return seckillStartTime;
	}
	public void setSeckillStartTime(Date seckillStartTime) {
		this.seckillStartTime = seckillStartTime;
	}
	public Date getSeckillEndTime() {
		return seckillEndTime;
	}
	public void setSeckillEndTime(Date seckillEndTime) {
		this.seckillEndTime = seckillEndTime;
	}
}
