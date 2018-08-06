package com.neu.order.model.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrdersView {
	private int orderId;	
	private Address address;
	private char receiveGoodsTime;
	
	public char getReceiveGoodsTime() {
		return receiveGoodsTime;
	}
	public void setReceiveGoodsTime(char receiveGoodsTime) {
		this.receiveGoodsTime = receiveGoodsTime;
	}
	private int invoiceType;
	private String payment;
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	private String orderStatus;//“0”：未付款   “1”：已付款 “2”：已配货   “3”：已发货 “4”：交易完成  “5”：正在退款  “6”：已退款    “7”：已关闭
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date payTime;
	private double totalPrice;

	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	private List<ProductsView> productsView;
	private List<Integer> giftcardsId;
	public List<ProductsView> getProductsView() {
		return productsView;
	}
	public void setProductsView(List<ProductsView> productsView) {
		this.productsView = productsView;
	}

	public List<Integer> getGiftcardsId() {
		return giftcardsId;
	}
	public void setGiftcardsId(List<Integer> giftcardsId) {
		this.giftcardsId = giftcardsId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public int getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(int invoiceType) {
		this.invoiceType = invoiceType;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	public Date getSendGoodsTime() {
		return sendGoodsTime;
	}
	public void setSendGoodsTime(Date sendGoodsTime) {
		this.sendGoodsTime = sendGoodsTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	protected Date refundTime;
	protected Date sendGoodsTime;
	protected Date closeTime;
	protected Date endTime;
}
