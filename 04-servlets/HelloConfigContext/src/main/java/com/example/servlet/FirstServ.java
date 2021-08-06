package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServ extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/*	This method is an example of a servlet using servletcontext and servletconfig.
	 *
	 *	It's simply retrieving the values then printing them to the console.
	 *
	 *	If you check the web.xml you'll see the config was declared within this servlet's servlet tags
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String contextEx = getServletContext().getInitParameter("contextExample");
		String configEx = getServletConfig().getInitParameter("configExample");
		
		System.out.println(contextEx+" & "+configEx);
	}
}
