package com.neu.prodetail.model.service;

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
	
//	public List<Product> updateByLocal(int cid, ShoppingCart[] localStorageInfo) {
//				
//		for(ShoppingCart c : localStorageInfo) {
//			if(IfInCart(c.getId())) {
//				cartDao.updateProNumInCart(c.getId(),c.getQuantity());
//			}else {
//				cartDao.insertProIntoCart(c);
//			}
//		}
//		return productDao.getProductByCid(cid);
//	}
	
	public List<Product> updateByLocal(int cid, CartProductInfo[] localStorageInfo) {
		
		ShoppingCart s = new ShoppingCart();
		for(CartProductInfo c : localStorageInfo) {
			if(IfInCart(c.getId())) {
				cartDao.updateProNumInCart(c.getId(),c.getQuantity());
			}else {
				s.setId(c.getId());
				s.setcId(c.getcId());
				s.setProId(c.getProId());
				s.setProductStatus(c.getProductStatus());
				s.setPrice(c.getPrice());
				s.setQuantity(c.getQuantity());
				s.setPurchaseType(c.getPurchaseType());
				s.setAddTime(c.getAddTime());
				cartDao.insertProIntoCart(s);
			}
		}
		return productDao.getProductByCid(cid);
	}
	
	public boolean IfInCart(int id) {

		ShoppingCart s = cartDao.selectProductInCart(id);
		if(s != null)
			return true;
		else
			return false;
	}
}
