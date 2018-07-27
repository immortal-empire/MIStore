package com.neu.prodetail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("banner_product.action")
	public void bannerProduct(int[] ids){
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]);			
		}

	}
	
}
