package com.neu.prodetail.model.dao;

import java.util.List;

import com.neu.prodetail.model.bean.CartProductInfo;
import com.neu.prodetail.model.bean.Product;

public interface ProductDAO {
	
	public List<CartProductInfo> getProductByCid(int cid);

	public Product getCartInfoByProId(int proId);

	public List<Product> getAllProductByComtyId(int comtyId);

	public List<Product> getProductInfoByComttyId(int comttyId);
}
