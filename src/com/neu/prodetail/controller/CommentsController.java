package com.neu.prodetail.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.neu.cart.model.service.OrderService;
import com.neu.prodetail.model.bean.Comments;
import com.neu.prodetail.model.service.CommentsService;

@Controller
public class CommentsController {
	@Autowired
	private CommentsService commService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/getCommentsByProIdAndRank/{proId}/{rank}", method=RequestMethod.POST)
	@ResponseBody
	public List<Comments> getCommentsByRank(@PathVariable int proId, @PathVariable int rank) {
		
		return commService.getCommentsByRank(proId, rank);
	}
	
	@RequestMapping(value="/getCommTypeNum/{proId}", method=RequestMethod.POST)
	@ResponseBody
	public List<Integer> getCommTypeNum(@PathVariable int proId) {
		
		return commService.getCommTypeNum(proId);
	}
	
	@RequestMapping(value="/getNewComments/{proId}", method=RequestMethod.POST)
	@ResponseBody
	public List<Comments> getNewComments(@PathVariable int proId) {
		
		return commService.getNewComments(proId);
	}
	
	@RequestMapping(value="/addComments", method=RequestMethod.POST)
	@ResponseBody
	public String addComments(Comments comment, @RequestParam MultipartFile[] upload, HttpServletRequest request) {
		
		System.out.println(comment.getCid() + comment.getProId() + comment.getOrderid() + comment.getRank());
		int folder = commService.getAllCommentsNum() + 1;
		comment.setCommId(folder);
		String uploadpath = request.getServletContext().getRealPath("/img") + "/" + folder;
		System.out.println(uploadpath);
		commService.addComments(comment,upload,uploadpath);
		orderService.setIfEvaluate(comment.getOrderid(), comment.getProId());
		return "{\"result\":true}";
	}
	
}
