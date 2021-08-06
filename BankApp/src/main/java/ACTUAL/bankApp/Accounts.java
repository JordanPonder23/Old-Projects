package ACTUAL.bankApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Accounts {
	
	//All these can probably be made static variables before finished product
	String accntNumber; 
	boolean checking; 
	boolean joint; 
	String owner1; 
	String owner2; 
	String currentHoldings;
	
		
	public String getAccntNumber() {
		return accntNumber;
	}
	public void setAccntNumber(String accntNumber) {
		this.accntNumber = accntNumber;
	}
	public boolean isChecking() {
		return checking;
	}
	public void setChecking(boolean checking) {
		this.checking = checking;
	}
	public boolean isJoint() {
		return joint;
	}
	public void setJoint(boolean joint) {
		this.joint = joint;
	}
	public String getOwner1() {
		return owner1;
	}
	public void setOwner1(String owner1) {
		this.owner1 = owner1;
	}
	public String getOwner2() {
		return owner2;
	}
	public void setOwner2(String owner2) {
		this.owner2 = owner2;
	}
	public String getCurrentHoldings() {
		return currentHoldings;
	}
	public void setCurrentHoldings(String currentHoldings) {
		this.currentHoldings = currentHoldings;
	} 
	void accntInfo(String found, String flNm, boolean Admin) {
		StringBuilder sb = new StringBuilder(found);
		
		found= sb.toString();			
	
	String grabName = found.substring(found.indexOf('[')+1, found.indexOf(']'));
	
	sb.delete(found.indexOf('['), found.indexOf(']')+1);
	found = sb.toString(); 
	String lstNm= found.substring(found.indexOf('[')+1, found.indexOf(']'));
	sb.delete(found.indexOf('['), found.indexOf(']')+1);
	found = sb.toString(); 
	String sal= found.substring(found.indexOf('[')+1, found.indexOf(']'));
	sb.delete(found.indexOf('['), found.indexOf(']')+1);
	found = sb.toString(); 
	String hs= found.substring(found.indexOf('[')+1, found.indexOf(']'));
	sb.delete(found.indexOf('['), found.indexOf(']')+1);
	found = sb.toString(); 
	String co= found.substring(found.indexOf('[')+1, found.indexOf(']'));
	sb.delete(found.indexOf('['), found.indexOf(']')+1);
	found = sb.toString(); 
	System.out.println("Primary account owner: ");
	System.out.println("Name: " +grabName+" "+lstNm+" Salary: " +sal);
	System.out.println("Homeowner: " + hs+" | "+"Carowner: "+co );
	
	String check = found.substring(found.indexOf('[')+1, found.indexOf(']'));
//	System.out.println("check in accounts: "+ check); //test
	int parsibility = 0; 
	boolean nParsed = true; 
	try{
		parsibility= Integer.parseInt(check);//this will either be eg. '200', or eg. 'Jim'
	}catch(NumberFormatException nfe) {
		/// not a number
		nParsed = false; 
	}
	//System.out.println("nparsed value in Regrun: " + nParsed); //-test-
	
	if(nParsed == false)//just using 3 to account for random spaces during testing- 
	{//ugly     :/  Just want to make it work, at this point... 
		
		String grabName2 = found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String lstNm2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String sal2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String hs2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String co2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		System.out.println("");
		System.out.println("Secondary account owner: ");
		System.out.println("Name: " +grabName2+" "+lstNm2+" Salary: " +sal2);
		System.out.println("Homeowner: " + hs2+ " |"+" Carowner: "+co2 );
		System.out.println("------------------------------------");
	}
	Accounts ac = new Accounts(); //v-test-
	
	System.out.println("Current Account Balance: " +"$"+ac.calcSum(found)+".00");
	System.out.println("=-==--=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-");
	
	if(Admin == true & found.length() >1) {
		System.out.println();
		System.out.println("Recent History: ");
	   ac.generateHistory(found);  // this will give me all the deposits and withdraw interactions performed by the EU(end user) 
	   
	}else if (found.equals("")) {
		System.out.println("-This account, currently has no history to show-");
	}
	System.out.println("Press Enter twice to close this static-form"); //I think the problem is that we always return to the actual options... we HAVE to return to the search because the whole functionality is dependent on starting there because I'm a
	Scanner scan = new Scanner(System.in);
	scan.nextLine();scan.nextLine();//takes two 
	RegLoginRun rlr = new RegLoginRun();
	
	if(Admin == true) {
	rlr.displayAdmCons();
	rlr.adminConsole(scan, rlr);// bring it back to options. 
	}else {
		rlr.accountSearch();
	}
	
	}
	//history generator 
	void generateHistory(String found) {
		
		while(found.length() >3) {
		String dep = "Deposited: ";
		String drew = "Withdrew: ";
		String grab = found.substring(found.indexOf('[')+1, found.indexOf(']'));
		char negativeCheck = grab.charAt(0);//check to see whether the first char is '-' or not 
		if(negativeCheck=='-') {
			System.out.println(drew + grab+".00");
		}else {
			System.out.println(dep +"$"+ grab+".00");
		}
		StringBuilder sb = new StringBuilder(found); 
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString();
	}
	}
	
	String openAccnt(String found) {
		StringBuilder sb = new StringBuilder(found);
			
			found= sb.toString();			
		
		String grabName = found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String lstNm= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String sal= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String hs= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		String co= found.substring(found.indexOf('[')+1, found.indexOf(']'));
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		found = sb.toString(); 
		
		String check = found.substring(found.indexOf('[')+1, found.indexOf(']'));
	//	System.out.println("check in accounts: "+ check); //test
		int parsibility = 0; 
		boolean nParsed = true; 
		try{
			parsibility= Integer.parseInt(check);//this will either be eg. '200', or eg. 'Jim'
		}catch(NumberFormatException nfe) {
			/// not a number
			nParsed = false; 
		}
		//System.out.println("nparsed value in Regrun: " + nParsed); //-test-
		
		if(nParsed == false)//just using 3 to account for random spaces during testing- 
		{//ugly     :/  Just want to make it work, at this point... 
			String grabName2 = found.substring(found.indexOf('[')+1, found.indexOf(']'));
			sb.delete(found.indexOf('['), found.indexOf(']')+1);
			found = sb.toString(); 
			String lstNm2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
			sb.delete(found.indexOf('['), found.indexOf(']')+1);
			found = sb.toString(); 
			String sal2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
			sb.delete(found.indexOf('['), found.indexOf(']')+1);
			found = sb.toString(); 
			String hs2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
			sb.delete(found.indexOf('['), found.indexOf(']')+1);
			found = sb.toString(); 
			String co2= found.substring(found.indexOf('[')+1, found.indexOf(']'));
			sb.delete(found.indexOf('['), found.indexOf(']')+1);
			found = sb.toString(); 
			
		}
		System.out.println("------------------------------------");
		Accounts ac = new Accounts(); //v-test-
	//	System.out.println("From the chipper: " + found);// this will give me all the deposits and withdraw interactions performed by the EU(end user) 
		System.out.println("Current Account Balance: "+"$" +ac.calcSum(found)+".00");
		return found; //returned from the chipper
	}
	void withdraw(String accntInfo, String filename) {
		Accounts ac = new Accounts(); 
		ac.openAccnt(accntInfo);
		
		String saved = accntInfo;//in case they withdraw too much, save the old data. 
		System.out.println("Enter an amount you'd like to withdraw (enter 'e' to leave): ");
		Scanner scan = new Scanner(System.in);
		String newWithdraw = scan.next(); 
		if(newWithdraw.equalsIgnoreCase("e")) {
			
			CustomerBlueprint cb = new CustomerBlueprint(); 
			RegLoginRun rlr = new RegLoginRun(); 
			rlr.accountSearch();
		}else {
		
		newWithdraw = "["+"-"+newWithdraw+"]";
		
	//	System.out.println("adding to chain "+ newWithdraw); // test-
		accntInfo = accntInfo +newWithdraw;
	//	System.out.println("chain: "+ accntInfo); // test-
		
		ac.writeAccount(accntInfo, filename);
		//ac.openAccnt(accntInfo);
		ac.withdraw(accntInfo, filename);
		}
	}
	int calcSum(String found) //--found passed from above ^^ (openAccnt) 
	{
		
		int returnSum =0; 
		int cycleindex = found.length();//not an exact incrimentor.. Just want found.length to have a variable.. idk why.. 
		
		char[] cycleStr = found.toCharArray();
		int trustit=0; 
		for(char all : cycleStr) {
			if(all == '[') {
				++trustit;
			}
		}
		 
		while(trustit>0) {
		String grab = found.substring(found.indexOf('[')+1, found.indexOf(']')); //grabs a value
		
		int grabbed=0; 
		
	//	System.out.println("string: "+grab); test-
		try {
		 grabbed = Integer.parseInt(grab);//this should work every time.. 	--try catch may be smart just for later!! 
		}
		catch(NumberFormatException nfe) {
			break; 
		}
	//	System.out.println("int "+grabbed); test-
		int saved = returnSum; 
		
		returnSum = returnSum+ grabbed;
		if(returnSum <5) {
			returnSum = saved; 
		//	System.out.println("   * you ain't got that kinda money, honey :(");
		}
		//System.out.println("for return "+ grabbed); test-
		
		StringBuilder sb = new StringBuilder(found); 
		
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		
		found = sb.toString(); 
		
	//	System.out.println("leftover: "+ found); test-
		
		trustit=trustit-1; 
		}
	//	System.out.println("duffer? "+returnSum); test-
		return returnSum; 
	}

	
	void deposit(String accnt, String fileSpot, boolean zr) {
	if(zr== true) {
		System.out.println("Current balance is: $0 ");
		System.out.println("Enter an amount to deposit: ");
		System.out.println(" (Type 'e' to exit)");
	}else if(zr== false) {
		RegLoginRun rlr1 = new RegLoginRun();									//			<-------!!!!!!!!!!!!!!!!
		Accounts ac1 = new Accounts(); 								//calling the chipper here
		ac1.openAccnt(accnt) ; //working?
		
		System.out.println("Enter an amount to deposit: ");
		System.out.println(" (Type 'e' to exit)");
	}
	Scanner scan = new Scanner(System.in); 
	String initialPrompt = scan.next();
	String returnedValue ="";
	Accounts ac = new Accounts(); 
	
	if(initialPrompt.equalsIgnoreCase("e")) {
	    Accounts ac3 = new Accounts(); 
	    zr = true; 
	   // ac3.deposit(accnt,fileSpot ,zr);
	    CustomerBlueprint cb = new CustomerBlueprint(); 
	   
	}else {//begin else statement-
	//=-=-=-=-=-
	int parseAtt=0; 
	try {
	parseAtt = Integer.parseInt(initialPrompt);//<<<< 
	}
	catch (NumberFormatException nfe)
	{
		System.out.println(" ** Enter a value of money in numbe format* ");
		System.out.println(" -:");
		
		zr= false; //no need to represent the menu options
		ac.deposit(accnt, fileSpot, zr);
	}
	//====================-==--
	System.out.println("$"+parseAtt+ " will be added to your account -");
	System.out.println("           ( is this correct: 'y' / 'n' )");
	String confirm = scan.next();
	if(confirm.equalsIgnoreCase("y"))
	{
		accnt = accnt+"["+parseAtt+"]";//going to string all interactions together
		
		File deletingAction = new File(fileSpot);//delete old
		deletingAction.delete();//delete
		ac.writeAccount(accnt, fileSpot);//create new
		System.out.println( "     $_$ <.< " );
		System.out.println("=--=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println(" Deposit more =-= Return   =-=  Log out");
		System.out.println("=  Type '1'   -:- Type '2' -:-  Type '3'  = ");
		System.out.println("-------------------------------------------");
		
		ac.fromDep(accnt,fileSpot);
	}
	else if(confirm.equalsIgnoreCase("n")) {
		System.out.println("How much would you like to deposit?");
		System.out.println(":");
		ac.deposit(accnt, fileSpot, zr);
		
	} 
//	System.out.println("file: "+fileSpot); -test-
//	System.out.println("test: "+ accnt);   -test-
	}//end else statement- 
	}
	void fromDep(String accnt, String fileSpot){
		Scanner scan = new Scanner(System.in); 
		String initialPrompt = scan.next(); 
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);//<<<< 
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" * Not an option");
			fromDep(accnt, fileSpot);
		}
		RegLoginRun rlr = new RegLoginRun();
		if(parseAtt ==1) {
			Accounts ac = new Accounts(); 
			boolean zr = false; 
			ac.deposit(accnt, fileSpot, zr);
		}else if(parseAtt ==2) {
			CustomerBlueprint cb = new CustomerBlueprint(); 
			
			rlr.accountSearch();
			
		}else if(parseAtt ==3) {
			rlr.presentOptions(scan, rlr, 0);
		}
	}
	
	void writeAccount(String accnt, String  fileSpot)
	{
	//	System.out.println("write method called "); //test- 
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSpot)))
		{
			
			oos.writeObject(accnt);  
		} 
		catch (IOException e) 
		{
			System.out.println("IOException.. ");
			//e.printStackTrace(); nothing for now.. 
		}
	}
	
	String reviewAccnt(String filename)
	{
		String stringly = "";
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
		{
			Object obj = ois.readObject(); 
			
			stringly = obj.toString();
			//System.out.println(stringly); -test-
		} 
		catch (FileNotFoundException e)
		{
			
		//	System.out.println("");
		//	System.out.println("Application started -previous file resolved");
		}
		catch (IOException e) 
		{
			//System.out.println("test file"); //during testing the test file will throw this! 
			
		} 
		catch (ClassNotFoundException e)
		{
			//System.out.println("You did something very wrong and should feel ashamed.");
		}			
	return stringly; 		
	}
	
}
