package com.neu.bai.model.dao;

import org.apache.ibatis.annotations.Param;

import com.neu.util.model.bean.Customer;


public interface CustomerMapper {
	//查个人信息
	public Customer selectCustomerByCid(int cid);
	//修改个人信息
	public void updateCustomer(@Param("cid")int cid,@Param("cname")String cname,@Param("gender")String gender);
	//修改密码
	public void changePassword(@Param("cid")int cid,@Param("newPassword")String password);
	//验证密码是否正确
	public String contrastPassword(int cid);
	//修改电话
	public void changephone(@Param("cid")int cid,@Param("phone")String phone);
	
}
