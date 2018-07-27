package com.neu.prodetail.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.prodetail.model.bean.Product;
import com.neu.prodetail.model.dao.ProductDAO;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDao;
	public List<Product> getProductByCid(int cid) {
		// TODO Auto-generated method stub
		return productDao.getProductByCid(cid);
	}

}
