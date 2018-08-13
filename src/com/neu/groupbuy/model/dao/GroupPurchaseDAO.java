package com.neu.groupbuy.model.dao;

import java.util.List;

import com.neu.groupbuy.model.bean.GroupPurchase;



public interface GroupPurchaseDAO {

	//查询所有团购商品
	public List<GroupPurchase> selectGroupPurchase();

}
