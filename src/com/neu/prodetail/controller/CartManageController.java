package com.neu.prodetail.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.service.CartManageService;

@Controller
public class CartManageController {
	
	@Autowired
	private CartManageService cartService;
	//�û���¼ʱ��ʼ��LocalStorage
	@RequestMapping(value="/updateCart/{cid}", method=RequestMethod.POST)
	@ResponseBody
	public List<CartProductInfo> updateCart(@PathVariable int cid,@RequestBody CartProductInfo[] localStorageInfo) {
		
		System.out.println(cid);
		
		return cartService.updateByLocal(cid,localStorageInfo);
	}
	//�û��ѵ�¼���
	@RequestMapping(value="/addProductToCart/{cid}/{proId}", method=RequestMethod.POST)
	@ResponseBody
	public List<CartProductInfo> addProductToCart(@PathVariable int cid,@PathVariable int proId) {
		
		System.out.println(cid);
		List<CartProductInfo> cartlist = new ArrayList<CartProductInfo>();
		
		cartlist = cartService.addProductToCart(cid, proId);
		return cartlist;
	}
	//�û�δ��¼�����ӹ��ﳵ
	@RequestMapping(value="/getCartInfoByProId/{proId}", method=RequestMethod.POST)
	@ResponseBody
	public List<CartProductInfo> getCartInfoByProId(@PathVariable int proId,@RequestBody(required = false) CartProductInfo[] localStorageInfo) {
		
		List<CartProductInfo> cartlist = new ArrayList<CartProductInfo>();
		System.out.println(proId);
		if(localStorageInfo == null) {
			System.out.println("��Ϊ��");
			cartlist = cartService.updateLocalByProId(proId);
		}else {
			cartlist = cartService.updateLocalByProId(proId,localStorageInfo);
		}
		return cartlist;
	}
}
