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
	
	//��ѯ��ǰ�û�ӵ�еĵ�ַ
	public List<Address> getAddress(int userId) {
		
		List<Address> list = addressDAO.selectAddress(userId);
		for(Address a:list) {
			a.setSelected(false);
		}
		return list;
	}
	
	//�����û���ַ�������������޸ģ�
	public String updateAddress(Address address) {
		if(address.getAddressId()==0) {
			addressDAO.insertAddress(address);
		}else {
			addressDAO.updateAddress(address);
		}
		return "��ַ���³ɹ���";
	}
	
	//��ѯ��ǰ�û�����Ʒ��
	public List<GiftCard> getGiftCard(int userId){
		
		List<GiftCard> list = giftCardDAO.selectGiftCard(userId);
		return list;
	}
	
	//���涩��
	public String saveOrder(Order order) {
		Timestamp d = new Timestamp(System.currentTimeMillis());
		order.setStartTime(d);
		order.setOrderStatus("0");
		//���涩��
		orderDAO.insertOrder(order);
		int orderId=order.getOrderId();
		for(int i=0;i<order.getProducts().size();i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("orderId", orderId);
			map.put("productId", order.getProducts().get(i).getProId());
			map.put("quantity", order.getProducts().get(i).getQuantity());
			map.put("price",  order.getProducts().get(i).getPrice());
			map.put("ifevaluate","0");
			//���涩����ص���Ʒ
			orderDAO.insertOrderItem(map);
			//�޸ĸ���Ʒ�Ŀ��
			orderDAO.updateInventory(map);
			//ɾ�����ﳵ�����궩������Ʒ
			Map<String,Object> SCmap = new HashMap<String,Object>();
			SCmap.put("Id", order.getProducts().get(i).getId());
			SCmap.put("productStatus", "3");
			shoppingCardDAO.updateProductStatus(SCmap);
		}
		return "��������ɹ�!";
		
	}
}
