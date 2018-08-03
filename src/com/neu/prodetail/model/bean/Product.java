package com.neu.prodetail.model.bean;

import java.util.Date;

public class Product {
	private Integer proId;
	private CommodityTwoType twoType;
	private String proName;
	private String color;
	private String configuration;
	private String picture;
	private Integer inventory;
	private double sellingPrice;
	private String proDescriptive;
	private Date addtime;
	private String state;
	private int volume;//销量，临时查询需要
	private int isFavor;//在搜索时，判断该商品是否是用户的收藏品
	
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public CommodityTwoType getTwoType() {
		return twoType;
	}
	public void setTwoType(CommodityTwoType twoType) {
		this.twoType = twoType;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getProDescriptive() {
		return proDescriptive;
	}
	public void setProDescriptive(String proDescriptive) {
		this.proDescriptive = proDescriptive;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}	
	public int getisFavor() {
		return isFavor;
	}
	public void setFavor(int isFavor) {
		this.isFavor = isFavor;
	}
	
	@Override
	public String toString() {
		return "Product [proId=" + proId + ", twoType=" + twoType
				+ ", proName=" + proName + ", color=" + color
				+ ", configuration=" + configuration + ", picture=" + picture
				+ ", inventory=" + inventory + ", sellingPrice=" + sellingPrice
				+ ", proDescriptive=" + proDescriptive + ", addtime=" + addtime
				+ ", state=" + state + ", volume=" + volume + ", isFavor="
				+ isFavor + "]";
	}	
}
