package com.neu.cart.model.dao;

import java.util.Map;

import com.neu.cart.model.bean.Order;

public interface OrderDAO {

	//插入订单
	public void insertOrder(Order order);
	//保存订单关联的商品
	public void insertOrderItem(Map<String,Object> map);
	//修改商品库存
	public void updateInventory(Map<String,Object> map);
}
