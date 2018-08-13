package com.neu.order.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.order.model.bean.OrdersView;
import com.neu.order.model.bean.ProductsCommentView;
import com.neu.order.model.dao.OrderViewsMapper;

@Service
public class OrderViewService {
	@Autowired
	private OrderViewsMapper orderViewMapper;

	public List<OrdersView> getOrderByType(int type,int cid) {
		List<OrdersView> orders = orderViewMapper.getOrderByType(type, cid);
		return orders;
	}
	
	
	public void updateOrderStatus(int orderStatus,int orderId) {
		orderViewMapper.updateOrderStatus(orderStatus, orderId);
		System.out.println("ÐÞ¸Ä×´Ì¬³É¹¦");
	}

	
	public OrdersView getOrderById(int oid) {
		OrdersView order = orderViewMapper.getOrderById(oid);
		return order;
	}
	
	
	public List<ProductsCommentView> getUncommentedProduct(Integer cid) {
		List<ProductsCommentView> productsCommentViews = orderViewMapper.getUncommentedProduct(cid);
		System.out.println(cid);
		return productsCommentViews;
	}
}
