package com.neu.util.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.util.model.bean.Customer;
import com.neu.util.model.service.CustomerService;

@Controller
public class RegisterController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public String register(Customer c) {
		System.out.println(c.getCname());
		System.out.println(c.getCphone());
		customerService.saveCustomer(c);
		return "{\"result\":true}";
	}
	
}
