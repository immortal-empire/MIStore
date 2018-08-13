package com.neu.bai.model.dao;

import org.apache.ibatis.annotations.Param;

import com.neu.bai.model.bean.Gift;

public interface GiftMapper {

	public void addgift(@Param("Cid")int Cid, @Param("Password")String password);//以注解的形式表现
}
