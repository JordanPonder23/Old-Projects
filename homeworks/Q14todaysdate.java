package com.homework;
import  java.lang.Math.*;
import java.util.Date;

public class Q14todaysdate {

//	Write a program that demonstrates the switch case. Implement the following
//	functionalities in the cases:
//	Case 1: Find the square root of a number using the Math class method.
//	Case 2: Display today’s date.
//	Case 3: Split the following string and store it in a sting array.
//
//	“I am learning Core Java”
	
	public static void main(String[] args) {
		
		int caser = 3; 
		
		switch(caser)
		{
		
		case 1: 
			System.out.println(Math.sqrt(9));
			break; 
				
		case 2: Date d = new Date();
				System.out.println(d);
			break; 
				
		case 3: 
			
			String origStr = "I am learning Core Java";	
			String[] strArray=	origStr.split(" ");
			
			for(int i =0; i<4 ;i++) {
				System.out.println(strArray[i]);
				}
			break; 
				
		}
		
		
	}


}
////finished. 