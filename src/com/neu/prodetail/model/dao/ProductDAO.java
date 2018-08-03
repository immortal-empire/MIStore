package com.neu.prodetail.model.dao;

import java.util.List;
import java.util.Map;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.bean.CommodityOneType;
import com.neu.prodetail.model.bean.Product;

public interface ProductDAO {

	/**
	 * ��ȡ������Ʒһ������
	 * @return List<CommodityOneType>
	 * @author ������
	 */
	List<CommodityOneType> getFirstCategory();
	
	/**
	 * ����һ������ID����ѯ������������Ʒ
	 * @param firstCategory һ������ID
	 * @return List<Product>
	 * @author ������
	 */
	List<Product> getProductsByFirstCategory(int firstCategory);
	
	/**
	 * ���Ȼ��top4�����Ķ���������ƷID
	 * @return List<Integer> �ĸ����ŵĶ���������ƷID
	 * @author ������
	 */
	List<Integer> getHotCommSecondCategoryId();
	
	/**
	 * ��ȡĳ���������£�������������Ʒ��������ߵ�����
	 * @param categoryId ��������ID
	 * @return ����comments�����а������������ݡ���Ʒ��Ϣ
	 * @author ������
	 */
	Comments getCommentsBySecCateId(int categoryId);

	/**
	 * ��ÿ��һ�����࣬�����������Ҫ��Ʒ
	 * @param comtyId һ������ID
	 * @return List<Product>
	 * @author ������
	 */
	List<Product> getDisplayProducts(int comtyId);
	
	/**
	 * ��ȡ������Ʒ�������Ƽ�
	 * @param limit
	 * @return List<Product>
	 * @author ������
	 */
	List<Product> getBestSelling(int limit);

	/**
	 * getSearchResult
	 * ʵ����Ʒ����
	 * @param map ��������
	 * K_keyword �ؼ���
	 * K_proType ��Ʒһ������
	 * K_isAvailable �Ƿ��л�
	 * @return
	 * @author ������
	 */
	List<Product> getSearchResult(Map<String, Object> map);
	
	/**
	 * �����û�ID����ѯ���ղص���Ʒ��ID
	 * @param userid �û�ID
	 * @return List<Integer> ��ƷID
	 * @author ������
	 */
	List<Integer> getFavorProIds(int userid);
	
	/**
	 * ͨ���û�ID����ѯ���û������ղع�����ȡ���˵���ƷID
	 * @param userid �û�ID
	 * @return List<Integer> �����ղع�����ȡ���˵���Ʒ��ID
	 * @author ������
	 */
	List<Integer> getHistoryFavorProIds(int userid);

	/**
	 * ����һ���ղؼ�¼
	 * @param favorMap 
	 * key:K_userid value:�û�ID
	 * key:K_proid value:��ƷID
	 * @author ������
	 */
	void addNewFavor(Map<String, Object> favorMap);
	
	/**
	 * �����ղأ������ղؼ�¼״̬
	 * @param favorMap 
	 * key:K_userid value:�û�ID
	 * key:K_proid value:��ƷID
	 * @author ������
	 */
	void updateFavor(Map<String, Object> favorMap);

	/**
	 * ȡ���ղ���Ʒ�����ղؼ�¼״̬����Ϊ0
	 * @param favorMap 
	 * key:K_userid value:�û�ID
	 * key:K_proid value:��ƷID
	 * @author ������
	 */
	void removeFromFavor(Map<String, Object> favorMap);

	/**
	 * getFavorsByUserId
	 * �����û�ID����ȡ�����ղص���Ʒ
	 * @param userid �û�ID
	 * @return List<Product> List��װ�û��ղص���Ʒ
	 * @author ������
	 */
	List<Product> getFavorsByUserId(int userid);

	public List<CartProductInfo> getProductByCid(int cid);

	public Product getCartInfoByProId(int proId);

	public List<Product> getAllProductByComtyId(int comtyId);

	public List<Product> getProductInfoByComttyId(int comttyId);
}

