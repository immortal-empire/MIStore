package com.neu.bai.model.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.neu.bai.model.dao.CustomerMapper;
import com.neu.bai.model.dao.GiftMapper;
import com.neu.util.model.bean.Customer;

@Service
public class Peoservice {

	@Autowired
	private CustomerMapper customermapper;
	
	public Customer selectCustomerByCid(int cid) {
		return customermapper.selectCustomerByCid(cid);
	} 
	
	public void updateCustomer(int cid,String cname,String gender) {
		System.out.println(cname);
		customermapper.updateCustomer(cid, cname, gender);
	}
	
	public void changephone(int cid,String phone) {
		customermapper.changephone(cid, phone);
	}

	public String changePassword(int cid,String newPassword,String oldPassword) {
		// TODO Auto-generated method stub
		String old = customermapper.contrastPassword(cid);
		if(old.equals(oldPassword)){
			
		
			customermapper.changePassword(cid, newPassword);
			return "ÐÞ¸Ä³É¹¦";
		}
		else {
			return "ÃÜÂë´íÎó";
		}
		
	}
	@Autowired
	private GiftMapper giftmapper;
	public void addgift(int Cid,String password) {
		giftmapper.addgift(Cid, password);
	}	
				
	}
	
//}
