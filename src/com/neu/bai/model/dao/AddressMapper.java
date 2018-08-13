package com.neu.bai.model.dao;
import java.util.List;

import com.neu.cart.model.bean.Address;

public interface AddressMapper {

	//查询用户地址
		public List<Address> selectAddress(int userId);
		//修改用户地址
		public void updateAddress(Address address);
		//新增用户地址
		public void insertAddress(Address address);
		//删除用户地址
		public void deleteAddress(int cid);
		
}
