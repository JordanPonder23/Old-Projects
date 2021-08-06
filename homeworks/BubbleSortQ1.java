package com.homework;

public class BubbleSortQ1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int[] intArray = {1,0,5,6,3,2,3,7,9,8,4};
		
			int outerIndex = 1; 
			while(outerIndex < intArray.length-1) {
			
			for(int i =1; i < intArray.length; i++) {
	
			if(intArray[intArray.length-i] < intArray[intArray.length -i-1]) {
				int temp = intArray[intArray.length-i-1] ;
				intArray[intArray.length-i-1] = intArray[intArray.length-i];
				intArray[intArray.length-i] = temp;	
			}   		
		}
			outerIndex += 1;
			}	
			
			int index =0; 
			for(int element: intArray) {
			System.out.println(intArray[index]);
			index +=1;
				
			}
			
	}
}//solved!! 


