package com.neu.prodetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

@Controller
public class GetComttyProduct {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/getAllProductByComtyId/{comtyId}", method=RequestMethod.POST)
	@ResponseBody
	public List<Product> getAllProductByComtyId(@PathVariable int comtyId) {
		System.out.println(comtyId);
		return productService.getAllProductByComtyId(comtyId);
	}
}
