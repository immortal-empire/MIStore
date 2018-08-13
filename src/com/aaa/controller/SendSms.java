package com.aaa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neu.util.model.bean.GetMessageCode;

@WebServlet("/sendSMS")
public class SendSms extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String phone=req.getParameter("phone");
		String code=GetMessageCode.getCode(phone); 
		System.out.println(code);
		resp.getWriter().print(code);
	}
}
