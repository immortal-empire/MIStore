package com.neu.cart.model.bean;

import java.sql.Timestamp;
import java.util.List;

public class Order {

	private int orderId;
	private List<ShoppingCart> products;
	private int addressId;
	private int consumerId;
	private String invoiceType;//��0��Ϊ���ӷ�Ʊ ��1��Ϊֽ�ʷ�Ʊ
	private int Gid;//��Ʒ��id
	private String orderStatus;//��0����δ����   ��1�����Ѹ��� ��2���������   ��3�����ѷ��� ��4�����������  ��5���������˿�  ��6�������˿�    ��7�����ѹر�
	private Timestamp startTime;
	private Timestamp payTime;
	private Timestamp redundTime;
	private Timestamp sendGoodsTime;
	private Timestamp closeTime;
	private Timestamp endTime;
	private double totalPrice;
	private String receiveGoodsTime;//"0":����ʱ�䣻"1":ֻ����ĩ�ջ���"2":ֻ����һ�������ջ�
	private String payment;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	public int getGid() {
		return Gid;
	}
	public void setGid(int gid) {
		Gid = gid;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getPayTime() {
		return payTime;
	}
	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}
	public Timestamp getRedundTime() {
		return redundTime;
	}
	public void setRedundTime(Timestamp redundTime) {
		this.redundTime = redundTime;
	}
	public Timestamp getSendGoodsTime() {
		return sendGoodsTime;
	}
	public void setSendGoodsTime(Timestamp sendGoodsTime) {
		this.sendGoodsTime = sendGoodsTime;
	}
	public Timestamp getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getReceiveGoodsTime() {
		return receiveGoodsTime;
	}
	public void setReceiveGoodsTime(String receiveGoodsTime) {
		this.receiveGoodsTime = receiveGoodsTime;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<ShoppingCart> getProducts() {
		return products;
	}
	public void setProducts(List<ShoppingCart> products) {
		this.products = products;
	}
}
