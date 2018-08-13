package com.neu.order.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neu.order.model.bean.OrdersView;
import com.neu.order.model.bean.ProductsCommentView;
import com.neu.order.model.bean.ProductsView;

public interface OrderViewsMapper {
	public List<OrdersView> getOrderByType(@Param("type")int type, @Param("cid")int cid);
	public void updateOrderStatus(@Param("orderStatus")int orderStatus, @Param("orderId")int orderId);

	public OrdersView getOrderById( @Param("oid")int oid);
	public List<ProductsCommentView> getUncommentedProduct(Integer cid);
}
