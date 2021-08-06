package com.homework;

import java.util.Scanner;

public class Q17scanner {
//	Q17. Write a program that calculates the simple interest on the principal, rate of interest
//	and number of years provided by the user. Enter principal, rate and time through the
//	console using the Scanner class.

//	Interest = Principal* Rate* Time
	public static void main(String[] args) {
		
		int principal = 5000;
		double interR = 0.04;
		
		System.out.println("The principal is: "+ principal);
		System.out.println("-=-=-=-=-=-=-");
		System.out.println("Enter number of year: ");
		System.out.println("");
		
		Scanner scan = new Scanner(System.in);
		
		String yrs =  scan.next();
		int intyrs = Integer.parseInt(yrs);
		
		double extra2 =0; 
		while(intyrs > 0) {
			extra2 = principal+ (principal*interR);	
			intyrs -=1;
		}
		
		System.out.println(extra2 +"");
		
	/////solved!!
	}
}
