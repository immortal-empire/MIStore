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
		
		private int groupIdAll;//��Ҫ�õ���ȫ�ֱ�������Id
		
		//��ѯ�Ź���Ʒ��Ϣ
		@RequestMapping("selectGroupPurchaseByGroupId")
		public@ResponseBody Group selectGroupPurchaseByGroupId(int groupId) {
				
				groupIdAll = groupId;
				System.out.println("����Controller");
				
				Group group = groupOrderService.selectGroupPurchaseByGroupId(groupId);
				System.out.println(group.getId()+"   "+group.getGroupPurchase().getGroupPrice()+"   "+group.getGroupPurchase().getProduct().getProName());
				return group;
		}
		
		//�µ�
		@RequestMapping("createGroupOrder")
		public@ResponseBody int createGroupOrder(int addressnum,int quantity,float account,String distributionTime,String invoice,int customerId) {
			
			System.out.println("�����µ�Controller");
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
		
		//����֧��
		@RequestMapping("updataGroupOrderPayment")
		public@ResponseBody String updataGroupOrderPayment(String groupOrderId,String payment,boolean type) {
			System.out.println("����֧��Controller");
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("K_groupOrderId", groupOrderId);
			map.put("K_payment", payment);
			map.put("type", type);
			String result =groupOrderService.updataGroupOrderPayment(map);
			System.out.println(result);
			return result;
		}
		
		//�˶�����
		@RequestMapping("quitGroupOrder")
		public@ResponseBody String quitGroupOrder(int groupOrderId) {
			System.out.println("�����˶�Controller");
			groupOrderService.quitGroupOrder(groupOrderId);
			String result = "�˶��ɹ�";
			return result;
			
		}
		
		//�����˿�
		@RequestMapping("refund")
		public@ResponseBody String refund(int groupOrderId) {
			System.out.println("���������˿�Controller");
			groupOrderService.refund(groupOrderId);
			String result = "�˿�ɹ�";
			return result;
			
		}
		
		//ȷ���ջ�
		@RequestMapping("confirmationOfreceipt")
		public@ResponseBody String confirmationOfreceipt(int groupOrderId) {
			System.out.println("����ȷ���ջ�Controller");
			String result = groupOrderService.confirmationOfreceipt(groupOrderId);
			return result;
		}

//�����������Ź���������ģ��		
		//��ѯ�Ź���������ȫ����Ч����
		@RequestMapping("getGroupOrder")
		public@ResponseBody List<GroupOrder> getGroupOrder(int customerId) {
			
			System.out.println("�����ѯ�Ź�����Controller");
			List<GroupOrder> list = groupOrderService.getGroupOrder(customerId);
			return list;	
		}
		
		//��ѯδ֧���Ź�����
		@RequestMapping("getNotPayGroupOrder")
		public@ResponseBody List<GroupOrder> getNotPayGroupOrder(int customerId) {
			System.out.println("�����ѯδ֧���Ź�����Controller");
			List<GroupOrder> list = groupOrderService.getNotPayGroupOrder(customerId);
			return list;
		}
		
		//��ѯ���ջ��Ź�����
		@RequestMapping("getReceivingGroupOrder")
		public@ResponseBody List<GroupOrder> getReceivingGroupOrder(int customerId){
			System.out.println("�����ѯ���ջ��Ź�����Controller");
			List<GroupOrder> list = groupOrderService.getReceivingGroupOrder(customerId);
			return list;
		}
		
		//��ѯ�ѹر��Ź�����
		@RequestMapping("getClosedGroupOrder")
		public@ResponseBody  List<GroupOrder> getClosedGroupOrder(int customerId){
			System.out.println("�����ѯ�ѹر��Ź�����Controller");
			List<GroupOrder> list = groupOrderService.getClosedGroupOrder(customerId);
			return list;
		}
		
		//��������������ѯ
		@RequestMapping("selectGroupOrderByInput")
		public@ResponseBody List<GroupOrder> selectGroupOrderByInput(String customerId,String result){
			System.out.println("��������������ѯController");
			System.out.println(customerId+"   "+result);
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("K_customerId", customerId);
			map.put("K_result", result);
			List <GroupOrder> list = groupOrderService.selectGroupOrderByInput(map);
			
			for(GroupOrder groupOrder:list) {
				System.out.println("�Ź�����Id:"+groupOrder.getId()+"    �ջ���:"+groupOrder.getAddress().getHost());
			}
			return list;		
		}
		
		//ͨ���Ź�����Id��ѯ�Ź���������
		@RequestMapping("selectGroupOrderDetailById")
		public@ResponseBody GroupOrder selectGroupOrderDetailById(int groupOrderId) {
			System.out.println("�����ѯ�Ź���������Controller");
			System.out.println(groupOrderId);
			GroupOrder groupOrder = groupOrderService.selectGroupOrderDetailById(groupOrderId);
			System.out.println(groupOrder.getAddress().getAddressDetail());
			return groupOrder;
		}
}
