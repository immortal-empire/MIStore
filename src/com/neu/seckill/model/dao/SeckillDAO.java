package com.neu.seckill.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neu.seckill.model.bean.Remind;
import com.neu.seckill.model.bean.SeckillInfo;
import com.neu.seckill.model.bean.FlashPurchase;

public interface SeckillDAO {
	//根据当前系统时间获取本期闪购活动开始时间
	public Date getSeckillStartTime(Date now);
	//根据当前系统时间获取本期闪购活动结束时间
	public Date getSeckillEndTime(Date now);
	//根据当前系统时间获取下一期闪购活动开始时间
	public Date getNextSeckillStartTime(Date now);
	//根据当闪购活动开始时间，获取当期闪购的所有商品
	public List<SeckillInfo> getSeckillProducts(Date startTime);
	//根据本期闪购开始时间，获取接下来四期闪购的开始时间
	public List<Date> getAllSeckillTime(Date startTime);
	//查询当前闪购商品的库存数量
	public int getSeckillRemindNum(int seckillId);
	//查询当前用户对该商品的提醒记录ID
	public Object getRemindId(Remind remind);
	//添加提醒
	public void addRemind(Remind remind);
	//取消提醒
	public void deleteRemind(Remind remind);
	public void saveSeckillExecution(Map<String,Object> paramMap);
	//根据闪购ID查询到闪购商品
	public SeckillInfo getSeckillProductById(int seckillId);
	//在购物车中查找当前闪购商品的记录
	public int findProductInShoppingcart(FlashPurchase flashPurchase);
	//将闪购商品添加到购物车中
	public void addToShoppingcart(FlashPurchase flashPurchase);
	//修改闪购商品库存
	public void updateRemainNum(int seckillId);
}
