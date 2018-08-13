package com.neu.prodetail.model.dao;

import org.apache.ibatis.annotations.Param;

import com.neu.prodetail.model.bean.ShoppingCart;

public interface ShoppingCartDAO {

	public ShoppingCart selectProductInCart(int id);

	public void updateProNumInCart(@Param("id")Integer id, @Param("quantity")Integer quantity);

	public void insertProIntoCart(ShoppingCart c);

	public ShoppingCart selectProductInCartBycid(@Param("cid")int cid, @Param("proId")int proId);

	public void addProNumInCart(Integer id);

	public void updateProNumInCartByCid(@Param("cid")int cid, @Param("quantity")Integer quantity);

}
