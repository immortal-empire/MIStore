package com.neu.cart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.cart.model.bean.ShoppingCart;
import com.neu.cart.model.service.ShoppingCartService;

@Controller
@RequestMapping(value="cartAndOrder",
				method={RequestMethod.POST,RequestMethod.GET})
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//ɾ�����ﳵ��Ʒ
	@RequestMapping("deleteSCProduct")
	public @ResponseBody String deleteSCProduct(@RequestBody ShoppingCart shoppingCart) {
		//System.out.println(shoppingCart.getId()+" " +shoppingCart.getInventory()+" " +shoppingCart.getAddTime());
		String clue=shoppingCartService.deleteProduct(shoppingCart);
		return clue;
	}
	
	//�޸Ĺ��ﳵ����Ʒ����
	@RequestMapping("updateQuantity")
	public @ResponseBody String updateQuantity(@RequestBody ShoppingCart shoppingCart) {
		//System.out.println("111");
		//System.out.println(shoppingCart.getId()+" " +shoppingCart.getInventory()+" " +shoppingCart.getAddTime());
		String clue=shoppingCartService.updateQuantity(shoppingCart);
		return clue;
	}
	
	//�޸����ݿ��ϲ������Ʒ
	@RequestMapping("updateFavor")
	public @ResponseBody String updateFavor(@RequestBody Map<String,Object> map) {
		//System.out.println(map.toString());
		String clue=shoppingCartService.updateFavor(map);
		return clue;
	}

	//��ѯ��Ʒ���
	@RequestMapping("selectInventory")
	public  @ResponseBody int selectInventory(int proId) {
		int inventory=shoppingCartService.selectInventory(proId);
		return inventory;	
	}
	
	//�޸�������Ʒ��״̬
	@RequestMapping("updateProductStatus")
	public @ResponseBody String updateProductStatus(@RequestBody ShoppingCart shoppingCart) {
		//����״̬
		String clue=shoppingCartService.updateProductStatus(shoppingCart);
		return clue;
		
	}

}
