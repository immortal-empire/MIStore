package com.neu.prodetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

/**
 * FavorController
 * ��Ҫ���չ�����Ʒ�ղص�����
 * 1���ղ�ĳ����Ʒ
 * 2��ȡ���ղ�
 * 3�������û�ID��ȡ���������ղ���Ʒ
 * @author ������
 *
 */
@Controller
public class FavorController {
	@Autowired
	private ProductService productService;
	
	/**
	 * addToFavor
	 * ��favor���������ղؼ�¼
	 * @param userid �û�ID
	 * @param proid Ҫ�ղص���ƷID
	 */
	@RequestMapping("addToFavor.action")
	public void addToFavor(int userid,int proid){
		productService.addToFavor(userid,proid);
	}
	
	/**
	 * removeFromFavor
	 * ȡ���ղ�ĳ����Ʒ
	 * @param userid �û�ID
	 * @param proid ��ƷID
	 */
	@RequestMapping("removeFromFavor.action")
	public void removeFromFavor(int userid,int proid){
		productService.removeFromFavor(userid,proid);
	}
	
	/**
	 * getFavors
	 * @param userid �û�ID
	 * @return List<Product> List��װ�û��ղص���Ʒ
	 * ����ResponseBodyע�⽫���ת��JSON��ʽ����
	 */
	@RequestMapping("getFavors.action")
	public @ResponseBody List<Product> getFavors(int userid){
		return productService.getFavors(userid);		
	}
	
}
