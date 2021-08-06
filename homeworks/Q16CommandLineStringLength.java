package com.homework;

import java.util.Scanner;

public class Q16CommandLineStringLength {
	
//	Q16. Write a program to display the number of characters for a string input. The string
//	should be entered as a command line argument using (String [ ] args).	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
				
		String input = scan.next();
		input.length();
	//	int gre = Integer.parseInt(input);
		
		System.out.println(input.length());
		
	}	
}//solved!
