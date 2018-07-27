package com.neu.prodetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

@Controller
public class SearchProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping("searchProduct.action")
	 public void searchProduct(String keyword,int proType){
		//接收搜索参数:关键字keyword、商品分类:proType
		System.out.println(keyword + " " + proType);
		
		//调用service层
		List<Product> products = productService.searchProduct(keyword,proType);
		
	}
}
