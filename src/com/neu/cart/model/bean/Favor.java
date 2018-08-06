package com.neu.cart.model.bean;

public class Favor {

	private int SCId;
	private int cid;
	private int proid;
	private String status;
	
	public int getSCid() {
		return SCId;
	}
	public void setSCid(int sCId) {
		SCId = sCId;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

