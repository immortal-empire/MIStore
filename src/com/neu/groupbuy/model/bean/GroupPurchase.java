package com.neu.groupbuy.model.bean;

import java.util.Date;

import com.neu.prodetail.model.bean.Product;

public class GroupPurchase {
		
		private int groid;
		private int proid;//产品Id
		private float groupPrice;
		private int gMinimumSum;//最低参团人数
		private Date gStartTime;
		private Date gEndTime;
		private int gResidueSum;//可团购库存
		private String gState;
		private Product product;
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getGroid() {
			return groid;
		}
		public void setGroid(int groid) {
			this.groid = groid;
		}
		public int getProid() {
			return proid;
		}
		public void setProid(int proid) {
			this.proid = proid;
		}
		public float getGroupPrice() {
			return groupPrice;
		}
		public void setGroupPrice(float groupPrice) {
			this.groupPrice = groupPrice;
		}
		public int getgMinimumSum() {
			return gMinimumSum;
		}
		public void setgMinimumSum(int gMinimumSum) {
			this.gMinimumSum = gMinimumSum;
		}
		public Date getgStartTime() {
			return gStartTime;
		}
		public void setgStartTime(Date gStartTime) {
			this.gStartTime = gStartTime;
		}
		public Date getgEndTime() {
			return gEndTime;
		}
		public void setgEndTime(Date gEndTime) {
			this.gEndTime = gEndTime;
		}
		public int getgResidueSum() {
			return gResidueSum;
		}
		public void setgResidueSum(int gResidueSum) {
			this.gResidueSum = gResidueSum;
		}
		public String getgState() {
			return gState;
		}
		public void setgState(String gState) {
			this.gState = gState;
		}
		
}
