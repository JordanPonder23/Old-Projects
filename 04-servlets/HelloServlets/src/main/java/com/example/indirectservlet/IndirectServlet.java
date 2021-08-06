package com.example.indirectservlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * There are THREE ways a servlet can respond to an incoming request:
 * 	-Direct Response    (we use the PrintWriter object)
 * 					This happens when the servlet doesn't need any other help
 * 				Comes from the HttpServletResponse object
 * 	-Forward	(1 req, 1 resp.  Uses RequestDispatcher's .forward() to operate)
 * 				Comes from the HttpServletRequest object
 * 	-Redirect	(2 req, 2 resp. Uses Response object's .sendRedirect() to operate)
 * 				Comes from the HttpServletResponse object
 * 
 */
public class IndirectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		System.out.println("in IndirectServlet, GET");
		
		//this is how to redirect to a html page
		res.sendRedirect(
			"http://localhost:9001/HelloServlets/resources/html/secondpage.html");
		
		//this is how to redirect to another servlet
		/*res.sendRedirect(
				"http://localhost:9001/HelloServlets/dirserv");*/
		
		//this is how to redirect to another external website
		/*res.sendRedirect("https://www.google.com/");*/
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException
	{
		System.out.println("in IndirectServlet, POST");
		
		/*RequestDispatcher redis = req.getRequestDispatcher(
			"/resources/html/secondpage.html");*/
		
		/*RequestDispatcher redis = req.getRequestDispatcher(
				"https://www.google.com/");*/
		
		RequestDispatcher redis = req.getRequestDispatcher(
				"/dirserv");
		
		redis.forward(req, res);
	}
}
