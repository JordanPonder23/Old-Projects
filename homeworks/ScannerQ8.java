package com.homework;
import java.util.ArrayList; 
import java.util.Arrays; 

import java.applet.AppletContext;

public class ScannerQ8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//idk why this one right below doesn't work??
//		ArrayList<String> Storage = new ArrayList<>(Arrays.asList(“karan”, “madam”, ”tom”, “civic”, “radar”, “sexes”, “kayak”, “jimmy”, “john”, “refer”, “billy”,"did"));//five long
		ArrayList<String> palindromes = new ArrayList<>(12);
		ArrayList<String> Storage = new ArrayList<>();//capacity of twelve
		Storage.add("karan");
		Storage.add("madam");
		Storage.add("tom");
		Storage.add("civic");
		Storage.add("radar");
		Storage.add("sexes");
		Storage.add("jimmy");
		Storage.add("kayak");
		Storage.add("john");
		Storage.add("refer");
		Storage.add("billy");
		Storage.add("did");
		for(int i =0; i < 12; i++) {
	  StringBuilder sb = new StringBuilder(Storage.get(i));
			String reversed = sb.reverse().toString();
//			System.out.println(Storage.get(i));
//			System.out.println(reverse);
			if(reversed.equals(Storage.get(i).toString())) {
				palindromes.add(reversed);
				
			}
			
		
		
		}
		System.out.println(palindromes);
		//finished!! Q8
	}

}
