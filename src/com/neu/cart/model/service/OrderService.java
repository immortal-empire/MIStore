package com.neu.cart.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neu.cart.model.bean.Address;
import com.neu.cart.model.bean.GiftCard;
import com.neu.cart.model.bean.Order;
import com.neu.cart.model.bean.ShoppingCart;
import com.neu.cart.model.dao.AddressDAO;
import com.neu.cart.model.dao.GiftCardDAO;
import com.neu.cart.model.dao.OrderDAO;
import com.neu.cart.model.dao.ShoppingCartDAO;

@Service
public class OrderService {

	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private GiftCardDAO giftCardDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private ShoppingCartDAO shoppingCardDAO;
	
	//查询当前用户拥有的地址
	public List<Address> getAddress(int userId) {
		
		List<Address> list = addressDAO.selectAddress(userId);
		for(Address a:list) {
			a.setSelected(false);
		}
		return list;
	}
	
	//更新用户地址（包括新增和修改）
	public String updateAddress(Address address) {
		if(address.getAddressId()==0) {
			addressDAO.insertAddress(address);
		}else {
			addressDAO.updateAddress(address);
		}
		return "地址更新成功！";
	}
	
	//查询当前用户的礼品卡
	public List<GiftCard> getGiftCard(int userId){
		
		List<GiftCard> list = giftCardDAO.selectGiftCard(userId);
		return list;
	}
	
	//保存订单
	public String saveOrder(Order order) {
		Timestamp d = new Timestamp(System.currentTimeMillis());
		order.setStartTime(d);
		order.setOrderStatus("0");
		//保存订单
		orderDAO.insertOrder(order);
		int orderId=order.getOrderId();
		for(int i=0;i<order.getProducts().size();i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderId", orderId);
			map.put("productId", order.getProducts().get(i).getProId());
			map.put("quantity", order.getProducts().get(i).getQuantity());
			map.put("price",  order.getProducts().get(i).getPrice());
			map.put("ifevaluate","0");
			//保存订单相关的商品
			orderDAO.insertOrderItem(map);
			//修改该商品的库存
			orderDAO.updateInventory(map);
			//删除购物车中下完订单的商品
			Map<String,Object> SCmap = new HashMap<String,Object>();
			SCmap.put("Id", order.getProducts().get(i).getId());
			SCmap.put("productStatus", "3");
			shoppingCardDAO.updateProductStatus(SCmap);
		}
		return "订单保存成功!";
		
	}
}
