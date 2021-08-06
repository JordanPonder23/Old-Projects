package com.homework;

public class Q13triagle {

	public static void main(String[] args) {
		int[] OnesZeros = {0,1,0,1,0,1,0,1,0,1};
		
		int NewValue =1;
		boolean offOn = true; 
		do {
		
			if(offOn == true) {
		for(int i =0; i <NewValue; i++) {
				
				System.out.print( OnesZeros[i] );
				System.out.print(" ");
			}
			}else if(offOn == false) {
				for(int i =1; i <NewValue+1; i++) {
					
					System.out.print( OnesZeros[i] );
					System.out.print(" ");
				}
			}
		
		offOn = !offOn;
		System.out.println(" ");
		System.out.println(" ");
		NewValue += 1; 
		}while(NewValue < 5);
		
	}

}
//wacky but solved!!! 

