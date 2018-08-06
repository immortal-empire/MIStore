package com.neu.cart.model.dao;


import java.util.Map;

import com.neu.cart.model.bean.ShoppingCart;


public interface ShoppingCartDAO {
	
	//ɾ�����ﳵ����Ʒ(��״̬�޸�Ϊ��0��),�޸Ĺ��ﳵ����������Ʒ״̬
	public void updateProductStatus(Map<String,Object> map);
	//�޸Ĺ�����Ʒ������
	public void updateQuantity(Map<String,Object> map);
	//�޸Ĺ�����Ʒ��isLiked����
	public void updateFavor(Map<String,Object> map);
	//��ѯ������Ʒ�Ŀ����
	public int selectInventory(int proId);
}
