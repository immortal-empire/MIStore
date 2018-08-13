package com.neu.groupbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.groupbuy.model.service.GroupPurchaseService;




@Controller
@RequestMapping(value="assets",method={RequestMethod.POST,RequestMethod.GET})
public class GroupPurchaseController {
	
	@Autowired
	private GroupPurchaseService groupPurchaseService;
	
	//grouplist中查询所有团购商品
	@RequestMapping("selectGroupPurchase")
	public@ResponseBody  List<GroupPurchase> selectGroupPurchase() {
		System.out.println("111111");
		List<GroupPurchase> list = groupPurchaseService.selectGroupPurchase();
		for(GroupPurchase groupPurchase:list) {
			System.out.println(groupPurchase.getGroid()+"    "+groupPurchase.getProduct().getProName());
		}
		return list;
	}
}
