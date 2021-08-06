package com.homework;

public class fibQ2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		int[] fib = new int[100]; 
		fib[1] = 1;
		int i =0; 
		while(i <9) {
			fib[i+2]=fib[i] + fib[i+1];
				System.out.println(fib[i]);
				i++; 
		}		
		
	}
}
//solved! 