package com.neu.prodetail.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

/**
 * AllCategoryController
 * 用来接收search_product.html的请求，
 * 主要对所有商品分类进行查询并返回
 * @author 刘星星
 *
 */
@Controller
public class AllCategoryController {
 
	@Autowired
	private ProductService productService;
	 
	/**
	 * AllProductCategory()
	 * 用于查询所有商品分类下的商品
	 * @return Map<String,List<Product>> key:一级分类ID,value:商品list
	 */
	@RequestMapping("all_product_category.action")
	public @ResponseBody Map<String,List<Product>> AllProductCategory(){
		return productService.AllProductCategory();
	}

}
