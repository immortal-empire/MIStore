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
	 * �ֲ������������,��Ҫչʾ��Ʒ����Ϣ��ѯ
	 * @param ids һ������ID,ͨ��һ������ID������������Ʒ
	 * @return Map<Integer,List<Product>>,key:һ������ID,value:�Լ������¶�Ӧ����Ʒ����
	 * @author ������
	 */
	public Map<Integer,List<Product>> bannerProducts(int[] ids){
		Map<Integer,List<Product>> products = new HashMap<Integer, List<Product>>();
		
		//���������һ���������飬���ÿ��һ������ID�������������Ʒ
		for(int i=0;i<ids.length;i++){
			List<Product> list = productDAO.getProductsByFirstCategory(ids[i]);
			products.put(ids[i], list);
		}
		return products;		
	}

	/**
	 * dropDownProducts
	 * ����������������,��Ҫչʾ����Ʒ����Ϣ��ѯ
	 * @param ids һ������ID,ͨ��һ������ID������������Ʒ
	 * @return Map<Integer,List<Product>>,key:һ������ID,value:�Լ������¶�Ӧ����Ʒ����
	 * @author ������
	 */
	public Map<Integer, List<Product>> dropDownProducts(int[] ids) {
		Map<Integer,List<Product>> products = new HashMap<Integer, List<Product>>();
		
		//���������һ���������飬���ÿ��һ������ID�������������Ʒ		
		for(int i=0;i<ids.length;i++){
			List<Product> list = productDAO.getProductsByFirstCategory(ids[i]);
			products.put(ids[i], list);
		}
		return products;	
	}

	/**
	 * AllProductCategory()
	 * ���ڲ�ѯ������Ʒ�����µ���Ʒ��service��
	 * @return Map<String,List<Product>> key:һ������ID,value:��Ʒlist
	 * @author ������
	 */
	public Map<String, List<Product>> AllProductCategory() {
		Map<String,List<Product>> products = new HashMap<String, List<Product>>();
		
		//�����е�������Ʒ��������
		List<CommodityOneType> firstCategories = productDAO.getFirstCategory();
		
		//���ÿ��һ�����࣬��ѯ��������Ʒ
		for(CommodityOneType o:firstCategories){
			List<Product> list = productDAO.getProductsByFirstCategory(o.getComtyId());
			//����ѯ����Ž�Map
			products.put(o.getComtyName(), list);
		}
		return products;
	}

	/**
	 * getHotCommentProduct
	 * ������Ʒ������Ʒ��ѯ
	 * @return List<Comments>,����comments�����а������������ݡ���Ʒ��Ϣ
	 * @author ������
	 */
	public List<Comments> getHotCommentProduct() {
		List<Comments> list = new ArrayList<Comments>();
		
		//���Ȼ��top4�����Ķ���������ƷID
		List<Integer> secondIds = productDAO.getHotCommSecondCategoryId();
		
		//�ҳ���������£�����������ߵ���һ������
		for(int secId:secondIds){
			Comments comments = productDAO.getCommentsBySecCateId(secId);
			list.add(comments);
		}

		return list;
	}

	/**
	 * getProTypes
	 * ��������,select�������ѡ����Ʒ���������Ķ�̬����
	 * @return Map<Integer, String>,key:����ID,value:��������
	 * @author ������
	 */
	public Map<Integer, String> getProTypes() {
		//��ȡ����һ���������
		List<CommodityOneType> types = productDAO.getFirstCategory();
		
		//��дΪMap��ֻȡ����Ҫ����������
		Map<Integer, String> resultMap = new HashMap<Integer, String>();
		for(CommodityOneType c:types){
			resultMap.put(c.getComtyId(), c.getComtyName());
		}
		return resultMap;
	}

	/**
	 * displayProducts
	 * ��Ӧ��ҳ�з���Ʒ������Ҫ��Ʒչʾ�Ĺ���,�ṩ������Դ
	 * @return Map<String,List<Product>>
	 * @author ������
	 */
	public Map<String, List<Product>> displayProducts() {
		Map<String, List<Product>> resultMap = new HashMap<String, List<Product>>();
		
		//��ȡһ������
		List<CommodityOneType> types = productDAO.getFirstCategory();		
		
		/*
		 * ��ÿ��һ�����࣬��ѯ��������Ҫ��Ʒ
		 * ��SQL���Ѿ��ƶ����������ƣ����Ϊ8��
		 */
		for(CommodityOneType c:types){
			List<Product> list = productDAO.getDisplayProducts(c.getComtyId());
			resultMap.put(c.getComtyName(), list);
		}		
		return resultMap;
	}

	/**
	 * getRecommendProduct
	 * �Ƽ���Ʒ,���������Ƽ�������Ʒ
	 * @param limit ÿ���Ƽ����������ƣ���Ҫ��ǰ��ҳ���ʵ����ʾЧ����أ����ܹ�������
	 * @return
	 * @author ������
	 */
	public List<Product> getRecommendProduct(int limit) {
		return productDAO.getBestSelling(limit);
	}
	
	/**
	 * searchProduct
	 * ͨ��������Ĺؼ��֡�ɸѡ�����Ƚ�������,�����ؽ������
	 * @param keyword �ؼ���,�ַ���
	 * @param proType ��Ʒ����,ɸѡ����
	 * @param isAvailable �Ƿ��л�,ɸѡ����
	 * @param userid �û�ID,�����ж���Щ��Ʒ���û������ղص�
	 * @return List<Product>,�õ���Ʒ����
	 * @throws IOException
	 * @author ������
	 */
	public List<Product> searchProduct(String keyword, int proType, boolean isAvailable, int userid) throws IOException {
		//��Map��װ������������ΪMybatis��������
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("K_keyword", keyword);
		map.put("K_proType", proType);
		map.put("K_isAvailable", isAvailable==true?1:0);
		
		//���Ľ��
		List<Product> list = new ArrayList<Product>();
		
		if(keyword==null || ("").equals(keyword)){
			//�ؼ�����ϢΪ�գ���ֱ�Ӹ�����������������
			list = productDAO.getSearchResult(map);
		} else{
			//�ؼ��ʲ�Ϊ�գ�����Ҫ�Թؼ��ʽ��зִ�
			List<String> words = IkSegmentation.Seg(keyword);
			System.out.println("�ִʽ��Ϊ��");
			
			//��ÿ���ӹؼ��ʽ��в�ѯ
			for(String s:words){
				System.out.println(s);
				map.replace("K_keyword", s);
				List<Product> temp = productDAO.getSearchResult(map);
				list.addAll(temp);
			}			
		}
		
		//�������Listȥ�أ�����java8 lambda���ʽ
		List<Integer> ids = new ArrayList<>();//������ʱ�洢product��id
		list = list.stream().filter(// ����ȥ��
	               p -> {
	                   boolean flag = !ids.contains(p.getProId());
	                   ids.add(p.getProId());
	                   return flag;
	               }
	       ).collect(Collectors.toList());

		//������û�ID������0������Ҫ�ж��û����ղأ��ж�ĳ����Ʒ�Ƿ��ղ���
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
	 * ��favor���������ղؼ�¼
	 * @param userid �û�ID
	 * @param proid Ҫ�ղص���ƷID
	 * @author ������
	 */
	public void addToFavor(int userid, int proid) {		
		//��ѯ��ǰ�ղع�����ȡ���˵���Ʒ
		List<Integer> history = productDAO.getHistoryFavorProIds(userid);
		
		Map<String, Object> favorMap = new HashMap<String, Object>();		
		favorMap.put("K_userid", userid);
		favorMap.put("K_proid", proid);
		
		//����Ѿ����ڣ������½����ݱ��¼��״̬����Ϊ1
		if(history.contains(proid)){
			productDAO.updateFavor(favorMap);
		} else {
		//�����ǰδ�ղع����²���һ���ղؼ�¼
			productDAO.addNewFavor(favorMap);			
		}
	}

	/**
	 * removeFromFavor
	 * ȡ���ղ�ĳ����Ʒ
	 * @param userid �û�ID
	 * @param proid ��ƷID
	 * @author ������
	 */
	public void removeFromFavor(int userid, int proid) {
		Map<String, Object> favorMap = new HashMap<String, Object>();
		favorMap.put("K_userid", userid);
		favorMap.put("K_proid", proid);
		productDAO.removeFromFavor(favorMap);
	}

	/**
	 * getFavors
	 * �����û�ID����ȡ�����ղص���Ʒ
	 * @param userid �û�ID
	 * @return List<Product> List��װ�û��ղص���Ʒ
	 * @author ������
	 */
	public List<Product> getFavors(int userid) {
		return productDAO.getFavorsByUserId(userid);
	}
}

