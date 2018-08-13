package com.neu.groupbuy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.service.GroupOrderService;


@Controller
@RequestMapping(value="assets",method={RequestMethod.POST,RequestMethod.GET})
public class GroupOrderController {
		
		@Autowired
		private GroupOrderService groupOrderService;
		
		private int groupIdAll;//需要用到、全局变量，团Id
		
		//查询团购商品信息
		@RequestMapping("selectGroupPurchaseByGroupId")
		public@ResponseBody Group selectGroupPurchaseByGroupId(int groupId) {
				
				groupIdAll = groupId;
				System.out.println("进入Controller");
				
				Group group = groupOrderService.selectGroupPurchaseByGroupId(groupId);
				System.out.println(group.getId()+"   "+group.getGroupPurchase().getGroupPrice()+"   "+group.getGroupPurchase().getProduct().getProName());
				return group;
		}
		
		//下单
		@RequestMapping("createGroupOrder")
		public@ResponseBody int createGroupOrder(int addressnum,int quantity,float account,String distributionTime,String invoice,int customerId) {
			
			System.out.println("进入下单Controller");
			GroupOrder groupOrder = new GroupOrder();
			groupOrder.setCustomerId(customerId);
			groupOrder.setGroupId(groupIdAll);
			groupOrder.setAddressId(addressnum);
			groupOrder.setQuantity(quantity);
			groupOrder.setAccount(account);
			groupOrder.setDistributionTime(distributionTime);
			groupOrder.setInvoiceType(invoice);
			
			groupOrderService.createGroupOrder(groupOrder);
			
			return groupOrder.getId();
			
		}
		
		//订单支付
		@RequestMapping("updataGroupOrderPayment")
		public@ResponseBody String updataGroupOrderPayment(String groupOrderId,String payment,boolean type) {
			System.out.println("进入支付Controller");
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("K_groupOrderId", groupOrderId);
			map.put("K_payment", payment);
			map.put("type", type);
			String result =groupOrderService.updataGroupOrderPayment(map);
			System.out.println(result);
			return result;
		}
		
		//退订订单
		@RequestMapping("quitGroupOrder")
		public@ResponseBody String quitGroupOrder(int groupOrderId) {
			System.out.println("进入退订Controller");
			groupOrderService.quitGroupOrder(groupOrderId);
			String result = "退订成功";
			return result;
			
		}
		
		//申请退款
		@RequestMapping("refund")
		public@ResponseBody String refund(int groupOrderId) {
			System.out.println("进入申请退款Controller");
			groupOrderService.refund(groupOrderId);
			String result = "退款成功";
			return result;
			
		}
		
		//确认收货
		@RequestMapping("confirmationOfreceipt")
		public@ResponseBody String confirmationOfreceipt(int groupOrderId) {
			System.out.println("进入确认收货Controller");
			String result = groupOrderService.confirmationOfreceipt(groupOrderId);
			return result;
		}

//个人中心中团购订单管理模块		
		//查询团购订单或者全部有效订单
		@RequestMapping("getGroupOrder")
		public@ResponseBody List<GroupOrder> getGroupOrder(int customerId) {
			
			System.out.println("进入查询团购订单Controller");
			List<GroupOrder> list = groupOrderService.getGroupOrder(customerId);
			return list;	
		}
		
		//查询未支付团购订单
		@RequestMapping("getNotPayGroupOrder")
		public@ResponseBody List<GroupOrder> getNotPayGroupOrder(int customerId) {
			System.out.println("进入查询未支付团购订单Controller");
			List<GroupOrder> list = groupOrderService.getNotPayGroupOrder(customerId);
			return list;
		}
		
		//查询待收货团购订单
		@RequestMapping("getReceivingGroupOrder")
		public@ResponseBody List<GroupOrder> getReceivingGroupOrder(int customerId){
			System.out.println("进入查询待收货团购订单Controller");
			List<GroupOrder> list = groupOrderService.getReceivingGroupOrder(customerId);
			return list;
		}
		
		//查询已关闭团购订单
		@RequestMapping("getClosedGroupOrder")
		public@ResponseBody  List<GroupOrder> getClosedGroupOrder(int customerId){
			System.out.println("进入查询已关闭团购订单Controller");
			List<GroupOrder> list = groupOrderService.getClosedGroupOrder(customerId);
			return list;
		}
		
		//依据输入条件查询
		@RequestMapping("selectGroupOrderByInput")
		public@ResponseBody List<GroupOrder> selectGroupOrderByInput(String customerId,String result){
			System.out.println("进入输入条件查询Controller");
			System.out.println(customerId+"   "+result);
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("K_customerId", customerId);
			map.put("K_result", result);
			List <GroupOrder> list = groupOrderService.selectGroupOrderByInput(map);
			
			for(GroupOrder groupOrder:list) {
				System.out.println("团购订单Id:"+groupOrder.getId()+"    收货人:"+groupOrder.getAddress().getHost());
			}
			return list;		
		}
		
		//通过团购订单Id查询团购订单详情
		@RequestMapping("selectGroupOrderDetailById")
		public@ResponseBody GroupOrder selectGroupOrderDetailById(int groupOrderId) {
			System.out.println("进入查询团购订单详情Controller");
			System.out.println(groupOrderId);
			GroupOrder groupOrder = groupOrderService.selectGroupOrderDetailById(groupOrderId);
			System.out.println(groupOrder.getAddress().getAddressDetail());
			return groupOrder;
		}
}
