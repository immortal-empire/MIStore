package com.neu.groupbuy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.util.model.bean.Customer;
import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.groupbuy.model.dao.GroupDAO;


@Service
public class GroupService {
	
	@Autowired
	private GroupDAO groupDAO;
	
	//查询全部正在拼的团
	public List<Group> selectGroup(int groupPurchaseId) {
		System.out.println("service");
		List<Group> grouplist = groupDAO.selectGroup(groupPurchaseId);
		
		for(Group group:grouplist) {
			System.out.println("GroupId "+group.getId());
			int groupId = group.getId();
			List<GroupOrder> groupOrderlist = groupDAO.selectGroupOrderByGroupId(groupId);
			group.setGroupOrderlist(groupOrderlist);
			for(GroupOrder groupOrder:group.getGroupOrderlist()) {
				System.out.println("groupOrderId"+groupOrder.getId());
				int customerId = groupOrder.getCustomerId();
				Customer customer = groupDAO.selectCustomerByGroupOrderId(customerId);
				System.out.println("customerName"+customer.getCname());
				groupOrder.setCustomer(customer);
			}
		}
		
		return grouplist;
	}
	
	//通过团Id查询商品
	public GroupPurchase selectGroupPurchaseById(int groId) {
		// TODO Auto-generated method stub
		System.out.println(groId);
		return groupDAO.selectGroupPurchaseById(groId);
	}

	//查询当前正在拼团的人数
	public int selectNumOfPerson(int groupPurchaseId) {
		// TODO Auto-generated method stub
		int number=0;
		int num=0;
		List<Group> list = groupDAO.selectGroup(groupPurchaseId);
		for(Group group:list) {
			num = groupDAO.selectNumOfPerson(group.getId());
			number+=num;
		}
		System.out.println("当前有"+number+"人在拼团");
		return number;
	}

	//更新团状态
	public void updateGroupStateAuto(int groupPurchaseId) {
		// TODO Auto-generated method stub
		groupDAO.updateGroupStateAuto(groupPurchaseId);
		
		//检测已失效的团
		List<Group> list = groupDAO.selectUselessGroup(groupPurchaseId);
		
		//将失效的团中的订单的状态改成已退款
		for(Group group:list) {
			int groupId = group.getId();
			groupDAO.updateGroupOrderState(groupId);
			//查找该订团中的所有订单
			List<GroupOrder> uselessGroupOrderlist = groupDAO.selectGroupOrderByGroupId(groupId);
			for(GroupOrder groupOrder:uselessGroupOrderlist) {
				int quantity=groupOrder.getQuantity();
				//更新库存
				System.out.println("修改的库存为"+quantity);
				Map<String , Object> map = new HashMap<String , Object>();
				map.put("K_quantity", quantity);
				map.put("K_groupPurchaseId", groupPurchaseId);
				groupDAO.updateGroupPurchaseIntovaty(map);
			}
		}
	}

	//新增团
	public int createGroup(int groupPurchaseId) {
		// TODO Auto-generated method stub
		Group group = new Group();
		group.setGroupPurId(groupPurchaseId);
		groupDAO.createGroup(group);
		System.out.println("团Id"+group.getId());
		return group.getId();
	}

}
