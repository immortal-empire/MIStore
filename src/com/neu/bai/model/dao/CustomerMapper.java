package com.neu.bai.model.dao;

import org.apache.ibatis.annotations.Param;

import com.neu.util.model.bean.Customer;


public interface CustomerMapper {
	//�������Ϣ
	public Customer selectCustomerByCid(int cid);
	//�޸ĸ�����Ϣ
	public void updateCustomer(@Param("cid")int cid,@Param("cname")String cname,@Param("gender")String gender);
	//�޸�����
	public void changePassword(@Param("cid")int cid,@Param("newPassword")String password);
	//��֤�����Ƿ���ȷ
	public String contrastPassword(int cid);
	//�޸ĵ绰
	public void changephone(@Param("cid")int cid,@Param("phone")String phone);
	
}
