package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.SuperVillainDao;
import com.example.model.SuperVillain;

public class MyServlet extends HttpServlet {

	private static SuperVillainDao svilldao = new SuperVillainDao();

	public MyServlet() {
		insertInitialValues();
	}
	
	public static void insertInitialValues() {
		// villains
		SuperVillain vill1 = new SuperVillain("Danny Boy", "Technopath", 280_000);
		SuperVillain vill2 = new SuperVillain("Niero", "Guardian Spirit", 100_000);
		SuperVillain vill3 = new SuperVillain("Jinx", "Orb", 150_000);
		svilldao.insert(vill1);
		svilldao.insert(vill2);
		svilldao.insert(vill3);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm inside of the doGet");
		
		System.out.println("VILLAINS: ");
		System.out.println("\t"+ svilldao.selectAll() +"\n");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("I'm inside of the doPost");
		
		System.out.println("VILLAINS: ");
		System.out.println("\t"+ svilldao.selectAll() +"\n");
	}

}
