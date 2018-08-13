package com.neu.bai.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.neu.bai.model.dao.AddressMapper;
import com.neu.cart.model.bean.Address;

@Service
public class Addressservice {

	@Autowired
	private AddressMapper addressmapper;
	//查询当前用户拥有的地址
		public List<Address> getAddress(int userId) {
			List<Address> list = addressmapper.selectAddress(userId);
			for(Address a:list) {
				a.setSelected(false);
			}
			return list;
		}
		
		//更新用户地址（包括新增和修改）
		public String updateAddress(Address address) {
			if(address.getAddressId()==0) {
				addressmapper.insertAddress(address);
			}else {
				addressmapper.updateAddress(address);
			}
			return "地址更新成功！";
		}

		public void deleteAddress(int cid){
			//System.out.println(address.getConsumerName());
			addressmapper.deleteAddress(cid);
		}
}
