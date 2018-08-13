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
	
	//��ѯȫ������ƴ����
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
	
	//ͨ����Id��ѯ��Ʒ
	public GroupPurchase selectGroupPurchaseById(int groId) {
		// TODO Auto-generated method stub
		System.out.println(groId);
		return groupDAO.selectGroupPurchaseById(groId);
	}

	//��ѯ��ǰ����ƴ�ŵ�����
	public int selectNumOfPerson(int groupPurchaseId) {
		// TODO Auto-generated method stub
		int number=0;
		int num=0;
		List<Group> list = groupDAO.selectGroup(groupPurchaseId);
		for(Group group:list) {
			num = groupDAO.selectNumOfPerson(group.getId());
			number+=num;
		}
		System.out.println("��ǰ��"+number+"����ƴ��");
		return number;
	}

	//������״̬
	public void updateGroupStateAuto(int groupPurchaseId) {
		// TODO Auto-generated method stub
		groupDAO.updateGroupStateAuto(groupPurchaseId);
		
		//�����ʧЧ����
		List<Group> list = groupDAO.selectUselessGroup(groupPurchaseId);
		
		//��ʧЧ�����еĶ�����״̬�ĳ����˿�
		for(Group group:list) {
			int groupId = group.getId();
			groupDAO.updateGroupOrderState(groupId);
			//���Ҹö����е����ж���
			List<GroupOrder> uselessGroupOrderlist = groupDAO.selectGroupOrderByGroupId(groupId);
			for(GroupOrder groupOrder:uselessGroupOrderlist) {
				int quantity=groupOrder.getQuantity();
				//���¿��
				System.out.println("�޸ĵĿ��Ϊ"+quantity);
				Map<String , Object> map = new HashMap<String , Object>();
				map.put("K_quantity", quantity);
				map.put("K_groupPurchaseId", groupPurchaseId);
				groupDAO.updateGroupPurchaseIntovaty(map);
			}
		}
	}

	//������
	public int createGroup(int groupPurchaseId) {
		// TODO Auto-generated method stub
		Group group = new Group();
		group.setGroupPurId(groupPurchaseId);
		groupDAO.createGroup(group);
		System.out.println("��Id"+group.getId());
		return group.getId();
	}

}
