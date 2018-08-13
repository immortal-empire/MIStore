package com.neu.bai.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.neu.bai.model.dao.AddressMapper;
import com.neu.cart.model.bean.Address;

@Service
public class Addressservice {

	@Autowired
	private AddressMapper addressmapper;
	//��ѯ��ǰ�û�ӵ�еĵ�ַ
		public List<Address> getAddress(int userId) {
			List<Address> list = addressmapper.selectAddress(userId);
			for(Address a:list) {
				a.setSelected(false);
			}
			return list;
		}
		
		//�����û���ַ�������������޸ģ�
		public String updateAddress(Address address) {
			if(address.getAddressId()==0) {
				addressmapper.insertAddress(address);
			}else {
				addressmapper.updateAddress(address);
			}
			return "��ַ���³ɹ���";
		}

		public void deleteAddress(int cid){
			//System.out.println(address.getConsumerName());
			addressmapper.deleteAddress(cid);
		}
}
