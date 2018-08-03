package com.neu.prodetail.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.bean.ShoppingCart;
import com.neu.prodetail.model.dao.ProductDAO;
import com.neu.prodetail.model.dao.ShoppingCartDAO;

@Service
public class CartManageService {
	
	@Autowired
	private ShoppingCartDAO cartDao;
	@Autowired
	private ProductDAO productDao;
	
	public List<CartProductInfo> updateByLocal(int cid, CartProductInfo[] localStorageInfo) {
		
		ShoppingCart s = new ShoppingCart();
		for(CartProductInfo c : localStorageInfo) {
			//�ö��������idΪnull����һ��Ϊ�û�δ��¼ʱ�����
			if(c.getId() != null) {
				cartDao.updateProNumInCart(c.getId(),c.getQuantity());
			}else {

				s.setcId(cid);
				s.setProId(c.getProId());
				s.setProductStatus(c.getProductStatus());
				s.setPrice(c.getPrice());
				s.setQuantity(c.getQuantity());
				s.setIslike(c.getIsLiked());
				s.setPurchaseType(c.getPurchaseType());
				s.setAddTime(c.getAddTime());
				cartDao.insertProIntoCart(s);
			}
		}
		List<CartProductInfo> Cart = productDao.getProductByCid(cid);
		for(CartProductInfo c : Cart) {
			c.setIsChecked(false);
			c.setIsLiked("0");
		}
		return Cart;
	}
	

	public List<CartProductInfo> addProductToCart(int cid, int proId) {
		// TODO Auto-generated method stub		
		Timestamp d = new Timestamp(System.currentTimeMillis());//�õ���ǰʱ�� 
		Product p = new Product();
		ShoppingCart result = new ShoppingCart();
		ShoppingCart s = new ShoppingCart();
		//�жϸ��û��Ĺ��ﳵ���Ƿ��и���Ʒ
		result = cartDao.selectProductInCartBycid(cid, proId);
	
		if(result == null) {
			p = productDao.getCartInfoByProId(proId);
			s.setcId(cid);
			s.setProId(p.getProId());
			s.setProductStatus("1");
			s.setPrice(p.getSellingPrice());
			s.setQuantity(1);
			s.setIslike("0");
			s.setPurchaseType("0");
			s.setAddTime(d);
			cartDao.insertProIntoCart(s);
		} else {
			cartDao.addProNumInCart(result.getId());
		}
		
				
		List<CartProductInfo> Cart = productDao.getProductByCid(cid);
		for(CartProductInfo c : Cart) {
			c.setIsChecked(false);
		}
		return Cart;
	}
	
	public List<CartProductInfo> updateLocalByProId(int proId) {
		// TODO Auto-generated method stub
		Timestamp d = new Timestamp(System.currentTimeMillis());//�õ���ǰʱ�� 
		List<CartProductInfo> cartlist = new ArrayList<CartProductInfo>();
		Product p = new Product();
		CartProductInfo cartInfo = new CartProductInfo();
		p = productDao.getCartInfoByProId(proId);
		cartInfo.setProId(p.getProId());
		cartInfo.setProductStatus("1");
		cartInfo.setPrice(p.getSellingPrice());
		cartInfo.setQuantity(1);
		cartInfo.setIsLiked("0");
		cartInfo.setIsChecked(false);
		cartInfo.setPurchaseType("0");
		cartInfo.setAddTime(d);//��������ʱ��
		cartInfo.setProName(p.getProName());
		cartInfo.setPicture(p.getPicture());
		cartInfo.setInventory(p.getInventory());
		cartlist.add(cartInfo);
		return cartlist;
	}
	
	public List<CartProductInfo> updateLocalByProId(int proId, CartProductInfo[] localStorageInfo) {
		// TODO Auto-generated method stub
		Timestamp d = new Timestamp(System.currentTimeMillis());//�õ���ǰʱ�� 
		List<CartProductInfo> cartlist = new ArrayList<CartProductInfo>();
		Product p = new Product();
		CartProductInfo cartInfo = new CartProductInfo();
		boolean flag = false;
		if(localStorageInfo.length != 0) {
			for(CartProductInfo c : localStorageInfo) {
				
				if(c.getProId() == proId) {
					c.setQuantity(c.getQuantity()+1);
					cartlist.add(c);
					flag = true;
				}else {
					cartlist.add(c);
				}
			}
			//����Ʒ����ԭ��LocalStorage��,����proId��ȡ��Ʒ����Ϣ
			//CartProductInfo��id�Լ�cid������ֵ���û���¼ʱһͬ��ֵ
			if(!flag) {
				p = productDao.getCartInfoByProId(proId);
				cartInfo.setProId(p.getProId());
				cartInfo.setProductStatus("1");
				cartInfo.setPrice(p.getSellingPrice());
				cartInfo.setQuantity(1);
				cartInfo.setIsLiked("0");
				cartInfo.setIsChecked(false);
				cartInfo.setPurchaseType("0");
				cartInfo.setAddTime(d);//��������ʱ��
				cartInfo.setProName(p.getProName());
				cartInfo.setPicture(p.getPicture());
				cartInfo.setInventory(p.getInventory());
				cartlist.add(cartInfo);
			}
		}
		return cartlist;
	}

}
