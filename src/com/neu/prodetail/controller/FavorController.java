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
 * 主要接收关于商品收藏的请求：
 * 1、收藏某个商品
 * 2、取消收藏
 * 3、根据用户ID获取他的所有收藏商品
 * @author 刘星星
 *
 */
@Controller
public class FavorController {
	@Autowired
	private ProductService productService;
	
	/**
	 * addToFavor
	 * 向favor表中新增收藏记录
	 * @param userid 用户ID
	 * @param proid 要收藏的商品ID
	 */
	@RequestMapping("addToFavor.action")
	public void addToFavor(int userid,int proid){
		productService.addToFavor(userid,proid);
	}
	
	/**
	 * removeFromFavor
	 * 取消收藏某个商品
	 * @param userid 用户ID
	 * @param proid 商品ID
	 */
	@RequestMapping("removeFromFavor.action")
	public void removeFromFavor(int userid,int proid){
		productService.removeFromFavor(userid,proid);
	}
	
	/**
	 * getFavors
	 * @param userid 用户ID
	 * @return List<Product> List封装用户收藏的商品
	 * 利用ResponseBody注解将结果转成JSON形式返回
	 */
	@RequestMapping("getFavors.action")
	public @ResponseBody List<Product> getFavors(int userid){
		return productService.getFavors(userid);		
	}
	
}
