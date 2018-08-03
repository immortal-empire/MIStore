package com.neu.util.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.util.model.bean.Customer;
import com.neu.util.model.dao.CustomerDAO;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDao; 
	
	public void saveCustomer(Customer c) {
		
		c.setState("1");
		customerDao.saveCustomer(c);
	}
	
	public Customer findCustomerVerify(Customer c) {
		
		c.setCphone(c.getCname());
		return customerDao.findCustomerVerify(c);
	}
	
	public Customer getCustomerByCphone(String phone) {
		
		return customerDao.getCustomerByCphone(phone);
	}
}
