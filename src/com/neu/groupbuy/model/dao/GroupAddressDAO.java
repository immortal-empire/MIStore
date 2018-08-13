package com.neu.groupbuy.model.dao;

import java.util.List;

import com.neu.groupbuy.model.bean.GroupAddress;




public interface GroupAddressDAO {

	//获取地址信息
	public List<GroupAddress> loadAddressByCustomerId(int customerId);
	
		
}
