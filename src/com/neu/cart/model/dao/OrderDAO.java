package com.neu.cart.model.dao;

import java.util.Map;

import com.neu.cart.model.bean.Order;

public interface OrderDAO {

	//���붩��
	public void insertOrder(Order order);
	//���涩����������Ʒ
	public void insertOrderItem(Map<String,Object> map);
	//�޸���Ʒ���
	public void updateInventory(Map<String,Object> map);
}
