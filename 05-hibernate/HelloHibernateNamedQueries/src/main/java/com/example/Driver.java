package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.SuperVillainDao;
import com.example.model.SuperVillain;
import com.example.util.HibernateUtil;

public class Driver {
	public static SuperVillainDao svdao = new SuperVillainDao();
	
	public static void main(String[] args) {
		insertInitialValues();
		
		System.out.println("ABOUT TO SYSOUT SELECTBYNAME: ");
		System.out.println(svdao.selectByName("Fite Club"));
		System.out.println();
		
		//ALL VILLAINS
		System.out.println("\nALL VILLAINS: ");
		System.out.println("\t"+svdao.selectAll());
		
		System.out.println("done");
		HibernateUtil.closeSes();
	}

	
	public static void insertInitialValues() {
		//VILLAINS
		SuperVillain vill1= new SuperVillain("R Connell", "Hacking", 1);
		
		SuperVillain vill2= new SuperVillain("J Ponder", "I could have said ahh!"
				, 10_000);
		
		SuperVillain vill3= new SuperVillain("Xiandra", "Procrastinating"
				, -5);
		
		SuperVillain vill4= new SuperVillain("Jeremiah", "Gio"
				, 20_000);
		
		//INSERTS
		svdao.insert(vill1);
		svdao.insert(vill2);
		svdao.insert(vill3);
		svdao.insert(vill4);
		/*SuperVillain vill = 
				new SuperVillain(6998, "ScreenSlaver", "Hypnotism", 45_000);
		svdao.insert(vill);
		vill=
			new SuperVillain("Syndrome", "Power Remote", 25_000);
		svdao.insert(vill);
		vill=
			new SuperVillain("Joker", "Infectious Laugh", 123_500);
		svdao.insert(vill);
		vill=
			new SuperVillain("Aquaman", "throat punch", 99_000);
		svdao.insert(vill);
		vill=
			new SuperVillain("Dirty Bubble", "filth", 10);
		svdao.insert(vill);*/
	}
}
