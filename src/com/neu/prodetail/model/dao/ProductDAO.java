package com.neu.prodetail.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.bean.CommodityOneType;
import com.neu.prodetail.model.bean.Product;

public interface ProductDAO {

	/**
	 * 获取所有商品一级分类
	 * @return List<CommodityOneType>
	 * @author 刘星星
	 */
	List<CommodityOneType> getFirstCategory();
	
	/**
	 * 根据一级分类ID，查询下属的所有商品
	 * @param firstCategory 一级分类ID
	 * @return List<Product>
	 * @author 刘星星
	 */
	List<Product> getProductsByFirstCategory(int firstCategory);
	
	/**
	 * 首先获得top4热评的二级分类商品ID
	 * @return List<Integer> 四个热门的二级分类商品ID
	 * @author 刘星星
	 */
	List<Integer> getHotCommSecondCategoryId();
	
	/**
	 * 获取某二级分类下，评论数最多的商品的评分最高的评论
	 * @param categoryId 二级分类ID
	 * @return 其中comments对象中包括有评论内容、商品信息
	 * @author 刘星星
	 */
	Comments getCommentsBySecCateId(int categoryId);

	/**
	 * 对每个一级分类，查出下属的主要商品
	 * @param comtyId 一级分类ID
	 * @return List<Product>
	 * @author 刘星星
	 */
	List<Product> getDisplayProducts(int comtyId);
	
	/**
	 * 获取热销商品，进行推荐
	 * @param limit
	 * @return List<Product>
	 * @author 刘星星
	 */
	List<Product> getBestSelling(int limit);

	/**
	 * getSearchResult
	 * 实现商品搜索
	 * @param map 搜索条件
	 * K_keyword 关键词
	 * K_proType 商品一级分类
	 * K_isAvailable 是否有货
	 * @return
	 * @author 刘星星
	 */
	List<Product> getSearchResult(Map<String, Object> map);
	
	/**
	 * 根据用户ID，查询出收藏的商品的ID
	 * @param userid 用户ID
	 * @return List<Integer> 商品ID
	 * @author 刘星星
	 */
	List<Integer> getFavorProIds(int userid);
	
	/**
	 * 通过用户ID，查询该用户曾经收藏过，又取消了的商品ID
	 * @param userid 用户ID
	 * @return List<Integer> 曾经收藏过，但取消了的商品的ID
	 * @author 刘星星
	 */
	List<Integer> getHistoryFavorProIds(int userid);

	/**
	 * 新增一条收藏记录
	 * @param favorMap 
	 * key:K_userid value:用户ID
	 * key:K_proid value:商品ID
	 * @author 刘星星
	 */
	void addNewFavor(Map<String, Object> favorMap);
	
	/**
	 * 重新收藏，更新收藏记录状态
	 * @param favorMap 
	 * key:K_userid value:用户ID
	 * key:K_proid value:商品ID
	 * @author 刘星星
	 */
	void updateFavor(Map<String, Object> favorMap);

	/**
	 * 取消收藏商品，将收藏记录状态更改为0
	 * @param favorMap 
	 * key:K_userid value:用户ID
	 * key:K_proid value:商品ID
	 * @author 刘星星
	 */
	void removeFromFavor(Map<String, Object> favorMap);

	/**
	 * getFavorsByUserId
	 * 根据用户ID，获取他所收藏的商品
	 * @param userid 用户ID
	 * @return List<Product> List封装用户收藏的商品
	 * @author 刘星星
	 */
	List<Product> getFavorsByUserId(int userid);

	public List<CartProductInfo> getProductByCid(int cid);

	public Product getCartInfoByProId(int proId);

	public List<Product> getAllProductByComtyId(int comtyId);

	public List<Product> getProductInfoByComttyId(int comttyId);
}

