package com.example.controller;

import javax.servlet.http.HttpServletRequest;

public class LoginController {
	public static String login(HttpServletRequest request) {
		if(!request.getMethod().equals("POST")) {
			return "index.html";
		}
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(!(username.equals("cheese") && password.equals("louise"))){
			return "/wrongcreds.do";
		}
		else {
			request.getSession().setAttribute("loggedusername", username);
			request.getSession().setAttribute("loggedpassword", password);
			return "/home.do";
		}
	}
}
