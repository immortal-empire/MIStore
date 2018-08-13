package com.neu.cart.model.bean;

public class GiftCard {

	private int Cid;
	private int giftCardId;
	private double amount;
	private double balance;
	private String password;
	private String status;
	
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getGiftCardId() {
		return giftCardId;
	}
	public void setGiftCardId(int giftCardId) {
		this.giftCardId = giftCardId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
