package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.dao.CrimeDao;
import com.example.dao.SuperPrisonDao;
import com.example.dao.SuperVillainDao;
import com.example.model.Crime;
import com.example.model.SuperPrison;
import com.example.model.SuperVillain;
import com.example.util.HibernateUtil;

public class Driver {
	public static SuperVillainDao svdao = new SuperVillainDao();
	public static CrimeDao cdao = new CrimeDao();
	public static SuperPrisonDao spdao = new SuperPrisonDao();
	
	public static void main(String[] args) {
		//insertInitialValues();
		
		System.out.println("ABOUT TO SYSOUT SELECTBYNAME: ");
		System.out.println(svdao.selectByName("Xiandra"));
		System.out.println();
		
		//PRISONS
		System.out.println("\nALL PRISONS: ");
		System.out.println("\t"+spdao.selectAll());
		
		//CRIMES
		System.out.println("\nALL CRIMES: ");
		System.out.println("\t"+cdao.selectAll());
		
		//A VILLAIN
		System.out.println("Select by Id 8: " + svdao.selectById(8));
		//ALL VILLAINS
		System.out.println("\nALL VILLAINS: ");
		System.out.println("\t"+svdao.selectAll());
		
		System.out.println("done");
		HibernateUtil.closeSes();
	}

	
	public static void insertInitialValues() {
		
		//SUPER PRISONS
		SuperPrison sprison1 = new SuperPrison("Raft", "it's in the middle of the ocean somewhere");
		SuperPrison sprison2 = new SuperPrison("Impel Down","5 levels, no exit");
		SuperPrison sprison3 = new SuperPrison("Arkham","The worst of the worst are here");

		//CRIMES
		Crime crime1 = new Crime("Aggravated Assault", "Causing Bodily Harm");
		Crime crime2 = new Crime("Arson", "Burning buildings, land, or property");
		Crime crime3 = new Crime("Pineapples on Pizza", "Who would do such a thing?");
		Crime crime4 = new Crime("The Twilight Series", "Those movies are illegal, right?");
		Crime crime5 = new Crime("Throat Punching", "Wait...isn't that aggravated assault?");
		
		//VILLAINS
		List<Crime> crimeList = new ArrayList<>();
		
		crimeList.add(crime1);
		crimeList.add(crime5);
		SuperVillain vill1= new SuperVillain("R Connell", "Hacking", 1, crimeList, sprison3);
		sprison3.getVillList().add(vill1);
		
		crimeList= new ArrayList<>();
		crimeList.add(crime5);
		crimeList.add(crime3);
		crimeList.add(crime4);
		SuperVillain vill2= new SuperVillain("J Ponder", "I could have said ahh!"
				, 10_000, crimeList, sprison3);
		sprison3.getVillList().add(vill2);
		
		crimeList= new ArrayList<>();
		crimeList.add(crime4);
		crimeList.add(crime5);
		crimeList.add(crime3);
		crimeList.add(crime2);
		crimeList.add(crime1);
		SuperVillain vill3= new SuperVillain("Xiandra", "Procrastinating"
				, -5, crimeList, sprison2);
		sprison2.getVillList().add(vill3);
		
		crimeList= new ArrayList<>();
		crimeList.add(crime3);
		crimeList.add(crime1);
		SuperVillain vill4= new SuperVillain("Jeremiah", "Gio"
				, 20_000, crimeList, sprison2);
		sprison2.getVillList().add(vill4);
		
		//INSERTS
		spdao.insert(sprison1);
		spdao.insert(sprison2);
		spdao.insert(sprison3);
		cdao.insert(crime1);
		cdao.insert(crime2);
		cdao.insert(crime3);
		cdao.insert(crime4);
		cdao.insert(crime5);
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
