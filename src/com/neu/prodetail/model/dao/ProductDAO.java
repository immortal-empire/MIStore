package com.neu.prodetail.model.dao;

import java.util.List;

import com.neu.prodetail.model.bean.Product;

public interface ProductDAO {
	
	public List<Product> getProductByCid(int cid);
}
