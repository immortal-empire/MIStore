package com.neu.seckill.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.seckill.model.bean.Remind;
import com.neu.seckill.model.bean.SeckillInfo;
import com.neu.seckill.model.service.SeckillService;

@Controller
public class SeckillController {
	@Autowired
	private SeckillService seckillService;
	
	//获取闪购时间，根据系统当前时间，查询出本期闪购开始时间、结束时间与下期闪购开始时间
	@RequestMapping("getCurSeckillTime")
	public @ResponseBody List<Date> getSeckillTime(){
		Date now = new Date();
		List<Date> timeInfo = seckillService.getSeckillTime(now);
		return timeInfo;
	}
	
	//根据闪购开始时间，获得当期闪购的所有商品信息
	@RequestMapping("getSeckillProducts")
	public @ResponseBody List<SeckillInfo> getSeckillProducts(@RequestBody Date startTime){	
		List<SeckillInfo> seckillInfo = seckillService.getSeckillProducts(startTime);
		return seckillInfo;
	}
	
	//根据本期闪购的开始时间，获取接下来四期闪购的开始时间
	@RequestMapping("getAllSeckillTime")
	public @ResponseBody List<Date> getAllSeckillTime(@RequestBody Date startTime){
		List<Date> timeList = seckillService.getAllSeckillTime(startTime);
		return timeList;
	}
	
	//判断当前用户对于该商品是否设置了提醒
	@RequestMapping("isReminded")
	public @ResponseBody boolean isReminded(int seckillId,int userId){
		Remind remind = new Remind();
		remind.setSeckillId(seckillId);
		remind.setCustomerId(userId);
		return seckillService.getRemind(remind);
	}
	
	//根据当前提醒状态，设置提醒or取消提醒
	@RequestMapping("setRemind")
	public  @ResponseBody int setRemind(int seckillId,int userId,int state){
		Remind remind = new Remind();
		remind.setSeckillId(seckillId);
		remind.setCustomerId(userId);
		seckillService.setRemind(remind,state);
		return 0;
	}
	
	//秒杀商品
	@RequestMapping("buy")
	public  @ResponseBody int executeSeckill(int seckillId,int userId){
		Date now = new Date();
		int result = seckillService.executeSeckill(seckillId,userId,now);
		System.out.println("结果是"+result);
		return result;
	}
}
