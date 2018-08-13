package com.neu.groupbuy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.dao.GroupOrderDAO;



@Service
public class GroupOrderService {
	
	@Autowired
	private GroupOrderDAO groupOrderDAO;

	//根据组Id查询团购商品
	public Group selectGroupPurchaseByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return groupOrderDAO.selectGroupPurchaseByGroupId(groupId);
	}
	
	//下单
	public int createGroupOrder(GroupOrder groupOrder) {
		// TODO Auto-generated method stub
		groupOrderDAO.createGroupOrder(groupOrder);
		int groupId=groupOrder.getGroupId();
		
		//查询团中的人数
		int count =  groupOrderDAO.selectCountOfGroup(groupId);
		System.out.println(count);
		//查询团购中最低可拼团人数
		Group group = groupOrderDAO.GroupPurchaseIdByGroupId(groupId);
		int groupPurchaseId=group.getGroupPurId();
		group.setGroupPurchase(groupOrderDAO.selectCountLimitGroupPurchase(groupPurchaseId));
		
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("K_quantity", groupOrder.getQuantity());
		map.put("K_groupPurchaseId", groupPurchaseId);
		groupOrderDAO.updataGroupPurchaseResidueSum(map);//更新库存
		
		System.out.println("最低团购人数"+group.getGroupPurchase().getgMinimumSum());
		int gMinimumSum = group.getGroupPurchase().getgMinimumSum();
		
		//判断，如果团中的人数够了最低拼团人数，则更改团状态，否则不更改
		if(count == gMinimumSum) {
			//更改团状态
			groupOrderDAO.updateGroupState(groupId);
			//更新订单状态
			groupOrderDAO.updateGroupOrderState(groupId);
		}
		
		return groupOrder.getId();
	}
	
	
	
	//查询团购订单
	public List<GroupOrder> getGroupOrder(int customerId) {
		
		//根据客户Id查询所有有效订单
		List<GroupOrder> groupOrderlist = groupOrderDAO.getGroupOrder(customerId);
		
		for(GroupOrder list:groupOrderlist) {
			
			//根据订单中地址Id查询地址信息
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
			//根据订单中的团Id查询团
			int groupId = list.getGroupId();
			list.setGroup(groupOrderDAO.getGroup(groupId));
			
			//根据团Id查询团购商品
			int groupPurchaseId = list.getGroup().getGroupPurId();
			list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
			//根据团购商品Id查询商品信息
			int productId = list.getGroup().getGroupPurchase().getProid();
			list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return groupOrderlist;
	}

	public List<GroupOrder> getNotPayGroupOrder(int customerId) {
		
		List<GroupOrder> notPayGroupOrderlist = groupOrderDAO.getNotPayGroupOrder(customerId);
		
		for(GroupOrder list:notPayGroupOrderlist) {
					
					//根据订单中地址Id查询地址信息
					int groupOrderAddressId = list.getAddressId();
					list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
					
					//根据订单中的团Id查询团
					int groupId = list.getGroupId();
					list.setGroup(groupOrderDAO.getGroup(groupId));
					
					//根据团Id查询团购商品
					int groupPurchaseId = list.getGroup().getGroupPurId();
					list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
					
					//根据团购商品Id查询商品信息
					int productId = list.getGroup().getGroupPurchase().getProid();
					list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return notPayGroupOrderlist;
	}

	//查询待收货订单
	public List<GroupOrder> getReceivingGroupOrder(int customerId) {
		// TODO Auto-generated method stub
		List<GroupOrder> receivingGroupOrderlist = groupOrderDAO.getReceivingGroupOrder(customerId);
		
		for(GroupOrder list:receivingGroupOrderlist) {
			
			//根据订单中地址Id查询地址信息
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
			//根据订单中的团Id查询团
			int groupId = list.getGroupId();
			list.setGroup(groupOrderDAO.getGroup(groupId));
			
			//根据团Id查询团购商品
			int groupPurchaseId = list.getGroup().getGroupPurId();
			list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
			//根据团购商品Id查询商品信息
			int productId = list.getGroup().getGroupPurchase().getProid();
			list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return receivingGroupOrderlist;
	}

	//查询已关闭团购订单
	public List<GroupOrder> getClosedGroupOrder(int customerId) {
		// TODO Auto-generated method stub
		List<GroupOrder> closedGroupOrderlist = groupOrderDAO.getClosedGroupOrder(customerId);
		
		for(GroupOrder list:closedGroupOrderlist) {
					
				//根据订单中地址Id查询地址信息
				int groupOrderAddressId = list.getAddressId();
				list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
					
				//根据订单中的团Id查询团
				int groupId = list.getGroupId();
				list.setGroup(groupOrderDAO.getGroup(groupId));
					
				//根据团Id查询团购商品
				int groupPurchaseId = list.getGroup().getGroupPurId();
				list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
					
				//根据团购商品Id查询商品信息
				int productId = list.getGroup().getGroupPurchase().getProid();
				list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		return closedGroupOrderlist;
	}
	
	//根据输入条件查询团购订单
	public List<GroupOrder> selectGroupOrderByInput(Map<String , Object> map) {
		// TODO Auto-generated method stub
		List<GroupOrder> selectGroupOrderByInputlist = groupOrderDAO.selectGroupOrderByInput(map);
		
		for(GroupOrder list:selectGroupOrderByInputlist) {
			
			//根据订单中地址Id查询地址信息
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
	}
		
		return selectGroupOrderByInputlist;
	}
	
	//根据团购订单Id查询订单详情
	public GroupOrder selectGroupOrderDetailById(int groupOrderId) {
		// TODO Auto-generated method stub
		GroupOrder groupOrder = groupOrderDAO.selectGroupOrderDetailById(groupOrderId);
		
		//根据订单中地址Id查询地址信息
		int groupOrderAddressId = groupOrder.getAddressId();
		groupOrder.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
		//根据订单中的团Id查询团
		int groupId = groupOrder.getGroupId();
		groupOrder.setGroup(groupOrderDAO.getGroup(groupId));
			
		//根据团Id查询团购商品
		int groupPurchaseId = groupOrder.getGroup().getGroupPurId();
		groupOrder.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
		//根据团购商品Id查询商品信息
		int productId = groupOrder.getGroup().getGroupPurchase().getProid();
		groupOrder.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		return groupOrder;
	}
	
	//订单支付
	public String updataGroupOrderPayment(Map<String , Object> map) {
		// TODO Auto-generated method stub
		System.out.println("支付");
		if((boolean) map.get("type")) {
			groupOrderDAO.updataGroupOrderPayment(map);
		}else {
			System.out.println("正常支付");
			System.out.println();
			groupOrderDAO.updateOrderPayment(map);
		}
		String result = "支付成功";
		return result;
	}

	//退订
	public void quitGroupOrder(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.quitGroupOrder(groupOrderId);
	}

	//退款
	public void refund(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.refund(groupOrderId);
	}

	//确认收货
	public String confirmationOfreceipt(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.confirmationOfreceipt(groupOrderId);
		String result = "已确认收货";
		return result;
	}
	
}
