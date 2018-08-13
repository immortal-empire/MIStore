package com.neu.seckill.model.bean;

public class Remind {
	private int remindId;
	private int customerId;
	private int seckillId;
	private String state;
	
	public int getRemindId() {
		return remindId;
	}
	public void setRemindId(int remindId) {
		this.remindId = remindId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(int seckillId) {
		this.seckillId = seckillId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
