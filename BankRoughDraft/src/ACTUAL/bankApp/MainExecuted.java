package ACTUAL.bankApp;

import java.util.Scanner;

import     java.io.Serializable;
//import java.io.Externalizable;


public class MainExecuted {
static boolean loggedAdmin = false; //we start NOT logged in
static boolean LoggedCust = false; 
	public static void main(String[] args) {
		// totally not auto generated stub
		Scanner scan = new Scanner(System.in);
		
		RegLoginRun openApp = new RegLoginRun(); 
		RegLoginRun backUp = new RegLoginRun(); 
		openApp.greet();
		int adminFl =0;
		openApp.presentOptions(scan, openApp, adminFl);
					
	}
	boolean logged(boolean fromSomewhere) {
		
		loggedAdmin = fromSomewhere; // change static status 
		
		return loggedAdmin; 
	}
}

