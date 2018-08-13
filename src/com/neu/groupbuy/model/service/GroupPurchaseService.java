package com.neu.groupbuy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.groupbuy.model.bean.GroupPurchase;
import com.neu.groupbuy.model.dao.GroupPurchaseDAO;



@Service
public class GroupPurchaseService {
	
	@Autowired
	private GroupPurchaseDAO groupPurchaseDAO;
	
	public List<GroupPurchase> selectGroupPurchase() {
		// TODO Auto-generated method stub
		return groupPurchaseDAO.selectGroupPurchase();
	}

}
