package com.neu.cart.model.dao;

import java.util.List;

import com.neu.cart.model.bean.GiftCard;

public interface GiftCardDAO {

	//查询用户礼品卡
	public List<GiftCard> selectGiftCard(int userId);
}
