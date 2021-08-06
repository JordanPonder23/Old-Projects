package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*") //* means everything
@Controller
public class SessionController {

	@GetMapping(value="/getName.app")
	public void getLoggedInName(HttpSession session) {
		System.out.println("in the getName method");
		
		String name = (String)session.getAttribute("loggedName");
		System.out.println(name);
		
		String pass = (String)session.getAttribute("loggedPass");
		System.out.println(pass);
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	@GetMapping(value="/login.app")
	public void login(HttpSession session) {
		System.out.println("in the login method");
		session.setAttribute("loggedName", "MyNameIsPeachesAndImTheBest");
		session.setAttribute("loggedPass", "soulglow");
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	@GetMapping(value="/logout.app")
	public void logout(HttpSession session) {
		System.out.println("in the logout method");
		session.invalidate();
		
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
