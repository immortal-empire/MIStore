package com.neu.bai.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.bai.model.service.Peoservice;
import com.neu.util.model.bean.Customer;

@Controller
public class UpdateCustomer {
	
	@Autowired
	private Peoservice peoservice;
	//根据登录customer的id查询个人信息
	@RequestMapping(value="/customer/{cid}",method=RequestMethod.GET)
	@ResponseBody
	public Customer selectCustomerByCid(@PathVariable int cid)
	{	
		return peoservice.selectCustomerByCid(cid);
	}
	
	//读取修改后个人信息，返回数据库
	@RequestMapping(value="/store/{cid}/{cname}/{gender}",method=RequestMethod.POST)
	@ResponseBody
	public Customer updateCustomer(@PathVariable int cid,@PathVariable String cname,@PathVariable String gender) throws UnsupportedEncodingException {
		
		cname = java.net.URLDecoder.decode(cname, "UTF-8");//一次解码
		System.out.println("进入controller");
		System.out.println(cid);
		peoservice.updateCustomer(cid, cname, gender);
		return peoservice.selectCustomerByCid(cid);
		
	}
	@RequestMapping(value="/changepassword/{cid}/{newPassword}/{oldPassword}",method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(@PathVariable int cid,@PathVariable String newPassword,@PathVariable String oldPassword) {
		return peoservice.changePassword(cid,newPassword,oldPassword);
	}
	
	@RequestMapping(value="/sendphone/{cid}/{phone}",method=RequestMethod.POST)
	@ResponseBody
	public Customer changephone(@PathVariable int cid,@PathVariable String phone) {
		peoservice.changephone(cid, phone);
		return peoservice.selectCustomerByCid(cid);
	}
}