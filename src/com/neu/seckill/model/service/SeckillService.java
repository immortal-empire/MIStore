package com.neu.seckill.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.seckill.model.bean.Remind;
import com.neu.seckill.model.bean.SeckillInfo;
import com.neu.seckill.model.bean.FlashPurchase;
import com.neu.seckill.model.dao.SeckillDAO;

@Service
public class SeckillService {
	@Autowired
	private SeckillDAO seckillDAO;
	
	public List<Date> getSeckillTime(Date now){
		List<Date> list = new ArrayList<Date>();
		list.add(seckillDAO.getSeckillStartTime(now));
		list.add(seckillDAO.getSeckillEndTime(now));
		list.add(seckillDAO.getNextSeckillStartTime(now));
		return list;
	}

	public List<SeckillInfo> getSeckillProducts(Date startTime) {
		List<SeckillInfo> list = seckillDAO.getSeckillProducts(startTime);
		for(SeckillInfo info:list){
			info.setRemindNum(seckillDAO.getSeckillRemindNum(info.getSeckillId()));
		}
		return list;
	}
	
	public List<Date> getAllSeckillTime(Date startTime){
		List<Date> list = seckillDAO.getAllSeckillTime(startTime);
		return list;
	}

	//在数据库中查询提醒表，若有提醒记录返回true
	public boolean getRemind(Remind remind) {
		if(seckillDAO.getRemindId(remind)!= null){
			return true;
		}else{
			return false;
		}			
	}
	
	//设置提醒，若状态为0，则添加提醒；状态为1，取消提醒
	public void setRemind(Remind remind, int state) {
		if(state==0)
			seckillDAO.addRemind(remind);
		else if (state==1)
			seckillDAO.deleteRemind(remind);
		else
			System.out.println("setRemind failed");
	}

	public int executeSeckill(int seckillId, int userId, Date now) {
		SeckillInfo product = seckillDAO.getSeckillProductById(seckillId);
		FlashPurchase flashPurchase = new FlashPurchase();
		flashPurchase.setcId(userId);
		flashPurchase.setProId(product.getProductId());
		flashPurchase.setPrice(product.getSeckillPrice());
		flashPurchase.setAddtime(now);
		flashPurchase.setSeckillStartTime(product.getSeckillStartTime());
		flashPurchase.setSeckillEndTime(product.getSeckillEndTime());
		int result = -1;
		if(seckillDAO.findProductInShoppingcart(flashPurchase)>0){
			result = 0;
		}else{
			seckillDAO.addToShoppingcart(flashPurchase);
			seckillDAO.updateRemainNum(seckillId);
			result = 1;
		}	
		return result;
	}
}
