package com.homework;

public class Q4Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	 5 : 5 * 4 *3 *2* 1... =120  Write a program to compute N factorial.
	
		int input = 5; 
		int inputCopy = input;
		int output =1; 
				for(int i= 0; i < input;) {
					
					output= output* (input - i); 	
					
					i++; 
				}
		System.out.println(output);
	}
}

//Q4. finished