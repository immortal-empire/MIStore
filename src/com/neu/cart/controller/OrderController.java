package com.neu.cart.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.cart.model.bean.Address;
import com.neu.cart.model.bean.GiftCard;
import com.neu.cart.model.bean.Order;
import com.neu.cart.model.bean.ShoppingCartInfo;
import com.neu.cart.model.service.OrderService;

@Controller
@RequestMapping(value="cartAndOrder",
				method={RequestMethod.POST,RequestMethod.GET})
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//����û���ַ
	@RequestMapping("getAddress")
	public@ResponseBody List<Address> getAddress(int userId){
		//System.out.println(userId);
		List<Address> list = orderService.getAddress(userId);
		return list;
		
	}
	//�����û��޸ĵĵ�ַ
	@RequestMapping("updateAddress")
	public@ResponseBody String updateAddress(@RequestBody Address address){
		//System.out.println(address.getConsumerName());
		String clue = orderService.updateAddress(address);
		return clue;
	}
	
	//����û�����Ʒ��
	@RequestMapping("getGiftCard")
	public@ResponseBody List<GiftCard> getGiftCard(int userId){
		List<GiftCard> list = orderService.getGiftCard(userId);
		return list;	
	}
	
	//���涩��
	@RequestMapping("saveOrder")
	public@ResponseBody int  saveOrder(@RequestBody Order order) {
		//System.out.println(order.getProducts().size());
		int orderId=orderService.saveOrder(order);
		return orderId;
		
	}
}
