package com.driver;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String input = "}{";
		int OCurly = 0;
		int OBrace = 0;
		int OParanth = 0;
		int cCurly = 0;
		int cBrace = 0;
		int cParanth = 0;
		ArrayList<Integer> CurlyAL = new ArrayList<Integer>();
		ArrayList<Integer> BraceAL = new ArrayList<Integer>();
		ArrayList<Integer> ParenthAL = new ArrayList<Integer>();
		StringBuilder extraMutable = new StringBuilder(input);

		StringBuilder mutableSB = new StringBuilder(input);
		boolean broken = false; 

		int index = 0;
		while (index < input.length()) {
			if (mutableSB.charAt(index) == '{') {
				CurlyAL.add(index);
				OCurly++;
			} else if (mutableSB.charAt(index) == '[') {
				BraceAL.add(index);
				OBrace++;
			} else if (mutableSB.charAt(index) == '(') {
				ParenthAL.add(index);
				OParanth++;
			} else if (mutableSB.charAt(index) == '}') {
			
				try {
				 if( CurlyAL.get(0) < index ) {
					 CurlyAL.remove(0);
				 } else {
					 broken= true; 
					 break; 
				 }
				}catch(IndexOutOfBoundsException e ) {}
				cCurly++;
				if(cCurly > OCurly) {
					 broken= true; 
					 break; 
				}
				
			} else if (mutableSB.charAt(index) == ']') {
				try {
				if( BraceAL.get(0) < index ) {
					BraceAL.remove(0);
				 } else {
					 broken= true; 
					 break; 
				 }}catch(IndexOutOfBoundsException e ) {}
				cBrace++;
				if(cBrace > OBrace) {
					 broken= true; 
					 break; 
				}
			} else if (mutableSB.charAt(index) == ')') {
				try {
				if( ParenthAL.get(0) < index ) {
					ParenthAL.remove(0);
				 } else {
					 broken= true; 
					 break; 
				 }}catch(IndexOutOfBoundsException e  ) {}
				cParanth++;
				if(cParanth > OParanth) {
					 broken= true; 
					 break; 
				}
			}
			index++;
		}

		if (OCurly == cCurly && OBrace == cBrace && OParanth == cParanth && broken!= true) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

	}

}
