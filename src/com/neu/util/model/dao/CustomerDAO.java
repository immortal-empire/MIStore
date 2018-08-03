package com.neu.util.model.dao;

import com.neu.util.model.bean.Customer;

public interface CustomerDAO {
	
	public void saveCustomer(Customer c);

	public Customer findCustomerVerify(Customer c);

	public Customer getCustomerByCphone(String phone);

}
