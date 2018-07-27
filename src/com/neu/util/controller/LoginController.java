package com.neu.util.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.neu.util.model.bean.Customer;
import com.neu.util.model.service.CustomerService;

@Controller
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Customer login(Customer c) {
		System.out.println(c.getCname());
		Customer customer = customerService.findCustomerVerify(c);
		return customer;
	}
	
}
