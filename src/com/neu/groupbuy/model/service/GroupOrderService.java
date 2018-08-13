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

	//������Id��ѯ�Ź���Ʒ
	public Group selectGroupPurchaseByGroupId(int groupId) {
		// TODO Auto-generated method stub
		return groupOrderDAO.selectGroupPurchaseByGroupId(groupId);
	}
	
	//�µ�
	public int createGroupOrder(GroupOrder groupOrder) {
		// TODO Auto-generated method stub
		groupOrderDAO.createGroupOrder(groupOrder);
		int groupId=groupOrder.getGroupId();
		
		//��ѯ���е�����
		int count =  groupOrderDAO.selectCountOfGroup(groupId);
		System.out.println(count);
		//��ѯ�Ź�����Ϳ�ƴ������
		Group group = groupOrderDAO.GroupPurchaseIdByGroupId(groupId);
		int groupPurchaseId=group.getGroupPurId();
		group.setGroupPurchase(groupOrderDAO.selectCountLimitGroupPurchase(groupPurchaseId));
		
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("K_quantity", groupOrder.getQuantity());
		map.put("K_groupPurchaseId", groupPurchaseId);
		groupOrderDAO.updataGroupPurchaseResidueSum(map);//���¿��
		
		System.out.println("����Ź�����"+group.getGroupPurchase().getgMinimumSum());
		int gMinimumSum = group.getGroupPurchase().getgMinimumSum();
		
		//�жϣ�������е������������ƴ���������������״̬�����򲻸���
		if(count == gMinimumSum) {
			//������״̬
			groupOrderDAO.updateGroupState(groupId);
			//���¶���״̬
			groupOrderDAO.updateGroupOrderState(groupId);
		}
		
		return groupOrder.getId();
	}
	
	
	
	//��ѯ�Ź�����
	public List<GroupOrder> getGroupOrder(int customerId) {
		
		//���ݿͻ�Id��ѯ������Ч����
		List<GroupOrder> groupOrderlist = groupOrderDAO.getGroupOrder(customerId);
		
		for(GroupOrder list:groupOrderlist) {
			
			//���ݶ����е�ַId��ѯ��ַ��Ϣ
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
			//���ݶ����е���Id��ѯ��
			int groupId = list.getGroupId();
			list.setGroup(groupOrderDAO.getGroup(groupId));
			
			//������Id��ѯ�Ź���Ʒ
			int groupPurchaseId = list.getGroup().getGroupPurId();
			list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
			//�����Ź���ƷId��ѯ��Ʒ��Ϣ
			int productId = list.getGroup().getGroupPurchase().getProid();
			list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return groupOrderlist;
	}

	public List<GroupOrder> getNotPayGroupOrder(int customerId) {
		
		List<GroupOrder> notPayGroupOrderlist = groupOrderDAO.getNotPayGroupOrder(customerId);
		
		for(GroupOrder list:notPayGroupOrderlist) {
					
					//���ݶ����е�ַId��ѯ��ַ��Ϣ
					int groupOrderAddressId = list.getAddressId();
					list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
					
					//���ݶ����е���Id��ѯ��
					int groupId = list.getGroupId();
					list.setGroup(groupOrderDAO.getGroup(groupId));
					
					//������Id��ѯ�Ź���Ʒ
					int groupPurchaseId = list.getGroup().getGroupPurId();
					list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
					
					//�����Ź���ƷId��ѯ��Ʒ��Ϣ
					int productId = list.getGroup().getGroupPurchase().getProid();
					list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return notPayGroupOrderlist;
	}

	//��ѯ���ջ�����
	public List<GroupOrder> getReceivingGroupOrder(int customerId) {
		// TODO Auto-generated method stub
		List<GroupOrder> receivingGroupOrderlist = groupOrderDAO.getReceivingGroupOrder(customerId);
		
		for(GroupOrder list:receivingGroupOrderlist) {
			
			//���ݶ����е�ַId��ѯ��ַ��Ϣ
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
			//���ݶ����е���Id��ѯ��
			int groupId = list.getGroupId();
			list.setGroup(groupOrderDAO.getGroup(groupId));
			
			//������Id��ѯ�Ź���Ʒ
			int groupPurchaseId = list.getGroup().getGroupPurId();
			list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
			//�����Ź���ƷId��ѯ��Ʒ��Ϣ
			int productId = list.getGroup().getGroupPurchase().getProid();
			list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		
		return receivingGroupOrderlist;
	}

	//��ѯ�ѹر��Ź�����
	public List<GroupOrder> getClosedGroupOrder(int customerId) {
		// TODO Auto-generated method stub
		List<GroupOrder> closedGroupOrderlist = groupOrderDAO.getClosedGroupOrder(customerId);
		
		for(GroupOrder list:closedGroupOrderlist) {
					
				//���ݶ����е�ַId��ѯ��ַ��Ϣ
				int groupOrderAddressId = list.getAddressId();
				list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
					
				//���ݶ����е���Id��ѯ��
				int groupId = list.getGroupId();
				list.setGroup(groupOrderDAO.getGroup(groupId));
					
				//������Id��ѯ�Ź���Ʒ
				int groupPurchaseId = list.getGroup().getGroupPurId();
				list.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
					
				//�����Ź���ƷId��ѯ��Ʒ��Ϣ
				int productId = list.getGroup().getGroupPurchase().getProid();
				list.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		}
		return closedGroupOrderlist;
	}
	
	//��������������ѯ�Ź�����
	public List<GroupOrder> selectGroupOrderByInput(Map<String , Object> map) {
		// TODO Auto-generated method stub
		List<GroupOrder> selectGroupOrderByInputlist = groupOrderDAO.selectGroupOrderByInput(map);
		
		for(GroupOrder list:selectGroupOrderByInputlist) {
			
			//���ݶ����е�ַId��ѯ��ַ��Ϣ
			int groupOrderAddressId = list.getAddressId();
			list.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
	}
		
		return selectGroupOrderByInputlist;
	}
	
	//�����Ź�����Id��ѯ��������
	public GroupOrder selectGroupOrderDetailById(int groupOrderId) {
		// TODO Auto-generated method stub
		GroupOrder groupOrder = groupOrderDAO.selectGroupOrderDetailById(groupOrderId);
		
		//���ݶ����е�ַId��ѯ��ַ��Ϣ
		int groupOrderAddressId = groupOrder.getAddressId();
		groupOrder.setAddress(groupOrderDAO.getAddress(groupOrderAddressId));
			
		//���ݶ����е���Id��ѯ��
		int groupId = groupOrder.getGroupId();
		groupOrder.setGroup(groupOrderDAO.getGroup(groupId));
			
		//������Id��ѯ�Ź���Ʒ
		int groupPurchaseId = groupOrder.getGroup().getGroupPurId();
		groupOrder.getGroup().setGroupPurchase(groupOrderDAO.getGroupPurchase(groupPurchaseId));
			
		//�����Ź���ƷId��ѯ��Ʒ��Ϣ
		int productId = groupOrder.getGroup().getGroupPurchase().getProid();
		groupOrder.getGroup().getGroupPurchase().setProduct(groupOrderDAO.getProduct(productId));
		return groupOrder;
	}
	
	//����֧��
	public String updataGroupOrderPayment(Map<String , Object> map) {
		// TODO Auto-generated method stub
		System.out.println("֧��");
		if((boolean) map.get("type")) {
			groupOrderDAO.updataGroupOrderPayment(map);
		}else {
			System.out.println("����֧��");
			System.out.println();
			groupOrderDAO.updateOrderPayment(map);
		}
		String result = "֧���ɹ�";
		return result;
	}

	//�˶�
	public void quitGroupOrder(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.quitGroupOrder(groupOrderId);
	}

	//�˿�
	public void refund(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.refund(groupOrderId);
	}

	//ȷ���ջ�
	public String confirmationOfreceipt(int groupOrderId) {
		// TODO Auto-generated method stub
		groupOrderDAO.confirmationOfreceipt(groupOrderId);
		String result = "��ȷ���ջ�";
		return result;
	}
	
}
