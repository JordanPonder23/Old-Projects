package com.homework;

public class substringQ5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		Write a substring method that accepts a string str and an integer idx and returns the
//		substring contained between 0 and idx-1 inclusive. Do NOT use any of the existing
//		substring methods in the String, StringBuilder, or StringBuffer APIs.
		
		String str = "theshadyshadiness"; 
		int idx = 5; 
		int Zero = 0;
		String Snippet = "";
		
		while(Zero < idx) {
			char Character = str.charAt(Zero); 
			Snippet = Snippet + Character; 
			
			Zero++; 
		}
		System.out.println(Snippet);	
	}
}

//substring q5 solved!!! 

