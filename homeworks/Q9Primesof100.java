package com.homework;
import java.util.ArrayList; 
import java.util.Arrays; 


//Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime
//numbers to the console.

public class Q9Primesof100 {

	public static void main(String[] args) {
		
		ArrayList<Integer> numbers = new ArrayList<>(100);
		for(int i =1; i <=100; i++) {
			numbers.add(i);
		}
		int index = 0; 
		
		for(int i =0; i <100; i++) {
		if (numbers.get(i) >1) {
				
				if(i%2 ==0 & i >2) {
					System.out.println(numbers.get(i) +" ");
				}else if(i%3 ==0 &i>3) {
					System.out.println(numbers.get(i) +" ");
				}else if(i%5 ==0 & i>5) {
					System.out.println(numbers.get(i)+" ");
				}else if(i%7 ==0 & i> 7) {
					System.out.println(numbers.get(i)+" ");
				}
				
			}
			
		}		
	}	
}
