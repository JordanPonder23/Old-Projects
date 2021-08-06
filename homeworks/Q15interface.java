package com.homework;

import java.util.Scanner;

public class Q15interface {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Test tst = new Test(); 
		//-----
		System.out.println("enter, at least, one number to add: ");
		int add1 = scan.nextInt();
		System.out.println("Enter another number to add: ");
		int add2 = scan.nextInt(); 
		System.out.println(tst.addition(add1, add2));
		//--
		System.out.println("Enter a number to subtract: ");
		int sub1 = scan.nextInt(); 
		System.out.println("Enter another to subtract : ");
		int sub2 = scan.nextInt(); 
		System.out.println(tst.subtraction(sub1, sub2));
		//--
		System.out.println("Enter a number to divie: ");
		int div1 = scan.nextInt();
		System.out.println("Enter a number to divide by: ");
		int div2 = scan.nextInt(); 
		System.out.println(tst.division(div1, div2));
		//--
		System.out.println("Enter a number to multiply: ");
		int mult1 = scan.nextInt();
		System.out.println("Enter a number to multiply by: ");
		int mult2 = scan.nextInt();
		System.out.println(tst.multiplication(mult1, mult2));
	}
}
//----=
interface Implmnt{
	int addition(int add, int add2);
	int subtraction(int  sub, int  sub2);
	int division(int  div, int  div1); 
	int multiplication(int  mult, int  mult2); 
}
//----=
class Test implements Implmnt{

	@Override
	public int addition(int add, int add2) {
		int rtrn = add + add2; 
		
		return rtrn;
	}

	@Override
	public int subtraction(int  sub, int  sub2) {
		int rtrn = 0; 
		rtrn = sub - sub2;		
		return rtrn;
	}
	@Override
	public int division(int  div, int  div1) {
		int rtrn = div/div1; 
		
		return rtrn;
	}
	@Override
	public int multiplication(int  mult, int  mult1) {
		int rtrn = mult* mult1; 
		
		return rtrn;
	}	
}