package com.neu.groupbuy.model.bean;

import java.util.Date;

import com.neu.util.model.bean.Customer;

public class GroupOrder {
	
		private int id;
		private int customerId;
		private int groupId;
		private int addressId;
		private int quantity;
		private float account;
		private String distributionMode;
		private String distributionTime;
		private String invoiceType;
		private String payment;
		private String isPay;
		private String state;
		private Date startTime;
		private Date payTime;
		private Date refundTime;
		private Date sendGoodsTime;
		private Date closeTime;
		private Date endTime;
		private Group group;
		private GroupAddress groupAddress;
		private Customer customer;
		
		public String getInvoiceType() {
			return invoiceType;
		}
		public void setInvoiceType(String invoiceType) {
			this.invoiceType = invoiceType;
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		public GroupAddress getAddress() {
			return groupAddress;
		}
		public void setAddress(GroupAddress groupAddress) {
			this.groupAddress = groupAddress;
		}
		public Group getGroup() {
			return group;
		}
		public void setGroup(Group group) {
			this.group = group;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		
		public int getGroupId() {
			return groupId;
		}
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}
		public int getAddressId() {
			return addressId;
		}
		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public float getAccount() {
			return account;
		}
		public void setAccount(float account) {
			this.account = account;
		}
		public String getDistributionMode() {
			return distributionMode;
		}
		public void setDistributionMode(String distributionMode) {
			this.distributionMode = distributionMode;
		}
		public String getDistributionTime() {
			return distributionTime;
		}
		public void setDistributionTime(String distributionTime) {
			this.distributionTime = distributionTime;
		}
		public String getPayment() {
			return payment;
		}
		public void setPayment(String payment) {
			this.payment = payment;
		}
		public String getIsPay() {
			return isPay;
		}
		public void setIsPay(String isPay) {
			this.isPay = isPay;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
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
			
}
