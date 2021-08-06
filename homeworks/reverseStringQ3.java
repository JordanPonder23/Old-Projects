package com.homework;

public class reverseStringQ3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
//		Reverse a string without using a temporary variable. Do NOT use reverse() in the
//		StringBuffer or the StringBuilder APIs.
		
		String Reversable = "strawberry"; 
		StringBuilder sb = new StringBuilder(Reversable);
		int index = 0; 
		sb.trimToSize();//fixes length around the actual characters
		int length = sb.capacity(); //returns length
		StringBuilder sb2 = new StringBuilder(); 
		String NewString ="";
				
				for(int i =0; i < length;) {
					char temp = sb.charAt(length-1 -i);
					sb2.append(temp);
					i++; 
				}		
		System.out.println(sb2);			
	}
}
//working!!! 