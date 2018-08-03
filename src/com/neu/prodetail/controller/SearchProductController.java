package com.neu.prodetail.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

@Controller
public class SearchProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/getProductInCart/{cid}", method=RequestMethod.POST)
	@ResponseBody
	public List<CartProductInfo> getProductByCid(@PathVariable int cid) {
		
		System.out.println(cid);
		return productService.getProductByCid(cid);		
	}
	
	@RequestMapping(value="/getProductInfoByComttyId/{comttyId}", method=RequestMethod.POST)
	@ResponseBody
	public List<Product> getProductInfoByComttyId(@PathVariable int comttyId) {
		
		return productService.getProductInfoByComttyId(comttyId);		
	}
	
}
