package com.neu.cart.model.dao;

import java.util.Map;

public interface FavorDAO {

	//�ղ�ϲ������Ʒ
	public void insertFavor(Map<String,Object> map);
	//�޸��ղص���Ʒ��״̬
	public void updateFavorStatus(Map<String,Object> map);
	//��ѯ�ղص���Ʒ
	public int selectFavor(Map<String,Object> map);
}
