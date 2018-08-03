package com.neu.prodetail.model.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.dao.ProductDAO;


@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDao;
	public List<CartProductInfo> getProductByCid(int cid) {
		// TODO Auto-generated method stub
		List<CartProductInfo> Cart = productDao.getProductByCid(cid);
		for(CartProductInfo c : Cart) {
			c.setIsChecked(false);
			c.setIsLiked("0");
		}
		return Cart;
	}
	public List<Product> getAllProductByComtyId(int comtyId) {
		// TODO Auto-generated method stub

		return productDao.getAllProductByComtyId(comtyId);
	}
	public List<Product> getProductInfoByComttyId(int comttyId) {
		// TODO Auto-generated method stub
		
		return productDao.getProductInfoByComttyId(comttyId);
	}

	
	
}
