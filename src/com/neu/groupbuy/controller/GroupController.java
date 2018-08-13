package com.neu.groupbuy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.groupbuy.model.service.GroupService;



@Controller
@RequestMapping(value="assets",method={RequestMethod.POST,RequestMethod.GET})
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	private int groupPurchaseId;
	
	//查询团购商品信息
	@RequestMapping("selectGroupPurchaseById")
	public@ResponseBody GroupPurchase selectGroupPurchaseById(int groId) {
		System.out.println("进入商品Controller");
		System.out.println(groId);
		groupPurchaseId = groId;
		GroupPurchase groupPurchase = groupService.selectGroupPurchaseById(groId);
		System.out.println(groupPurchase.getGroid()+groupPurchase.getProduct().getProName());
		return groupPurchase;		
	}
	
	//自动更新团的状态
	@RequestMapping("updateGroupStateAuto")
	public@ResponseBody void updateGroupStateAuto() {
		System.out.println("自动更新团的状态");
		groupService.updateGroupStateAuto(groupPurchaseId);
		System.out.println("更新完成");
	}
	
	//查询团
	@RequestMapping("selectGroup")
	public@ResponseBody List<Group> selectGroup() {
		
		//调用service层进行查询
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		System.out.println("selectGroup  groId   "+groupPurchaseId);
		List<Group> list = groupService.selectGroup(groupPurchaseId);
		for(Group group:list) {
			String time = sdf.format(group.getEndTime());
			group.setEndTimeStr(time);	
		}
		return list;
	}
	
	//查询当前正在拼团的人数
	@RequestMapping("selectNumOfPerson")
	public@ResponseBody  int selectNumOfPerson() {
		System.out.println("11111");
		int number = groupService.selectNumOfPerson(groupPurchaseId);
		System.out.println("拼团人数"+number);
		return number;
	}
	
	//新增团
	@RequestMapping("createGroup")
	public@ResponseBody int createGroup() {
		System.out.println("进入新增团的Controller");
		System.out.println("从网址获得团购商品Id"+groupPurchaseId);
	 	int newGroupId = groupService.createGroup(groupPurchaseId);
		System.out.println("新增团Id"+newGroupId);
		return newGroupId;
	}
	
}
