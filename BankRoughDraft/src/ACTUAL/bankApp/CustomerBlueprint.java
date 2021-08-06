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
	private String lastName; 
	private String salary; 
	private boolean house; 
	private boolean car;
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
		
		String foundPW ="";
		String foundUN ="";		

		char ch = usernmEntry.charAt(0);
		char ch2 = usernmEntry.charAt(1);
		 int index =(int)ch;
		int index2 =  (int)ch2;
		char pch = usernmEntry.charAt(2);
		char pch2 = usernmEntry.charAt(3);
		int pInd = (int)pch;
		int pInd2 = (int)pch2; 		
		char pch3 = usernmEntry.charAt(4);
		char pch4 = usernmEntry.charAt(5);
		int pInd3 = (int)pch3;
		int pInd4 = (int)pch4;
		
		String filename = "./Customers/Cust"+"-"+index+"-"+index2+"-"+pInd+"-"+pInd2+"-"+pInd3+"-"+pInd4+".txt";		
		String returnedContent = cb.readObject(filename); 
		StringBuilder sb = new StringBuilder(returnedContent);
		int toCut = sb.indexOf("-");
		 foundPW = sb.substring(toCut +1);//should grab everything after the dash		
		
		 try {
		 foundUN= sb.substring(0, toCut);//straight up... 
		 }catch(IndexOutOfBoundsException  ifx) {}
		 
		 //will throw indexoutofboundsexception... needs some sort of trycatch... (problem)
		 
		if(usernmEntry.equals(foundUN) & psswrdEntry.equals(foundPW)) //evaluate credentials, comparing user data to current text file data.. 
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
	 String readObject(String filename)
		{
		 String stringable="";
			 	boolean work = true; 
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
			{
				Object obj = ois.readObject();  // de-serialization
			//	System.out.println(obj.toString()); //test- 
						stringable = obj.toString();		
				//System.out.println("even making it here? "); //test- 
				// returns if txt file of a particular name is found
			} 
			catch (FileNotFoundException e)
			{
				//e.printStackTrace();   <--- we don't want this to print but this is a good thing! :) 
				work = false; 
				System.out.println("");
				System.out.println("This user does not exist ");
				System.out.println("");
				// returns if a file of a particular name is not found
				RegLoginRun rlr = new RegLoginRun(); 
				Scanner scan = new Scanner(System.in); 
				rlr.customerLogin(scan, rlr);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("error IOException");
				 // returns if a file of a particular name is not found
			} 
			catch (ClassNotFoundException e)
			{
				//e.printStackTrace();
				System.out.println("error class not found");
				// returns if a file of a particular name is not found
			}			
		return stringable; 		
		}
		String accountOptions(Scanner scan, CustomerBlueprint cb) {
			System.out.println("Withdraw      - type '1'     -- RETIRE ACCOUNT (Type '4')");
			System.out.println("Deposit        - type '2'       This option will close");
			System.out.println("Account Status  - type '3'      the account and send all");
			System.out.println(" (and history)                  assets into oblivion....");
			System.out.println("       (Logout  =- type 'e') -=");
			String initialPrompt = scan.next();
			String returnedValue ="";
			if(initialPrompt.equalsIgnoreCase("e")) {
				returnedValue ="lv";
			}else {
			//=-=-=-=-=-
			int parseAtt=0; 
			try {
			parseAtt = Integer.parseInt(initialPrompt);//<<<< 
			}
			catch (NumberFormatException nfe)
			{
				System.out.println(" ** Enter a number ");
				cb.accountOptions(scan, cb);
			}
			//====================-==--
			
			if(parseAtt == 1) {
				returnedValue = "wthDrw";
			}else if (parseAtt ==2) {
				returnedValue = "dpst";
			}
			else if(parseAtt ==4) {
				returnedValue ="trmnt";
			}else if(parseAtt ==3) {
				returnedValue ="sts";
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

