package com.neu.groupbuy.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.groupbuy.model.bean.GroupAddress;
import com.neu.groupbuy.model.bean.Group;
import com.neu.groupbuy.model.bean.GroupOrder;
import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.prodetail.model.bean.Product;



public interface GroupOrderDAO {

	//������Id��ѯ�Ź���Ʒ
	public Group selectGroupPurchaseByGroupId(int groupId);

	//��ѯ�Ź���������������Ч����
	public List<GroupOrder> getGroupOrder(int customerId);
	
	//��ѯδ֧�����Ź�����
	public List<GroupOrder> getNotPayGroupOrder(int customerId);
	
	//��ѯ���ջ��Ź�����
	public List<GroupOrder> getReceivingGroupOrder(int customerId);
	
	//��ѯ�ѹر��Ź�����
	public List<GroupOrder> getClosedGroupOrder(int customerId);
	
	//������ѯ
	public List<GroupOrder> selectGroupOrderByInput(Map<String,Object> map);
	
	//�����Ź�����Id��ѯ��������
	public GroupOrder selectGroupOrderDetailById(int groupOrderId);
	
	//�ڲ�ѯ������Ч��������֧�����������ջ��������ѹرն����и���
	public GroupAddress getAddress(int groupOrderId);//��ѯ��ַ��Ϣ
	public Group getGroup(int groupId);//��ѯ��
	public GroupPurchase getGroupPurchase(int groupPurchaseId);//��ѯ�Ź���Ʒ
	public Product getProduct(int productId);//�����Ź���Ʒ�е���Ʒid��ѯ��Ʒ��Ϣ

//�µ�ģ��
	public int createGroupOrder(GroupOrder groupOrder);	
	//��ѯ������������
	public int selectCountOfGroup(int groupId);
	//��ѯĳ���Ź���Ʒ���ƴ������
	public Group GroupPurchaseIdByGroupId(int groupId);
	//��ѯ���ƴ������
	public GroupPurchase selectCountLimitGroupPurchase(int groupPurchaseId);
	//������״̬
	public void updateGroupState(int groupId);
	//���¿��
	public void updataGroupPurchaseResidueSum(Map<String, Object> map);
	//������״̬
	public void updateGroupOrderState(int groupId);
	
	//����֧������
	public void updataGroupOrderPayment(Map<String,Object> map);//����֧����Ϣ
	//����֧������
	public void updateOrderPayment(Map<String,Object> map);
	
	//�˶�
	public void quitGroupOrder(int groupOrderId);

	//�����˿�
	public void refund(int groupOrderId);

	//ȷ���ջ�
	public void confirmationOfreceipt(int groupOrderId);

}
