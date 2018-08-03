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
 * ��������search_product.html������
 * ��Ҫ��������Ʒ������в�ѯ������
 * @author ������
 *
 */
@Controller
public class AllCategoryController {
 
	@Autowired
	private ProductService productService;
	 
	/**
	 * AllProductCategory()
	 * ���ڲ�ѯ������Ʒ�����µ���Ʒ
	 * @return Map<String,List<Product>> key:һ������ID,value:��Ʒlist
	 */
	@RequestMapping("all_product_category.action")
	public @ResponseBody Map<String,List<Product>> AllProductCategory(){
		return productService.AllProductCategory();
	}

}
