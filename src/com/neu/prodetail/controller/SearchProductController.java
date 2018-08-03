package com.neu.prodetail.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;
import com.neu.prodetail.model.util.IkSegmentation;

@Controller
public class SearchProductController {
	
	@Autowired
	private ProductService productService;
		
	/**
	 * searchProduct
	 * ͨ��������Ĺؼ��֡�ɸѡ�����Ƚ�������,�����ؽ������
	 * @param keyword �ؼ���,�ַ���
	 * @param proType ��Ʒ����,ɸѡ����
	 * @param isAvailable �Ƿ��л�,ɸѡ����
	 * @param userid �û�ID,�����ж���Щ��Ʒ���û������ղص�
	 * @return List<Product>,�õ���Ʒ����,��JSON��ʽ����
	 * @throws IOException
	 */
	@RequestMapping("searchProduct.action")
	 public @ResponseBody List<Product> searchProduct(String keyword,int proType,boolean isAvailable,int userid) throws IOException{
		return productService.searchProduct(keyword,proType,isAvailable,userid);
	}

}
