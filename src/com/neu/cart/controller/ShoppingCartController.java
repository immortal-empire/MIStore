package com.neu.cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.cart.model.bean.ShoppingCartInfo;
import com.neu.cart.model.service.ShoppingCartService;

@Controller
@RequestMapping(value="cartAndOrder",
				method={RequestMethod.POST,RequestMethod.GET})
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//删除购物车商品
	@RequestMapping("deleteSCProduct")
	public @ResponseBody String deleteSCProduct(@RequestBody ShoppingCartInfo shoppingCart) {
		//System.out.println(shoppingCart.getId()+" " +shoppingCart.getInventory()+" " +shoppingCart.getAddTime());
		String clue=shoppingCartService.deleteProduct(shoppingCart);
		return clue;
	}
	
	//修改购物车的商品数量
	@RequestMapping("updateQuantity")
	public @ResponseBody String updateQuantity(@RequestBody ShoppingCartInfo shoppingCart) {
		//System.out.println("111");
		//System.out.println(shoppingCart.getId()+" " +shoppingCart.getInventory()+" " +shoppingCart.getAddTime());
		String clue=shoppingCartService.updateQuantity(shoppingCart);
		return clue;
	}
	
	//修改数据库的喜欢的商品
	@RequestMapping("updateFavor")
	public @ResponseBody String updateFavor(@RequestBody Map<String,Object> map) {
		//System.out.println(map.toString());
		String clue=shoppingCartService.updateFavor(map);
		return clue;
	}

	//查询商品库存
	@RequestMapping("selectInventory")
	public  @ResponseBody int selectInventory(int proId) {
		int inventory=shoppingCartService.selectInventory(proId);
		return inventory;	
	}
	
	//修改闪购商品的状态
	@RequestMapping("updateProductStatus")
	public @ResponseBody String updateProductStatus(@RequestBody ShoppingCartInfo shoppingCart) {
		//更新状态
		String clue=shoppingCartService.updateProductStatus(shoppingCart);
		return clue;
		
	}
	
	//查询购物车商品
	@RequestMapping("selectShoppingcart")
	public @ResponseBody List<ShoppingCartInfo> selectShoppingcart(int userId){
		List<ShoppingCartInfo> list = shoppingCartService.selectShoppingcart(userId);
		return list;
	}

}
