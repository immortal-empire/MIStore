package com.neu.prodetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.bean.ShoppingCart;
import com.neu.prodetail.model.service.CartManageService;

@Controller
public class CartManageController {
	
	@Autowired
	private CartManageService cartService;
	
	@RequestMapping(value="/updateCart/{cid}", method=RequestMethod.POST)
	@ResponseBody
	public List<Product> updateCart(@PathVariable int cid,@RequestBody CartProductInfo[] localStorageInfo) {
		
		System.out.println(cid);
		
		return cartService.updateByLocal(cid,localStorageInfo);
	}

//	@RequestMapping(value="/updateCart/{cid}", method=RequestMethod.POST)
//	@ResponseBody
//	public List<Product> updateCart(@PathVariable int cid,@RequestBody ShoppingCart[] localStorageInfo) {
//		
//		System.out.println(cid);
//		
//		return cartService.updateByLocal(cid,localStorageInfo);
//	}
}
