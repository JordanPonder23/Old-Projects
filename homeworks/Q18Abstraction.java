package com.homework;

import java.util.Scanner;

//Q18. Write a program having a concrete subclass that inherits three abstract methods
//from a superclass. Provide the following three implementations in the subclass
//corresponding to the abstract methods in the superclass:

//1. Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending
//if any are found.
//2. Convert all of the lower case characters to uppercase in the input string, and
//return the result.
//3. Convert the input string to integer and add 10, output the result to the console.
//Create an appropriate class having a main method to test the above setup.

public class Q18Abstraction {

	public static void main(String[] args) {
		
		Concrete cn = new Concrete(); 
		Scanner scan = new Scanner(System.in); 
		System.out.println("test whether there is uppercase in your entry:");
		String strn = scan.next();
		System.out.println(cn.upperFound(strn));
		//--
		System.out.println("convert lower case letters to uppercase:");
		String strn2 = scan.next();
		System.out.println(cn.lowerToUpper(strn2));
		//--
		System.out.println("enter a number to have 10 added to it:");
		String strn3 = scan.next();
		System.out.println(cn.stringToInteger(strn3));
	}
}
 abstract class ParentAB{
	
	 abstract boolean upperFound(String check);
	 
	 abstract String lowerToUpper(String rtrnd);
	 
	 abstract int stringToInteger(String rtrnd); 
}
 class Concrete extends ParentAB
 {

	@Override
	boolean upperFound(String check) {
		boolean rtrnd = false;
		
		char[] per = check.toCharArray();
		for(char p : per ) {
			if(Character.isUpperCase(p)) {
				rtrnd = true; 
			}
		}		
		return rtrnd;
	}

	@Override
	String lowerToUpper(String rtrnd) {
		rtrnd=rtrnd.toUpperCase();
		return rtrnd;
	}

	@Override
	int stringToInteger(String rtrnd) {
		int parsed; 
		 parsed = Integer.parseInt(rtrnd);
		 parsed = parsed +10; 
				
		return parsed;
	}
 }
 
 //solved!