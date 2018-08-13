package com.neu.groupbuy.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.util.model.bean.Customer;
import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.bean.GroupPurchase;



public interface GroupDAO {

//查询有效的团
	public List<Group> selectGroup(int groupPurchaseId);
	public List<GroupOrder> selectGroupOrderByGroupId(int groupId);//查询团购订单
	public Customer selectCustomerByGroupOrderId(int customerId);//通过团购订单查询顾客
	
	//查询某一个团购商品
	public GroupPurchase selectGroupPurchaseById(int groId);

	//查询当前拼团人数
	public int selectNumOfPerson(int groupId);
	
	//自动更新团状态
	public void updateGroupStateAuto(int groupPurchaseId);
	//检测已失效的团
	public List<Group> selectUselessGroup(int groupPurchaseId);
	//将失效团中的订单改成已退款
	public void updateGroupOrderState(int groupId);
	
	//新开团并返回Id
	public int createGroup(Group group);
	
	//更新库存
	public void updateGroupPurchaseIntovaty(Map<String, Object> map);
	
}
