package com.neu.seckill.model.bean;

import java.util.Date;

public class SeckillInfo {
	private int seckillId;
	private int productId;
	private String productName;
	private String color;
	private String configuration;
	private String picture;
	private String description;
	private double sellingPrice;
	private double seckillPrice;
	private int remindNum;
	private Date seckillStartTime;
	private Date seckillEndTime;
	private int seckillMaxNum;
	private int seckillRemainNum;
	private String seckillState;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getRemindNum() {
		return remindNum;
	}
	public void setRemindNum(int remindNum) {
		this.remindNum = remindNum;
	}
	public int getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(int seckillId) {
		this.seckillId = seckillId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getSeckillPrice() {
		return seckillPrice;
	}
	public void setSeckillPrice(double seckillPrice) {
		this.seckillPrice = seckillPrice;
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
	public int getSeckillMaxNum() {
		return seckillMaxNum;
	}
	public void setSeckillMaxNum(int seckillMaxNum) {
		this.seckillMaxNum = seckillMaxNum;
	}
	public int getSeckillRemainNum() {
		return seckillRemainNum;
	}
	public void setSeckillRemainNum(int seckillRemainNum) {
		this.seckillRemainNum = seckillRemainNum;
	}
	public String getSeckillState() {
		return seckillState;
	}
	public void setSeckillState(String seckillState) {
		this.seckillState = seckillState;
	}
}
