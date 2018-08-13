package com.neu.groupbuy.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.groupbuy.model.bean.GroupAddress;
import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.prodetail.model.bean.Product;



public interface GroupOrderDAO {

	//根据团Id查询团购商品
	public Group selectGroupPurchaseByGroupId(int groupId);

	//查询团购订单或者所有有效订单
	public List<GroupOrder> getGroupOrder(int customerId);
	
	//查询未支付的团购订单
	public List<GroupOrder> getNotPayGroupOrder(int customerId);
	
	//查询待收货团购订单
	public List<GroupOrder> getReceivingGroupOrder(int customerId);
	
	//查询已关闭团购订单
	public List<GroupOrder> getClosedGroupOrder(int customerId);
	
	//输入框查询
	public List<GroupOrder> selectGroupOrderByInput(Map<String,Object> map);
	
	//根据团购订单Id查询订单详情
	public GroupOrder selectGroupOrderDetailById(int groupOrderId);
	
	//在查询所有有效订单、待支付订单、待收货订单、已关闭订单中复用
	public GroupAddress getAddress(int groupOrderId);//查询地址信息
	public Group getGroup(int groupId);//查询组
	public GroupPurchase getGroupPurchase(int groupPurchaseId);//查询团购商品
	public Product getProduct(int productId);//根据团购商品中的商品id查询商品信息

//下单模块
	public int createGroupOrder(GroupOrder groupOrder);	
	//查询该团现有人数
	public int selectCountOfGroup(int groupId);
	//查询某种团购商品最低拼团人数
	public Group GroupPurchaseIdByGroupId(int groupId);
	//查询最低拼团人数
	public GroupPurchase selectCountLimitGroupPurchase(int groupPurchaseId);
	//更改团状态
	public void updateGroupState(int groupId);
	//更新库存
	public void updataGroupPurchaseResidueSum(Map<String, Object> map);
	//更新团状态
	public void updateGroupOrderState(int groupId);
	
	//订单支付操作
	public void updataGroupOrderPayment(Map<String,Object> map);//更新支付信息
	//订单支付操作
	public void updateOrderPayment(Map<String,Object> map);
	
	//退订
	public void quitGroupOrder(int groupOrderId);

	//申请退款
	public void refund(int groupOrderId);

	//确认收货
	public void confirmationOfreceipt(int groupOrderId);

}
