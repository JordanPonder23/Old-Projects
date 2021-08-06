package com.homework;

import java.util.ArrayList;

//Q19. Create an ArrayList and insert integers 1 through 10. Display the ArrayList. Add all
//the even numbers up and display the result. Add all the odd numbers up and display the
//result. Remove the prime numbers from the ArrayList and print out the remaining
//ArrayList.

public class Q19ArrayList {
	
	public static void main(String[] args) {
		ArrayList<Integer> aL = new ArrayList<Integer>(); 
		
		for(int i =1; i<=10; i++) {
			
			aL.add(i); 
			System.out.print(i+" ");
		}// __build arrayList__ 
		System.out.println(" ");
		int evenSum = 0; 
		for(int i = 1; i<=10; i+=2) {
			evenSum = evenSum + (int)aL.get(i);
		}
		System.out.print(evenSum);
		System.out.println(" ");
		int oddSum = 0; 
		for(int i = 0; i<10; i+=2) {
			oddSum = oddSum + (int)aL.get(i);
			
		}
		System.out.print(oddSum);	
		
		for(int i =0; i<10; i++) {
									
		}		
	}		
}
