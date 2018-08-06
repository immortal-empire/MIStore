package com.neu.cart.model.dao;


import java.util.Map;

import com.neu.cart.model.bean.ShoppingCart;


public interface ShoppingCartDAO {
	
	//删除购物车的商品(将状态修改为“0”),修改购物车中闪购的商品状态
	public void updateProductStatus(Map<String,Object> map);
	//修改购买商品的数量
	public void updateQuantity(Map<String,Object> map);
	//修改购买商品的isLiked属性
	public void updateFavor(Map<String,Object> map);
	//查询购买商品的库存量
	public int selectInventory(int proId);
}
