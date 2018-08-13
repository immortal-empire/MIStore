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
	
	//��ȡ����ʱ�䣬����ϵͳ��ǰʱ�䣬��ѯ������������ʼʱ�䡢����ʱ��������������ʼʱ��
	@RequestMapping("getCurSeckillTime")
	public @ResponseBody List<Date> getSeckillTime(){
		Date now = new Date();
		List<Date> timeInfo = seckillService.getSeckillTime(now);
		return timeInfo;
	}
	
	//����������ʼʱ�䣬��õ���������������Ʒ��Ϣ
	@RequestMapping("getSeckillProducts")
	public @ResponseBody List<SeckillInfo> getSeckillProducts(@RequestBody Date startTime){	
		List<SeckillInfo> seckillInfo = seckillService.getSeckillProducts(startTime);
		return seckillInfo;
	}
	
	//���ݱ��������Ŀ�ʼʱ�䣬��ȡ���������������Ŀ�ʼʱ��
	@RequestMapping("getAllSeckillTime")
	public @ResponseBody List<Date> getAllSeckillTime(@RequestBody Date startTime){
		List<Date> timeList = seckillService.getAllSeckillTime(startTime);
		return timeList;
	}
	
	//�жϵ�ǰ�û����ڸ���Ʒ�Ƿ�����������
	@RequestMapping("isReminded")
	public @ResponseBody boolean isReminded(int seckillId,int userId){
		Remind remind = new Remind();
		remind.setSeckillId(seckillId);
		remind.setCustomerId(userId);
		return seckillService.getRemind(remind);
	}
	
	//���ݵ�ǰ����״̬����������orȡ������
	@RequestMapping("setRemind")
	public  @ResponseBody int setRemind(int seckillId,int userId,int state){
		Remind remind = new Remind();
		remind.setSeckillId(seckillId);
		remind.setCustomerId(userId);
		seckillService.setRemind(remind,state);
		return 0;
	}
	
	//��ɱ��Ʒ
	@RequestMapping("buy")
	public  @ResponseBody int executeSeckill(int seckillId,int userId){
		Date now = new Date();
		int result = seckillService.executeSeckill(seckillId,userId,now);
		System.out.println("�����"+result);
		return result;
	}
}
