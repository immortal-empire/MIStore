package com.neu.bai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.bai.model.service.Peoservice;

@Controller
public class Addgiftcard {
	@Autowired
	private Peoservice peoservice;
	//���ݵ�ǰ��¼��Cid��
	@RequestMapping(value="/gift/{Cid}/{password}",method=RequestMethod.POST)
	public void addgift(@PathVariable Integer Cid,@PathVariable String password) {
		System.out.println("����controller");
		peoservice.addgift(Cid, password);
		System.out.println(Cid);
	}

}
