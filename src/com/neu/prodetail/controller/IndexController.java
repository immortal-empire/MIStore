package com.neu.prodetail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.service.ProductService;

/**
 * IndexController
 * ��Ҫ����,��������ҳ�е����ɲ�ѯ����
 * @author ������
 *
 */
@Controller
public class IndexController {

	@Autowired
	private ProductService productService;
	
	/**
	 * bannerProducts
	 * �ֲ������������,��Ҫչʾ��Ʒ����Ϣ��ѯ
	 * @param ids һ������ID,ͨ��һ������ID������������Ʒ
	 * @return Map<Integer,List<Product>>,key:һ������ID,value:�Լ������¶�Ӧ����Ʒ����
	 */
	@RequestMapping("banner_product.action")
	public @ResponseBody Map<Integer,List<Product>> bannerProducts(int[] ids){
		Map<Integer,List<Product>> map = new HashMap<Integer, List<Product>>();
		if(ids!=null && ids.length>0){
			map = productService.bannerProducts(ids);
		}
		return map;
	}
	
	/**
	 * dropDown
	 * ����������������,��Ҫչʾ����Ʒ����Ϣ��ѯ
	 * @param ids һ������ID,ͨ��һ������ID������������Ʒ
	 * @return Map<Integer,List<Product>>,key:һ������ID,value:�Լ������¶�Ӧ����Ʒ����
	 */
	@RequestMapping("drop_down.action")
	public @ResponseBody Map<Integer,List<Product>> dropDown(int[] ids){
		Map<Integer,List<Product>> map = new HashMap<Integer, List<Product>>();
		if(ids!=null && ids.length>0){
			map = productService.dropDownProducts(ids);
		}
		return map;
	}
	
	/**
	 * hotCommentProduct
	 * ������Ʒ������Ʒ��ѯ
	 * @return List<Comments>,����comments�����а������������ݡ���Ʒ��Ϣ
	 */
	@RequestMapping("hot_comment_product.action")
	public @ResponseBody List<Comments> hotCommentProduct(){
		return productService.getHotCommentProduct();
	}
	
	/**
	 * getProTypes
	 * ��������,select�������ѡ����Ʒ���������Ķ�̬����
	 * @return Map<Integer, String>,key:����ID,value:��������
	 */
	@RequestMapping("getProTypes.action")
	public @ResponseBody Map<Integer, String> getProTypes(){
		return productService.getProTypes();
	}
	
	/**
	 * displayProducts
	 * ��Ӧ��ҳ�з���Ʒ������Ҫ��Ʒչʾ�Ĺ���,�ṩ������Դ
	 * @return Map<String,List<Product>>
	 */
	@RequestMapping("display_products.action")
	public @ResponseBody Map<String,List<Product>> displayProducts(){
		return productService.displayProducts();
	}
	
	/**
	 * recommendProduct
	 * �Ƽ���Ʒ,���������Ƽ�������Ʒ
	 * @param limit ÿ���Ƽ����������ƣ���Ҫ��ǰ��ҳ���ʵ����ʾЧ����أ����ܹ�������
	 * @return
	 */
	@RequestMapping("recommend.action")
	public @ResponseBody List<Product> recommendProduct(int limit){
		return productService.getRecommendProduct(limit);
	}
}
