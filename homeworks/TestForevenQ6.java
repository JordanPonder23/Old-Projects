package com.homework;

public class TestForevenQ6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Write a program to determine if an integer is even without using the modulus
//		operator (%)
		
		int testedInteger = 9; 
		
		while (testedInteger > 1) {
			testedInteger = testedInteger -2; 
		}
		if(testedInteger == 1 ) {
			System.out.println("number is odd");
		}else {
			System.out.println("number is even");
		}
	}
}
//
//Q6 finished/solved!!! 