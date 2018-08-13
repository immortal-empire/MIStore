package com.neu.groupbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.groupbuy.model.bean.GroupAddress;
import com.neu.groupbuy.model.service.AddressService;



@Controller
@RequestMapping(value="assets",method={RequestMethod.POST,RequestMethod.GET})
public class AddressController {
	
		@Autowired
		private AddressService addressService;
		
		//加载地址
		@RequestMapping("loadAddressByCustomerId")
		public@ResponseBody List<GroupAddress> loadAddressByCustomerId(int customerId){
			
			List<GroupAddress> list = addressService.loadAddressByCustomerId(customerId);
			for(GroupAddress groupAddress:list) {
				System.out.println("地址Id:"+groupAddress.getAddressnum()+"   收货人:"+groupAddress.getHost()+"   地址详情:"+groupAddress.getAddressDetail());
			}
			return list;
		}
		
		
}
