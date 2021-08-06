package ACTUAL.bankApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class CustomerBlueprint {
	
	//String custID; 
	private static String username; 
	private static String name; 
	private static String lastName; 
	private static String salary; 
	private static boolean house; 
	private static boolean car;
	private static String password;
	
	CustomerBlueprint(){
		
	}
	
	//initial creation constructor for when a user creates a user-name and password that works. 
	CustomerBlueprint(String storeUsername, String storePassword){
		
	}
	
//---------------------------------	
//	public String getCustID() {
//		return custID;
//	}
//	public void setCustID(String custID) {
//		this.custID = custID;
//	}
//---------------------------------
	public  String getUsername() {
		return username;
	}
	public  void setUsername(String username) {
		CustomerBlueprint.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public boolean isHouse() {
		return house;
	}
	public void setHouse(boolean house) {
		this.house = house;
	}
	public boolean isCar() {
		return car;
	}
	public void setCar(boolean car) {
		this.car = car;
	}
	//CHECKING PASSWORD DURING REGISTERING
	boolean confirmPassword(Scanner scan, CustomerBlueprint cb) {
		System.out.println("Confirm password: ");
		String scndPrompt = scan.next(); 
		boolean confirmP = false; 
		if(scndPrompt.equals(cb.getPassword())) {
		//	System.out.println("these equal"); - prior test -
			confirmP = true; 
		}else
		{
			System.out.println("Passwords don't match. Try again. ");
			cb.setPassword("");//should pass in blankness, to clean up for another try. 
			confirmP = false; 
		}		
		
		return confirmP;
	}
				//LOGIN FUNCTIONALITY FOR CUSTOMER
	boolean credCheck(Scanner scan, CustomerBlueprint cb, RegLoginRun backUp) //!!!!! CRED CHECK WILL NEED TO BE FIXED AFTER STERIALIZATION!!
	{ 
		System.out.println("Enter Username: ");
		String usernmEntry = scan.next(); //       !!!! spaces still break this :/ 
		if(usernmEntry.length() <6) {
			System.out.println("    *I am computer and I know this is not your username.. it's too short.");
			System.out.println(" Enter a username that is at least 6 characteres: ");	
			cb.credCheck(scan, cb, backUp); //try again-
		}
		System.out.println("Enter Password: ");
		String psswrdEntry = scan.next(); //  ---        -- ^ !!
		if(psswrdEntry.length() <8) {
			System.out.println("    *I am computer and I know this is not your password.. it's too short.");
			System.out.println(" Enter a password that is at least 8 characteres: ");	
			cb.credCheck(scan, cb, backUp); //try again-
		}
		SQLConnections sqlc = new SQLConnections(); 
		//System.out.println(sqlc.readingConnections(psswrdEntry)); -test-
		
		//	System.out.println("compare user input " +usernmEntry); //-test-
		if(psswrdEntry.equals(sqlc.readingConnections(usernmEntry))) //evaluate credentials, comparing user data to current text file data.. 
		{
			MainExecuted mx = new MainExecuted(); 
			mx.logged(true);
			return true; 
		}
		else
		{
			return false; 
		}
	}
		static String confirmAccountUser() {
			Scanner scan = new Scanner(System.in); 
			CustomerBlueprint cb = new CustomerBlueprint(); 
			System.out.println("Enter Password: ");
			String psswrdEntry = scan.next(); //  ---        -- ^ !!
			if(psswrdEntry.length() <8) {
				System.out.println("    *I am computer and I know this is not your password.. it's too short.");
				System.out.println(" Enter a password that is at least 8 characteres: ");
				cb.confirmAccountUser();
			}
			return psswrdEntry; 
		} 
		
		String accountOptions(Scanner scan, CustomerBlueprint cb, String wdrw) {
			System.out.println("Withdraw      - type '1'     -=");
			System.out.println("Deposit        - type '2'       ");
			System.out.println("Account Status  - type '3'     ");
			System.out.println(" (and history)                " );
			System.out.println("       (Logout  =- type 'e') -=");
			String initialPrompt = scan.next();
			String returnedValue ="";
			if(initialPrompt.equalsIgnoreCase("e")) {
				RegLoginRun rlr = new RegLoginRun(); 
				rlr.customerLogin(scan , rlr);
			}else {
			//=-=-=-=-=-
			int parseAtt=0; 
			try {
			parseAtt = Integer.parseInt(initialPrompt);//<<<< 
			}
			catch (NumberFormatException nfe)
			{
				System.out.println(" ** Enter a number ");
				cb.accountOptions(scan, cb, wdrw);
			}
			//==========-
			SQLConnections sqlc = new SQLConnections(); 
			if(parseAtt == 1) {
				System.out.println("   You choose withdraw ~~~ ");
				System.out.println();
				System.out.println("Enter an Amount to withdraw:");
				String withdrawinquire = scan.next();
					
					sqlc.Withdraw(withdrawinquire, wdrw);
					
			}else if (parseAtt ==2) {
				System.out.println("   You choose withdraw ~~~ ");
				System.out.println();
				System.out.println("Enter an Amount to deposit:");
				String depositamount = scan.next();
					
					sqlc.Deposit(depositamount, wdrw);
			}else if(parseAtt ==3) {
				sqlc.printAccountHistory(wdrw);	
				System.out.println();
				System.out.println("Press 'Enter' to return");
				scan.nextLine();
				scan.nextLine();
				cb.accountOptions(scan, cb, wdrw);
			}
			}
			return returnedValue; //if none of the above is triggered it should return "" which will be read in control flow statement from method
									//, accountSearch, in the ReLoginRun class and will represent THIS menu (account Options, a long with an error message right above it
		}
		boolean exitCheck(String e) {
			boolean extCheck = false; 
			if(e.equalsIgnoreCase("e"))
				extCheck =true; 
			
			return extCheck;
		}
}

