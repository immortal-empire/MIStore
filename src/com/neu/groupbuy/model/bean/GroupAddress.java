package com.neu.groupbuy.model.bean;

public class GroupAddress {
	
		private int addressnum;
		private int cid;//�˿�id
		private String addressDetail;
		private String host;//�ջ���
		private String tele;//�ջ��˵绰
		private String state;//״̬
		
		public int getAddressnum() {
			return addressnum;
		}
		public void setAddressnum(int addressnum) {
			this.addressnum = addressnum;
		}
		public int getCid() {
			return cid;
		}
		public void setCid(int cid) {
			this.cid = cid;
		}
		
		public String getAddressDetail() {
			return addressDetail;
		}
		public void setAddressDetail(String addressDetail) {
			this.addressDetail = addressDetail;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getTele() {
			return tele;
		}
		public void setTele(String tele) {
			this.tele = tele;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
			
}
