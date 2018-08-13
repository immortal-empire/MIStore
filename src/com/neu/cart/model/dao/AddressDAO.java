package com.neu.cart.model.dao;

import java.util.List;

import com.neu.cart.model.bean.Address;

public interface AddressDAO {
	
	//查询用户地址
	public List<Address> selectAddress(int userId);
	//修改用户地址
	public void updateAddress(Address address);
	//新增用户地址
	public void insertAddress(Address address);
}
