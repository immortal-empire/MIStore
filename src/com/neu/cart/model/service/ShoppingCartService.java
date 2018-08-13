package com.neu.cart.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.cart.model.bean.ShoppingCartInfo;
import com.neu.cart.model.dao.FavorDAO;
import com.neu.cart.model.dao.ShoppingCartInfoDAO;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartInfoDAO shoppingCartDAO;
	@Autowired
	private FavorDAO favorDAO;
	
	public String deleteProduct(ShoppingCartInfo sc) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productStatus", 0);
		map.put("Id", sc.getId());
		
		shoppingCartDAO.updateProductStatus(map);
		return "删除状态更新成功！";
	}
	
	public String updateQuantity(ShoppingCartInfo sc) {
		
		//System.out.println(sc.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productQuantity", sc.getQuantity());
		map.put("Id", sc.getId());
		
		shoppingCartDAO.updateQuantity(map);
		return "购买数量更新成功！";
	}
	
	public String updateFavor(Map<String,Object> map) {
		shoppingCartDAO.updateFavor(map);
		System.out.println(map.toString());
		int count = favorDAO.selectFavor(map);
		if(count!=0) {
			favorDAO.updateFavorStatus(map);;
		} else {
			favorDAO.insertFavor(map);
		}
		return "已修改是否喜欢并加入收藏列表";	
	}
	
	public int selectInventory(int proId) {
		int inventory=shoppingCartDAO.selectInventory(proId);
		return inventory;
	}
	
	public String updateProductStatus(ShoppingCartInfo shoppingCart) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productStatus", shoppingCart.getProductStatus());
		map.put("Id", shoppingCart.getId());
		shoppingCartDAO.updateProductStatus(map);
		return "闪购失效更新成功!";
		
	}
	
	public List<ShoppingCartInfo> selectShoppingcart(int userId){
		
		List<ShoppingCartInfo> list=shoppingCartDAO.selectShoppingcart(userId);
		for(ShoppingCartInfo sc : list){
			sc.setIsChecked(false);
		}
		return list;
		
	}
}
