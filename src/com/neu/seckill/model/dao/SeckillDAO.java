package com.neu.seckill.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.neu.seckill.model.bean.Remind;
import com.neu.seckill.model.bean.SeckillInfo;
import com.neu.seckill.model.bean.FlashPurchase;

public interface SeckillDAO {
	//���ݵ�ǰϵͳʱ���ȡ�����������ʼʱ��
	public Date getSeckillStartTime(Date now);
	//���ݵ�ǰϵͳʱ���ȡ�������������ʱ��
	public Date getSeckillEndTime(Date now);
	//���ݵ�ǰϵͳʱ���ȡ��һ���������ʼʱ��
	public Date getNextSeckillStartTime(Date now);
	//���ݵ��������ʼʱ�䣬��ȡ����������������Ʒ
	public List<SeckillInfo> getSeckillProducts(Date startTime);
	//���ݱ���������ʼʱ�䣬��ȡ���������������Ŀ�ʼʱ��
	public List<Date> getAllSeckillTime(Date startTime);
	//��ѯ��ǰ������Ʒ�Ŀ������
	public int getSeckillRemindNum(int seckillId);
	//��ѯ��ǰ�û��Ը���Ʒ�����Ѽ�¼ID
	public Object getRemindId(Remind remind);
	//�������
	public void addRemind(Remind remind);
	//ȡ������
	public void deleteRemind(Remind remind);
	public void saveSeckillExecution(Map<String,Object> paramMap);
	//��������ID��ѯ��������Ʒ
	public SeckillInfo getSeckillProductById(int seckillId);
	//�ڹ��ﳵ�в��ҵ�ǰ������Ʒ�ļ�¼
	public int findProductInShoppingcart(FlashPurchase flashPurchase);
	//��������Ʒ��ӵ����ﳵ��
	public void addToShoppingcart(FlashPurchase flashPurchase);
	//�޸�������Ʒ���
	public void updateRemainNum(int seckillId);
}
