package com.neu.groupbuy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.groupbuy.model.bean.GroupAddress;
import com.neu.groupbuy.model.dao.GroupAddressDAO;





@Service
public class AddressService {
	
	@Autowired
	private GroupAddressDAO groupAddressDAO;
	
	public List<GroupAddress> loadAddressByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return groupAddressDAO.loadAddressByCustomerId(customerId);
	}

}
