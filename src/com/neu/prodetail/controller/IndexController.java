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
 * 主要接收,并处理首页中的若干查询请求
 * @author 刘星星
 *
 */
@Controller
public class IndexController {

	@Autowired
	private ProductService productService;
	
	/**
	 * bannerProducts
	 * 轮播侧边栏悬浮层,需要展示商品的信息查询
	 * @param ids 一级分类ID,通过一级分类ID查找下属的商品
	 * @return Map<Integer,List<Product>>,key:一级分类ID,value:以及分类下对应的商品集合
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
	 * 导航栏悬浮下拉层,需要展示的商品的信息查询
	 * @param ids 一级分类ID,通过一级分类ID查找下属的商品
	 * @return Map<Integer,List<Product>>,key:一级分类ID,value:以及分类下对应的商品集合
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
	 * 热评商品板块的商品查询
	 * @return List<Comments>,其中comments对象中包括有评论内容、商品信息
	 */
	@RequestMapping("hot_comment_product.action")
	public @ResponseBody List<Comments> hotCommentProduct(){
		return productService.getHotCommentProduct();
	}
	
	/**
	 * getProTypes
	 * 搜索框中,select下拉框可选的商品分类条件的动态生成
	 * @return Map<Integer, String>,key:分类ID,value:分类名称
	 */
	@RequestMapping("getProTypes.action")
	public @ResponseBody Map<Integer, String> getProTypes(){
		return productService.getProTypes();
	}
	
	/**
	 * displayProducts
	 * 对应首页中分商品类别对主要商品展示的功能,提供数据来源
	 * @return Map<String,List<Product>>
	 */
	@RequestMapping("display_products.action")
	public @ResponseBody Map<String,List<Product>> displayProducts(){
		return productService.displayProducts();
	}
	
	/**
	 * recommendProduct
	 * 推荐商品,根据销量推荐人气商品
	 * @param limit 每次推荐的数量限制，主要与前端页面的实际显示效果相关，不能过多或过少
	 * @return
	 */
	@RequestMapping("recommend.action")
	public @ResponseBody List<Product> recommendProduct(int limit){
		return productService.getRecommendProduct(limit);
	}
}
