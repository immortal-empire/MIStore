package com.neu.bai.model.dao;
import java.util.List;

import com.neu.cart.model.bean.Address;

public interface AddressMapper {

	//��ѯ�û���ַ
		public List<Address> selectAddress(int userId);
		//�޸��û���ַ
		public void updateAddress(Address address);
		//�����û���ַ
		public void insertAddress(Address address);
		//ɾ���û���ַ
		public void deleteAddress(int cid);
		
}
