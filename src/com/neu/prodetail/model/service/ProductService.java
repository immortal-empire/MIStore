package com.neu.prodetail.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.bean.CommodityOneType;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.dao.ProductDAO;
import com.neu.util.IkSegmentation;


@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;
		
	public List<CartProductInfo> getProductByCid(int cid) {
		List<CartProductInfo> Cart = productDAO.getProductByCid(cid);
		for(CartProductInfo c : Cart) {
			c.setIsChecked(false);
			c.setIsLiked("0");
		}
		return Cart;
	}
	
	public List<Product> getAllProductByComtyId(int comtyId) {
		return productDAO.getAllProductByComtyId(comtyId);
	}
	
	public List<Product> getProductInfoByComttyId(int comttyId) {
		return productDAO.getProductInfoByComttyId(comttyId);
	}

		
	/**
	 * bannerProducts
	 * 轮播侧边栏悬浮层,需要展示商品的信息查询
	 * @param ids 一级分类ID,通过一级分类ID查找下属的商品
	 * @return Map<Integer,List<Product>>,key:一级分类ID,value:以及分类下对应的商品集合
	 * @author 刘星星
	 */
	public Map<Integer,List<Product>> bannerProducts(int[] ids){
		Map<Integer,List<Product>> products = new HashMap<Integer, List<Product>>();
		
		//遍历传入的一级分类数组，针对每个一级分类ID，查出下属的商品
		for(int i=0;i<ids.length;i++){
			List<Product> list = productDAO.getProductsByFirstCategory(ids[i]);
			products.put(ids[i], list);
		}
		return products;		
	}

	/**
	 * dropDownProducts
	 * 导航栏悬浮下拉层,需要展示的商品的信息查询
	 * @param ids 一级分类ID,通过一级分类ID查找下属的商品
	 * @return Map<Integer,List<Product>>,key:一级分类ID,value:以及分类下对应的商品集合
	 * @author 刘星星
	 */
	public Map<Integer, List<Product>> dropDownProducts(int[] ids) {
		Map<Integer,List<Product>> products = new HashMap<Integer, List<Product>>();
		
		//遍历传入的一级分类数组，针对每个一级分类ID，查出下属的商品		
		for(int i=0;i<ids.length;i++){
			List<Product> list = productDAO.getProductsByFirstCategory(ids[i]);
			products.put(ids[i], list);
		}
		return products;	
	}

	/**
	 * AllProductCategory()
	 * 用于查询所有商品分类下的商品，service层
	 * @return Map<String,List<Product>> key:一级分类ID,value:商品list
	 * @author 刘星星
	 */
	public Map<String, List<Product>> AllProductCategory() {
		Map<String,List<Product>> products = new HashMap<String, List<Product>>();
		
		//把现有的所有商品分类查出来
		List<CommodityOneType> firstCategories = productDAO.getFirstCategory();
		
		//针对每个一级分类，查询下属的商品
		for(CommodityOneType o:firstCategories){
			List<Product> list = productDAO.getProductsByFirstCategory(o.getComtyId());
			//将查询结果放进Map
			products.put(o.getComtyName(), list);
		}
		return products;
	}

	/**
	 * getHotCommentProduct
	 * 热评商品板块的商品查询
	 * @return List<Comments>,其中comments对象中包括有评论内容、商品信息
	 * @author 刘星星
	 */
	public List<Comments> getHotCommentProduct() {
		List<Comments> list = new ArrayList<Comments>();
		
		//首先获得top4热评的二级分类商品ID
		List<Integer> secondIds = productDAO.getHotCommSecondCategoryId();
		
		//找出这个分类下，给出评分最高的那一条评论
		for(int secId:secondIds){
			Comments comments = productDAO.getCommentsBySecCateId(secId);
			list.add(comments);
		}

		return list;
	}

	/**
	 * getProTypes
	 * 搜索框中,select下拉框可选的商品分类条件的动态生成
	 * @return Map<Integer, String>,key:分类ID,value:分类名称
	 * @author 刘星星
	 */
	public Map<Integer, String> getProTypes() {
		//获取所有一级分类对象
		List<CommodityOneType> types = productDAO.getFirstCategory();
		
		//改写为Map，只取出需要的两个数据
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		for(CommodityOneType c:types){
			resultMap.put(c.getComtyId(), c.getComtyName());
		}
		return resultMap;
	}

	/**
	 * displayProducts
	 * 对应首页中分商品类别对主要商品展示的功能,提供数据来源
	 * @return Map<String,List<Product>>
	 * @author 刘星星
	 */
	public Map<String, List<Product>> displayProducts() {
		Map<String, List<Product>> resultMap = new HashMap<String, List<Product>>();
		
		//获取一级分类
		List<CommodityOneType> types = productDAO.getFirstCategory();		
		
		/*
		 * 对每个一级分类，查询下属的主要商品
		 * 在SQL中已经制定了数量限制，最多为8个
		 */
		for(CommodityOneType c:types){
			List<Product> list = productDAO.getDisplayProducts(c.getComtyId());
			resultMap.put(c.getComtyName(), list);
		}		
		return resultMap;
	}

	/**
	 * getRecommendProduct
	 * 推荐商品,根据销量推荐人气商品
	 * @param limit 每次推荐的数量限制，主要与前端页面的实际显示效果相关，不能过多或过少
	 * @return
	 * @author 刘星星
	 */
	public List<Product> getRecommendProduct(int limit) {
		return productDAO.getBestSelling(limit);
	}
	
	/**
	 * searchProduct
	 * 通过搜索框的关键字、筛选条件等进行搜索,并返回结果集合
	 * @param keyword 关键词,字符串
	 * @param proType 商品分类,筛选条件
	 * @param isAvailable 是否有货,筛选条件
	 * @param userid 用户ID,用来判断哪些商品是用户曾经收藏的
	 * @return List<Product>,得到商品集合
	 * @throws IOException
	 * @author 刘星星
	 */
	public List<Product> searchProduct(String keyword, int proType, boolean isAvailable, int userid) throws IOException {
		//用Map封装搜索条件，作为Mybatis方法参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("K_keyword", keyword);
		map.put("K_proType", proType);
		map.put("K_isAvailable", isAvailable==true?1:0);
		
		//最后的结果
		List<Product> list = new ArrayList<Product>();
		
		if(keyword==null || ("").equals(keyword)){
			//关键词信息为空，则直接根据另外两个条件查
			list = productDAO.getSearchResult(map);
		} else{
			//关键词不为空，则需要对关键词进行分词
			List<String> words = IkSegmentation.Seg(keyword);
			System.out.println("分词结果为：");
			
			//对每个子关键词进行查询
			for(String s:words){
				System.out.println(s);
				map.replace("K_keyword", s);
				List<Product> temp = productDAO.getSearchResult(map);
				list.addAll(temp);
			}			
		}
		
		//搜索结果List去重，利用java8 lambda表达式
		List<Integer> ids = new ArrayList<>();//用来临时存储product的id
		list = list.stream().filter(// 过滤去重
	               p -> {
	                   boolean flag = !ids.contains(p.getProId());
	                   ids.add(p.getProId());
	                   return flag;
	               }
	       ).collect(Collectors.toList());

		//传入的用户ID不等于0，则需要判断用户的收藏，判断某个商品是否被收藏了
		if(userid!=0){
			List<Integer> favors = productDAO.getFavorProIds(userid);
			for(Product p:list){
				if(favors.contains(p.getProId())){
					p.setIsFavor(1);
				}
			}
		}
		return list;
	}

	/**
	 * 向favor表中新增收藏记录
	 * @param userid 用户ID
	 * @param proid 要收藏的商品ID
	 * @author 刘星星
	 */
	public void addToFavor(int userid, int proid) {		
		//查询以前收藏过，但取消了的商品
		List<Integer> history = productDAO.getHistoryFavorProIds(userid);
		
		Map<String, Object> favorMap = new HashMap<String, Object>();		
		favorMap.put("K_userid", userid);
		favorMap.put("K_proid", proid);
		
		//如果已经存在，则重新将数据表记录的状态更改为1
		if(history.contains(proid)){
			productDAO.updateFavor(favorMap);
		} else {
		//如果以前未收藏过，新插入一条收藏记录
			productDAO.addNewFavor(favorMap);			
		}
	}

	/**
	 * removeFromFavor
	 * 取消收藏某个商品
	 * @param userid 用户ID
	 * @param proid 商品ID
	 * @author 刘星星
	 */
	public void removeFromFavor(int userid, int proid) {
		Map<String, Object> favorMap = new HashMap<String, Object>();
		favorMap.put("K_userid", userid);
		favorMap.put("K_proid", proid);
		productDAO.removeFromFavor(favorMap);
	}

	/**
	 * getFavors
	 * 根据用户ID，获取他所收藏的商品
	 * @param userid 用户ID
	 * @return List<Product> List封装用户收藏的商品
	 * @author 刘星星
	 */
	public List<Product> getFavors(int userid) {
		return productDAO.getFavorsByUserId(userid);
	}
}

