package com.example.directservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.SuperVillain;
import com.fasterxml.jackson.databind.ObjectMapper;
/*
 * HTTP methods:
 * 	get, post, put, delete, patch, head, options, trace, etc
 */
public class DirectServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException
	{
		//the default content type is "text/html"
		//res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		out.println("<html><body><h1>The Servlet is directly talking "
				+ "to the client!</h1></body></html>");
		
		System.out.println("inside DirectServlet, doGet");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException
	{
		res.setContentType("application/json");
		SuperVillain asura = new SuperVillain("Asura", "Hair Armament", 200_000);
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(asura));
		
		System.out.println("We are in the doPost");
	}
}
