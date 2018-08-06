package com.neu.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.order.model.bean.OrdersView;
import com.neu.order.model.bean.ProductsCommentView;
import com.neu.order.model.service.OrderService;

@Controller
public class OrderViewsController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/OrderViews/{type}/{cid}", method=RequestMethod.GET)
	@ResponseBody
	public List<OrdersView> getOrderByType(@PathVariable int type,@PathVariable int cid){
		System.out.println("½øÈëcontroller");
		return orderService.getOrderByType(type, cid);
	}
	@RequestMapping(value="/getUncommentedProduct/{cid}", method=RequestMethod.GET)
	@ResponseBody
	public List<ProductsCommentView> getUncommentedProduct(@PathVariable Integer cid){
		return orderService.getUncommentedProduct(cid);
	}
}
