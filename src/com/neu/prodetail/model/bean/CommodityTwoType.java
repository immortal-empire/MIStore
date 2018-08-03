package com.neu.prodetail.model.bean;

public class CommodityTwoType {
	private Integer comttyId;
	private Integer comtyId;
	private String comttyName;
	private String state;
	public Integer getComttyId() {
		return comttyId;
	}
	public void setComttyId(Integer comttyId) {
		this.comttyId = comttyId;
	}
	public Integer getComtyId() {
		return comtyId;
	}
	public void setComtyId(Integer comtyId) {
		this.comtyId = comtyId;
	}
	public String getComttyName() {
		return comttyName;
	}
	public void setComttyName(String comttyName) {
		this.comttyName = comttyName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "CommodityTwoType [comttyId=" + comttyId + ", comtyId="
				+ comtyId + ", comttyName=" + comttyName + ", state=" + state
				+ "]";
	}
	
	
}
