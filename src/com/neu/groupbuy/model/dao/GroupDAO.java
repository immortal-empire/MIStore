package com.neu.groupbuy.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.util.model.bean.Customer;
import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.bean.GroupPurchase;



public interface GroupDAO {

//��ѯ��Ч����
	public List<Group> selectGroup(int groupPurchaseId);
	public List<GroupOrder> selectGroupOrderByGroupId(int groupId);//��ѯ�Ź�����
	public Customer selectCustomerByGroupOrderId(int customerId);//ͨ���Ź�������ѯ�˿�
	
	//��ѯĳһ���Ź���Ʒ
	public GroupPurchase selectGroupPurchaseById(int groId);

	//��ѯ��ǰƴ������
	public int selectNumOfPerson(int groupId);
	
	//�Զ�������״̬
	public void updateGroupStateAuto(int groupPurchaseId);
	//�����ʧЧ����
	public List<Group> selectUselessGroup(int groupPurchaseId);
	//��ʧЧ���еĶ����ĳ����˿�
	public void updateGroupOrderState(int groupId);
	
	//�¿��Ų�����Id
	public int createGroup(Group group);
	
	//���¿��
	public void updateGroupPurchaseIntovaty(Map<String, Object> map);
	
}
