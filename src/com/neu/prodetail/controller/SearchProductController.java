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
	 * ͨ��������Ĺؼ��֡�ɸѡ�����Ƚ�������,�����ؽ������
	 * @param keyword �ؼ���,�ַ���
	 * @param proType ��Ʒ����,ɸѡ����
	 * @param isAvailable �Ƿ��л�,ɸѡ����
	 * @param userid �û�ID,�����ж���Щ��Ʒ���û������ղص�
	 * @return List<Product>,�õ���Ʒ����,��JSON��ʽ����
	 * @throws IOException
	 * @author ������
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
