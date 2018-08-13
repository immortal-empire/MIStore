package com.neu.prodetail.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;
import org.springframework.stereotype.Controller;

@Controller
public class SearchProductController {
	
	@Autowired
	private ProductService productService;
		
	/**
	 * searchProduct
	 * 通过搜索框的关键字、筛选条件等进行搜索,并返回结果集合
	 * @param keyword 关键词,字符串
	 * @param proType 商品分类,筛选条件
	 * @param isAvailable 是否有货,筛选条件
	 * @param userid 用户ID,用来判断哪些商品是用户曾经收藏的
	 * @return List<Product>,得到商品集合,以JSON格式返回
	 * @throws IOException
	 * @author 刘星星
	 */
	@RequestMapping("searchProduct.action")
	 public @ResponseBody List<Product> searchProduct(String keyword,int proType,boolean isAvailable,int userid) throws IOException{
		return productService.searchProduct(keyword,proType,isAvailable,userid);
	}
	
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
	
	@RequestMapping(value="/getComttyIdByProId/{proId}", method=RequestMethod.POST)
	@ResponseBody
	public Product getComttyIdByProId(@PathVariable int proId) {
		
		return productService.getComttyIdByProId(proId);		
	}

}
