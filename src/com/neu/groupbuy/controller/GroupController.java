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
	
	//��ѯ�Ź���Ʒ��Ϣ
	@RequestMapping("selectGroupPurchaseById")
	public@ResponseBody GroupPurchase selectGroupPurchaseById(int groId) {
		System.out.println("������ƷController");
		System.out.println(groId);
		groupPurchaseId = groId;
		GroupPurchase groupPurchase = groupService.selectGroupPurchaseById(groId);
		System.out.println(groupPurchase.getGroid()+groupPurchase.getProduct().getProName());
		return groupPurchase;		
	}
	
	//�Զ������ŵ�״̬
	@RequestMapping("updateGroupStateAuto")
	public@ResponseBody void updateGroupStateAuto() {
		System.out.println("�Զ������ŵ�״̬");
		groupService.updateGroupStateAuto(groupPurchaseId);
		System.out.println("�������");
	}
	
	//��ѯ��
	@RequestMapping("selectGroup")
	public@ResponseBody List<Group> selectGroup() {
		
		//����service����в�ѯ
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
	
	//��ѯ��ǰ����ƴ�ŵ�����
	@RequestMapping("selectNumOfPerson")
	public@ResponseBody  int selectNumOfPerson() {
		System.out.println("11111");
		int number = groupService.selectNumOfPerson(groupPurchaseId);
		System.out.println("ƴ������"+number);
		return number;
	}
	
	//������
	@RequestMapping("createGroup")
	public@ResponseBody int createGroup() {
		System.out.println("���������ŵ�Controller");
		System.out.println("����ַ����Ź���ƷId"+groupPurchaseId);
	 	int newGroupId = groupService.createGroup(groupPurchaseId);
		System.out.println("������Id"+newGroupId);
		return newGroupId;
	}
	
}
