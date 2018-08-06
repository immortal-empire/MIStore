package com.neu.cart.model.dao;

import java.util.Map;

public interface FavorDAO {

	//收藏喜欢的商品
	public void insertFavor(Map<String,Object> map);
	//修改收藏的商品的状态
	public void updateFavorStatus(Map<String,Object> map);
	//查询收藏的商品
	public int selectFavor(Map<String,Object> map);
}
