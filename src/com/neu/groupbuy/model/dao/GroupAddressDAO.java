package com.neu.groupbuy.model.dao;

import java.util.List;

import com.neu.groupbuy.model.bean.GroupAddress;




public interface GroupAddressDAO {

	//��ȡ��ַ��Ϣ
	public List<GroupAddress> loadAddressByCustomerId(int customerId);
	
		
}
