package com.neu.groupbuy.model.bean;

import java.util.Date;
import java.util.List;

public class Group {
	
		private int id;
		private int groupPurId;//Õ≈π∫…Ã∆∑Id
		private String state;
		private Date startTime;
		private Date endTime;
		private String endTimeStr;
		private GroupPurchase groupPurchase;
		private GroupOrder groupOrder;
		private List<GroupOrder> groupOrderlist;
		
		public List<GroupOrder> getGroupOrderlist() {
			return groupOrderlist;
		}
		public void setGroupOrderlist(List<GroupOrder> groupOrderlist) {
			this.groupOrderlist = groupOrderlist;
		}
		public String getEndTimeStr() {
			return endTimeStr;
		}
		public void setEndTimeStr(String endTimeStr) {
			this.endTimeStr = endTimeStr;
		}
		public GroupOrder getGroupOrder() {
			return groupOrder;
		}
		public void setGroupOrder(GroupOrder groupOrder) {
			this.groupOrder = groupOrder;
		}
		public GroupPurchase getGroupPurchase() {
			return groupPurchase;
		}
		public void setGroupPurchase(GroupPurchase groupPurchase) {
			this.groupPurchase = groupPurchase;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getGroupPurId() {
			return groupPurId;
		}
		public void setGroupPurId(int groupPurId) {
			this.groupPurId = groupPurId;
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
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
}
