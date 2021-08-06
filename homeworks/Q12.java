package com.homework;

public class Q12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Q12. Write a program to store numbers from 1 to 100 in an array. Print out all the even
//		numbers from the array. Use the enhanced FOR loop for printing out the numbers.
			
		int[] storage = new int[100];
		
			for(int i = 1; i <=100;i++) {
				storage[i-1] = i; 
				//this should fill the array- 
			}
			for (int element : storage) {
				if(element % 2 == 0) {
					System.out.println(element);
				}
			}
	}

}
//solved!!! 