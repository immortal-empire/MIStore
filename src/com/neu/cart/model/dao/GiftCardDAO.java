package com.neu.cart.model.dao;

import java.util.List;

import com.neu.cart.model.bean.GiftCard;

public interface GiftCardDAO {

	//��ѯ�û���Ʒ��
	public List<GiftCard> selectGiftCard(int userId);
}
