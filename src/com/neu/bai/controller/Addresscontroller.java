package com.neu.bai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.neu.bai.model.dao.AddressMapper;
import com.neu.bai.model.service.Addressservice;
import com.neu.cart.model.bean.Address;
import com.neu.cart.model.service.OrderService;

@Controller
public class Addresscontroller {
	@Autowired
	private Addressservice addressservice;
	@Autowired
	private OrderService orderService;
	//����û���ַ
			@RequestMapping("getAddressBai")
			public@ResponseBody List<Address> getAddress(int userId){
				System.out.println("getAddress");
				System.out.println(userId);
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
		//ɾ���û���ַ
		@RequestMapping("deleteAddress/{addressId}")
			public@ResponseBody void deleteAddress(@PathVariable int addressId) {
				addressservice.deleteAddress(addressId);
			}
}

