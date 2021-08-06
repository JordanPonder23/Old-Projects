package com.example.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.model.SuperVillain;

public class SessionServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException
	{
		/*SuperVillain dannyboy = new SuperVillain("Danny Boy", "Technopath", 250_000);
		
		HttpSession session = req.getSession();
		session.setAttribute("villain", dannyboy);
		
		PrintWriter out = res.getWriter();
		out.println("Danny Boy is on the loose...");*/
		
		//attempting the forms as a GET method
		
		String name = req.getParameter("name");
		String superpower = (String)req.getParameter("superpower");
		int bounty = Integer.parseInt(req.getParameter("bounty"));
		
		SuperVillain tempVill = new SuperVillain(name, superpower, bounty);
		
		HttpSession session = req.getSession();
		session.setAttribute("villain",  tempVill);
		
		PrintWriter out = res.getWriter();
		out.println("A villain is on the loose...");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException
	{
		String name = req.getParameter("name");
		String superpower = (String)req.getParameter("superpower");
		int bounty = Integer.parseInt(req.getParameter("bounty"));
		
		SuperVillain tempVill = new SuperVillain(name, superpower, bounty);
		
		HttpSession session = req.getSession();
		session.setAttribute("villain",  tempVill);
		
		PrintWriter out = res.getWriter();
		out.println("A villain is on the loose...");
	}
}
