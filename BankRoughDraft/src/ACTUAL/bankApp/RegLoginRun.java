package ACTUAL.bankApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

	public class RegLoginRun {
		static int initialPrompt; //recycled		
		
		
	void greet() {
		System.out.println("     Welcome to my Banking Application");
		System.out.println("=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		System.out.println(" ~Decentralized, Demonitized.. Dewordthree~");
		System.out.println("============================================");
	}
	
	void presentOptions(Scanner scan, RegLoginRun backUp, int adminFl) {
		 
		System.out.println("What would you like to do?");
		System.out.println(" Customer portal - type 1");
		System.out.println(" Employee portal - type 2");
		//int initialPrompt = scan.nextInt();
		String initialPrompt = scan.next();
		//=-=-=-=-=-
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);//<<<< 
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" Enter a number ");
		}
		//====================-==--
		MainExecuted mx = new MainExecuted(); 
		
		if(parseAtt == 1 ) 
		{
			
			backUp.customerLogin(scan, backUp);
			 
		}
		else if(parseAtt == 2 ) {
			Employee emp = new Employee(); 
			if(emp.empCredCheck()) 
			{
				backUp.displayAdmCons(); //I only want this once  -
				backUp.adminConsole(scan, backUp); // this may have to run multiple times depending on user error -
			}else {
				adminFl++; 
				if(adminFl>2) {
					System.out.println("Too many failed attempts to login as an administrator");
					System.exit(1);// exit whole app
				}else {
				System.out.println("Admin password or username, not correct");
				System.out.println("Attempt "+adminFl+" of two allowable login failures");
				System.out.println("");
				backUp.presentOptions(scan, backUp, adminFl);
				}
			}
		}
		else {
			System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("not a readable value..");
			System.out.println("-v----------------v-");
			backUp.presentOptions(scan, backUp, 0);
		}
	}

	boolean loginPortal(Scanner scan, CustomerBlueprint cb, RegLoginRun backUp, int FailCnt){ //called in two places: -6-v- and - 
		
		boolean corrctCreds =false; // initialize as 'false'
		int failCount = FailCnt; // initialize "fail count" [for counting login failures] 
		if(cb.credCheck(scan, cb, backUp) == true) {
			
			return corrctCreds = true;      // re-instantiate as 'true' 
		}else										//! may need to be changed later with serialization and comparing collections
		{
			if(failCount >= 3) {
				return corrctCreds = false; //return false for sure
			}else {
			System.out.println("Sorry! Login was not correct try again! ");
			failCount++; 
			
			if(backUp.loginPortal(scan, cb, backUp, failCount) ==true) //prompt the end user, again, for a true value- 
				return corrctCreds =true;
			else
			return	corrctCreds= false; 
			}//introducing the world's ugliest work around.. ^
			
		}
		 		// return it to whichever function it was called in
	}
								///LOGIN AND REGISTER MENU HANDLING
	void customerLogin(Scanner scan, RegLoginRun backUp) {
		System.out.println(" ____________________       We <3 Customers - Founder");
		System.out.println("|Register - Type '1' |___________________");
		System.out.println("|Login    - Type '2' | (Exit - Type 'e') |");
		System.out.println("-=-=-=-==-=-=-=--=-=-=-=-==-=-==-=-=-=-=-= ");
		System.out.println();
		
		
		String initialPrompt = scan.next();
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" Please enter a number. ");
		}
		if(initialPrompt.equalsIgnoreCase("e")) {
			backUp.presentOptions(scan, backUp, 0);
		}
		else if(parseAtt == 1) 
		{
			CustomerBlueprint cb = new CustomerBlueprint(); 
			cb.setName(custRegister(scan));	
			backUp.passwordProcessing(scan, backUp, cb); //------------------------------1 -v
		}
		else if(parseAtt == 2)
		{
			System.out.println("=-=-=-=-=-=-=-=-Login-Here-=-=-=-=-=-=-=-=");
			CustomerBlueprint cb = new CustomerBlueprint(); //!!!!! CRED CHECK WILL NEED TO BE FIXED AFTER STERIALIZATION!!
			if(cb.credCheck( scan,  cb,  backUp) ==true ) {
				// Menu for users
				backUp.accountStuff(); /// returns a string for comparing to available account numbers - 9-v-
				
			}						
		}
		else
		{
			System.out.println("Not a viable option!");
			backUp.customerLogin(scan, backUp);
		}
	}
							//RETURNS CUSTOMER NAME
	String custRegister(Scanner scan)
	{
		System.out.println("=====================");
		System.out.println("   ---Register---");
		System.out.println("Enter your first name (not same as username for login)");
		String initialPrompt = scan.next();
		CustomerBlueprint cb = new CustomerBlueprint(); 
		cb.setName(initialPrompt);
		
		scan.reset();//clears additional spaces or user-error, addition of last name.. DOESN'T WORK!!!!!!!!!!!!!!!!!!!!!
		return initialPrompt;//should return customer full name.. 
	}
							//PASSWORD PROCESSING method
	void passwordProcessing(Scanner scan, RegLoginRun backUp, CustomerBlueprint cb){
	//=====                                                                            - 1 -^
		scan.reset();//clears additional spaces or user-error, addition of last name.. but doesn't really.. 
	System.out.println("Setup your password, "+ cb.getName()+"! ");
	
	backUp.custPassword(scan, cb, backUp);  //store password to compared in confirm password method (within customerBlueprint) --returned at -2-v-
												// --------------------------------------(4-v-)-----------  3-V-
	
	if(cb.confirmPassword(scan, cb) ==true) {//--------                                                                					     -2-^-
		//System.out.println("true returned"); - prior test - 
		System.out.println("Good job! Now create a unique username, "+ cb.getName()+":");
		backUp.collectUsername(scan, cb, backUp); 
		
	} else
	{
		//System.out.println("true not returned"); //test -		
		backUp.passwordProcessing(scan, backUp, cb); // logic for not NO MATCH                                         					     <-2-^
	} 	
	//----
	}
			
	@SuppressWarnings("unused")
	void accountSearch()    //------------------------  -  -  -    -     -    -    -    -    - -  ------  9-^-
	{
		RegLoginRun rlr = new RegLoginRun(); 
		System.out.println("             -=-=-=-=-=-=-=-=-=-=-");
		System.out.println("             =- Enter account # -=");
		System.out.println("(Enter your account ID to access your account)");
		Scanner scan = new Scanner(System.in); //------------------------------------------                                 --SHIT
		String initialPrompt = scan.next();
		String str = "./ExistingAccounts/"+initialPrompt+"-"+".txt";
		String found= rlr.reviewAccnt(str);
		//System.out.println(found);//test-
		int index =0; 
		String cap = "";
		String passible ="";
		while(index <30) { //this might be redundant logic.. I don't even know at this point.. worth checking later --------! 
				
//			String found= rlr.reviewAccnt(str);
//			System.out.println(found);//test-
			if(found.isEmpty()) {
				// do the not-thing
			}else
			{
				cap =found; //wait... why am I doing this? 
				passible = found;
			}
			index++; 
		}
		//System.out.println(cap);
		if( rlr.openAccnt(cap).equals("firstTime")) {
			boolean zeroBalnce = true; 
			System.out.println("Congratulations on accessing your account for the first time! ");
			System.out.println("-Here are some options: ");
			//split into separate method---
			CustomerBlueprint cb = new CustomerBlueprint(); 
			String determineChoose = cb.accountOptions(scan, cb);
			if(determineChoose =="") {
				//a number that wasn't within parameters for options was returned- eg. 4, 5, 15, etc 
				System.out.println("    *Not a menu option");
				cb.accountOptions(scan, cb);//return them
			}else if(determineChoose=="lv") {
				RegLoginRun backUp = new RegLoginRun(); 
				backUp.customerLogin(scan, backUp);
			}else if(determineChoose =="dpst") {
				Accounts ac = new Accounts();
				
				ac.deposit(passible, str, zeroBalnce);
				//pass an instance of all the info - passible
				//pass the string for the file 	   - str
			} else if(determineChoose =="wthDrw") {
				Accounts ac = new Accounts();
			//	System.out.println("withdraw"); test-
				ac.withdraw(passible, str);
			}else if(determineChoose == "trmnt") {
				System.out.println("Are you sure you want to close this account?");
				System.out.println("   All your money will disapear forever!");
				System.out.println("        We're going to take it all!!");
				System.out.println(" Type-     ( 'y' to delete)");
				System.out.println("   Hitting any other key will exit this prompt");
				String delete = scan.next();
				if(delete.equalsIgnoreCase("y")) {
					if( cb.credCheck(scan, cb, rlr)) {
				File deletingAction = new File(str);
				deletingAction.delete(); 
				System.out.println("Account #"+ initialPrompt+" deleted");
				rlr.customerLogin(scan, rlr);
					}else {
						System.out.println("You do not have permissions to delete this");
						rlr.presentOptions(scan, rlr, 0);
						
					}
				}else {
					rlr.accountSearch();
				}
			}else if(determineChoose == "sts") {
				Accounts ac = new Accounts(); 
				boolean cust = false; // not an admin
				ac.accntInfo(found ,str, cust);
				System.out.println("Hit Enter when you are done reviewing"); 
				scan.nextLine();//waits for enter key from user
				scan.nextLine(); 
				rlr.accountSearch();
			}
			//split into separate method--- (to be placed below, for "been accessed before" scenario - <stupidist thing I could have ever done.. 
		}else 
		{
			boolean zeroBalnce = false; 
			System.out.println("~= Welcome back =~");
			CustomerBlueprint cb = new CustomerBlueprint(); 
			String determineChoose = cb.accountOptions(scan, cb);
			if(determineChoose =="") {
				//a number that wasn't within parameters for options was returned- eg. 4, 5, 15, etc 
				System.out.println("    *Not a menu option");
				cb.accountOptions(scan, cb);//return them
			}else if(determineChoose=="lv") {
				RegLoginRun backUp = new RegLoginRun(); 
				backUp.customerLogin(scan, backUp);
			}else if(determineChoose =="dpst") {
				Accounts ac = new Accounts();
				
				ac.deposit(passible, str, zeroBalnce);   
			} else if(determineChoose =="wthDrw") {
				Accounts ac = new Accounts();
				ac.withdraw(passible, str);
			}
			else if(determineChoose == "trmnt") {
				System.out.println("Are you sure you want to close this account?");
				System.out.println("   All your money will disapear forever!");
				System.out.println("        We're going to take it all!!");
				System.out.println(" Type-     ( 'y' to delete)");
				System.out.println("   Hitting any other key will exit this prompt");
				String delete = scan.next();
				if(delete.equalsIgnoreCase("y")) {
					if( cb.credCheck(scan, cb, rlr)) {
				File deletingAction = new File(str);
				deletingAction.delete(); 
				System.out.println("Account #"+ initialPrompt+" deleted");
				rlr.customerLogin(scan, rlr);
					}else {
						System.out.println("You do not have permissions to delete this");
						rlr.presentOptions(scan, rlr, 0);
						
					}
				}else {
					rlr.accountSearch();
				}
			}else if(determineChoose == "sts") {
				Accounts ac = new Accounts(); 
				boolean notAnAdmin = false; 
				ac.accntInfo(found ,str, notAnAdmin);
				System.out.println("Hit Enter when you are done reviewing");
				scan.nextLine();//waits for enter key from user 
				scan.nextLine(); 
				rlr.accountSearch();
			}
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
		
		System.out.println("Account owner: "+grabName +" "+lstNm);
		//check if the rest is actually money or user information: 
		String check ="0";
		if(found.length() >0) {
		 check = found.substring(found.indexOf('[')+1, found.indexOf(']'));
		}//System.out.println("check in runreg: "+ check); test-
		int parsibility = 0; 
		boolean nParsed = true; 
		try{
			parsibility= Integer.parseInt(check);//this will either be eg. '200', or eg. 'Jim'
		}catch(NumberFormatException nfe) {
			/// not a number
			nParsed = false; 
		}
		// System.out.println("nparsed value in Regrun: " + nParsed); //test-
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
			System.out.print("- Joint Ownership :"+grabName2 +" "+lstNm2);
		}
		System.out.println("=-------------------------------------=");
		String firstTime ="";
		if(found.equals("")) {
			// if it is emptied out, that means it's first time access
			 firstTime ="firstTime";
		}else {
			 firstTime ="notFirst";
		}
		return firstTime; 
	}
	///APPLY FOR ACCOUNT & access existing account 
	void accountStuff() {
		System.out.println("-=-=-=-=-=-=-=-=--=-=");
		System.out.println("  ~ Apply for new account   - Type 1");
		System.out.println("  ~ Access existing account - Type 2");
		Scanner scan = new Scanner(System.in); 
		RegLoginRun rlr = new RegLoginRun(); 
		String initialPrompt = scan.next();
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" Enter a NUMBER, please! ");
			rlr.accountStuff(); //re-run
		}
		if(parseAtt ==1) {
			System.out.println("~ Will this be a joint account?");
			System.out.println("  - Type ('y' or 'n')");
			Random rndm = new Random(System.currentTimeMillis());
			int accntNmbr = rndm.nextInt(30);
			rlr.accountApply(scan, rlr, accntNmbr);
		}else if(parseAtt ==2) {
			rlr.accountSearch();   //we can use a variable to pass this into another method, I think! 
		}
		
	}
	void applyForm(Scanner scan, RegLoginRun rlr, int applying, String accountInfo, String ownerdetails) {         ///  applying variable is passed ---    --- 7-v- 
		CustomerBlueprint cb = new CustomerBlueprint(); 
		boolean scnd = false; 
		String accountName = accountInfo; //unique account name passed the squiggle will diff
		//System.out.println(accountName); TEST- 
		String ownerDetails = ownerdetails;
		
		while(applying >0) {							//!!!!! Serialization may change this!!! 
			if(applying<0) {
				break; 
			}
			if(scnd == true) {
				System.out.println("  ~2nd Person");
				System.out.println("=============================");
			}
			
			System.out.println("What salary do you make? "); //should be able to get this later.. 
			String slry = scan.next();
			
			try {
				int testableNumber = Integer.parseInt(slry);
				cb.setSalary(slry); scan.reset();
				System.out.println("Salary of "+slry+", recorded.");
			}catch (NumberFormatException nfe)
			{
				System.out.println("   Sorry but that is not a readable value");
				System.out.println("   --------------------------------------------------------------------------------------------------==");
				System.out.println("	*You do not need to enter special characters such as '$', '€' or include decimal places or comas ==");
				System.out.println("	*Just include your approxmiate salary in simplest format, eg. '35000'                            ==");
				System.out.println("");
							rlr.applyForm(scan, rlr, applying, accountName, ownerDetails);
							break; /// 							THIS IS WHERE TO CHECK TO SEE IF WE CAN BREAK OUT OF THE WHOLE WHILE
			}
			
			if(scnd == true) {
				System.out.println("What is your first name?");
				String frsName = scan.next(); scan.reset(); 
				cb.setName(frsName);
			}
			if(cb.getName()==null) { // if you are registering from the normal customer login, it won't popup so we need to add it with the trigger-condition being that it is null. 
				System.out.println("What is your first name?");
				String frsName = scan.next(); scan.reset(); 
				cb.setName(frsName);
			}
			System.out.println("What is your last name, "+ cb.getName());
			String lstName = scan.next();
			cb.setLastName(lstName); scan.reset(); 
			System.out.println("Do you have a car, " +cb.getName()+" "+cb.getLastName()+"?");
			System.out.println("  - Type ('y' or 'n')");
			String carPrsnt = scan.next();
			if(carPrsnt.equalsIgnoreCase("y") ) {
				cb.setCar(true);
				System.out.println("Car recorded");
			}
			else
			{//you enter the wrong value then it just assumes no car - 
				cb.setCar(false);
				System.out.println("No car recorded");
			}
			System.out.println("Do you have a house, " +cb.getName()+" "+cb.getLastName()+"?");
			System.out.println("  - Type ('y' or 'n')");
			String hmPrsnt = scan.next();
			if(hmPrsnt.equalsIgnoreCase("y") ) {
				cb.setHouse(true);
				System.out.println("House recorded");
			}
			else
			{//you enter the wrong value then it just assumes no car - 
				cb.setHouse(false);
				System.out.println("No house recorded");
			}
			
			scnd =true; 
			if(ownerDetails.equals("")) {
				System.out.println("");
				System.out.println("Info to save-" + "Name: " +cb.getName()+" "+ cb.getLastName());
				String HouseOwner = ""; 
				String Carowned = null; 
				if(cb.isHouse()) {
					 HouseOwner = " Home-owner";
				}else {
					 HouseOwner =" No house";
				}
				if(cb.isCar()) {
					 Carowned =" car-owner ";
				}else {
					 Carowned =" No car ";
				}
					
				System.out.println("                  Salary: "+ cb.getSalary() + ","+HouseOwner.toString()+","+Carowned.toString());
				System.out.println("");
			}else {
			System.out.println("Info to add to account:" );
			System.out.println("                   Name: " +cb.getName()+" "+ cb.getLastName());
			String HouseOwner = ""; 
			String Carowned = null; 
			if(cb.isHouse()) {
				 HouseOwner = " Home-owner";
			}else {
				 HouseOwner =" No house";
			}
			if(cb.isCar()) {
				 Carowned =" car-owner ";
			}else {
				 Carowned =" No car ";
			}
				
			System.out.println("                     Salary: "+ cb.getSalary() +","+ HouseOwner.toString()+","+Carowned.toString());
			
			System.out.println("");
			}
			String rtrnd= accntDetailsConfirmation(); 
			
			if( rtrnd.equals("tru")) {
				ownerDetails = ownerDetails+ '[' + cb.getName()+']'+'['+cb.getLastName()+']'+'['+cb.getSalary()+']'+'['+cb.isHouse()+']'+'['+cb.isCar()+']';
				scan.reset();//
				//do the thing
				applying -= 1; 
			}else if (rtrnd.equals("fals")) {
			
				
				System.out.println("Here is the form again! ");
				scan.reset();//
				
				rlr.applyForm(scan, rlr, applying, accountInfo, ownerDetails); //represent form.. 
			}else {
				System.out.println("      *invalid input, gurl! Try again!");
				scan.reset();//
				rlr.accntDetailsConfirmation(); // valid input
			} 
		}
		rlr.writeAccount(accountName ,ownerDetails);
		
		scan.nextLine(); 
		System.out.println("=================================");
		System.out.println("   Thank you for submitting");
		System.out.println("  an application for account");
		System.out.println("  with us today! - management");
		System.out.println("");
		System.out.println("  (our top-notch employees are reviewing your application, at this moment!)");
		System.out.println("We will get back to after we have determined you would be a proper fit for us");
		System.out.println("");
		System.out.println("       ~Meanwhile ----------------------------");
		System.out.println("                  Press Enter key to return --");
		System.out.println("       ---------------------------------------");
		scan.nextLine(); 
		
		 rlr.presentOptions(scan, rlr, 0);
	}
	
	String accntDetailsConfirmation() {
		String returned = "";
		Scanner scan = new Scanner(System.in); 
		scan.reset();//
		
		System.out.println("Is this all correct?");
		System.out.println(" ;-D   Yes - Type 1");
		System.out.println(" :-/    No - Type 2");
		String initialPrompt = scan.next();
		//=-=-=-=-=-
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);//<<<< 
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" 	*Enter a number from the below options ");
		}
		//====================-==--
		
		if(parseAtt == 2) 
		{
			return returned = "fals"; 
			 
		}
		else if (parseAtt ==1) {
			//just keep doing the thing
			return returned = "tru"; 
		}else {
			RegLoginRun rlr = new RegLoginRun();
			
			return returned ="maybe";
		}
	}
	
	void accountApply(Scanner scan, RegLoginRun rlr, int idAttempt) {
		//greeting is applied from the, relative prompts
		String accntInfo =""; //this will be passed and will store all account info, in either case (joint or not) 
		String checkExisting="";//for comparing to already approved files
		if(idAttempt == 0) {//can't have an account number of zero.. 
			idAttempt++; 
		}
		//String.valueOf(accntNmbr)
		accntInfo = "./CurrentApplications/"+String.valueOf(idAttempt)+"-"+".txt";
		checkExisting = "./ExistingAccounts/"+String.valueOf(idAttempt)+"-"+".txt";
	//	System.out.println(accntInfo); -test-
		if(rlr.readAccnt(accntInfo)==true & rlr.readAccnt(checkExisting)==true) {//compare in folder for other account-numbers that are the same          -v-v-13- 
			//System.out.println(accntInfo+" exists"); test-
			idAttempt++; //Increment for a new number
			rlr.accountApply(scan, rlr, idAttempt);
			}else 
			{
				System.out.println("Your account-id will be: "+idAttempt);
				System.out.println("**Remember this to look up your account later!");
				//do-the-thing
				
		String initialPrompt = scan.next();
		int jointValue = 1; 
		if(initialPrompt.equalsIgnoreCase("y") || initialPrompt.equalsIgnoreCase("n") )
		{
				if(initialPrompt.equalsIgnoreCase("y")) {
					jointValue++; //table for two 
					String emptyString =""; //empty string is just for initiating owner details on other end
	         	rlr.applyForm(scan, rlr, jointValue, accntInfo, emptyString);  /// joint value is passed into 'applyform()', as 'int applying' -7-^-
				}
				else if(initialPrompt.equalsIgnoreCase("n")) {
					String emptyString ="";
					rlr.applyForm(scan, rlr, jointValue, accntInfo, emptyString);
				}
		}
			
		else {
			System.out.println(" Not a readable value!");
			Random rndm2 = new Random(System.currentTimeMillis());
			int accntNmbr2 = rndm2.nextInt(30);
			rlr.accountApply(scan, rlr, accntNmbr2);
		}
			}	
		 // returns distinct account number/ index for folder
	}
	
								//COLLECT AND COMPARE USERNAME
	void collectUsername(Scanner scan, CustomerBlueprint cb, RegLoginRun backUp){//!!!WILL NEED A WAY TO COMPARE USERNAME TO OTHER USERNAMES ALREADY CREATED!!!!
		
						
		System.out.println("      -     Username can be between 6 and 12 characters long");
		String initialPrompt = scan.next();
		if(initialPrompt.equals(cb.getPassword())) {
			System.out.println("    *Username and Password can not be the same.");
			
			backUp.collectUsername(scan, cb, backUp);// if the username entered is the same as the password print message and try again.. 
			System.out.println("Please enter a username that is different than your password: ");
		}else//otherwise, do-the-thing 
		{
		if(initialPrompt.length() < 12 && initialPrompt.length() >= 6) {
		cb.setUsername(initialPrompt);
		//take user to login page to test credentials
		
		CustomerBlueprint inputCust2 = new CustomerBlueprint(cb.getUsername(), cb.getPassword());// < this apparently doesn't work.. 
		// can I serialize just PURE STRING AND THEN READ THE PURE STRING, instead? 												<=-=-=!!!!!!!!<
		
		String inputCust = cb.getUsername() + "-"+cb.getPassword();
		
		char ch = cb.getUsername().charAt(0);
		char ch2 = cb.getUsername().charAt(1);
		 int index =(int)ch;
		int index2 =  (int)ch2;
		char pch = cb.getUsername().charAt(2);
		char pch2 = cb.getUsername().charAt(3);
		int pInd = (int)pch;
		int pInd2 = (int)pch2; 		
		char pch3 = cb.getUsername().charAt(4);
		char pch4 = cb.getUsername().charAt(5);
		int pInd3 = (int)pch3;
		int pInd4 = (int)pch4; 	//grabs the first six characters of any username and searches for it in the  database (using readObject method --v--) 
								//rnd account number - 
		int indxChecker = 0; 
						 //for cycling through current filenames - 
		String filename = "./Customers/Cust"+"-"+index+"-"+index2+"-"+pInd+"-"+pInd2+"-"+pInd3+"-"+pInd4+".txt"; //<attempted name 	-				
	//	String filename = "./Admin/EmployeeAdmin.txt"; //<temporary for our employee/ admin
	//	String fileCheck = "./Cust"+"-"+index+"-"+index2+"-"+pInd+"-"+pInd2+".txt";	
		
		//read folder for any other that might have this account number - 
		FileNotFoundException e;
						
		
			if(readObject(filename)== true) ///< found: continue loop - 								//RIGHT HERE!!!!!!!!!!!!!
			{
					System.out.println("       *Username in use");
					System.out.println("Create a unique username: ");
					backUp.collectUsername(scan, cb, backUp); //try again - 
					
			}
			else {
				System.out.println("Username accepted!");		
				writeObject(filename, inputCust); 	
				 									//	RIGHT HERE!!!!!!!! 
			}				
																		
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-");
		System.out.println("Let's test those newly created credentials real quick by having you login!");
		//take end user to login portal.. 
		int failCount = 0; 
			if(backUp.loginPortal(scan, cb, backUp, failCount)==true) {//---calling 'loginPortal(1,2,3), above -^-6-
				System.out.println("      login successfull!");
				System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
				System.out.println("~ Will this be a joint account?");
				System.out.println("  - Type ('y' or 'n')");
				Random rndm = new Random(System.currentTimeMillis());
				int accntNmbr = rndm.nextInt(30);
				backUp.accountApply(scan, backUp, accntNmbr);
			}else 
			{
				//return to main menu or something
				System.out.println("=====================================================");
				System.out.println("Too many failed attempts - Returning you to main menu");
				presentOptions(scan, backUp, 0); 
			}						
		}
		else {
			int TooMnyBy = initialPrompt.length() -12; 
			
			if(TooMnyBy > 0) {//check for negatives
			System.out.println("Your username was "+TooMnyBy+ " characters too long! Shorten it up!");
			backUp.collectUsername(scan, cb, backUp);//give user another chance
			}
			else if(initialPrompt.length() <6 )
			{
				System.out.println("Your username was "+ (Math.abs(TooMnyBy) -6)+" characters too short! Give it some meat!");
				
				backUp.collectUsername(scan, cb, backUp );//give user another chance
			}
		}
	}//end of else statement.. 
	}//end of actual method
									//INITIAL PASSWORD ENTRY
	String custPassword(Scanner scan, CustomerBlueprint cb, RegLoginRun backUp) { //--very first call is 		3-^-
		cb.setPassword(null);//set to null again
			System.out.println("  -  NO special characters, ex. *, #, $, ', etc. ");
			System.out.println("  -  The password must contain a NUMBER");
			System.out.println("  -  Password must be 8 characters long");
			System.out.println("  -  Password must have a mix of upper case and lower case characters:");
			
		String initialPrompt = scan.next();
	
		char[] capsCheck = initialPrompt.toCharArray();//for cycling - 
		boolean upper = false;
		boolean lower =false; 
		boolean numberPrsnt= false; 
		boolean failed = false; 
		for(int inx =0; inx < capsCheck.length; inx++) {
			if( Character.isUpperCase( capsCheck[inx] )) {			
				upper =true; 
			}
			if( Character.isLowerCase( capsCheck[inx] )) {
				lower =true; 
			}
			if( Character.isDigit( capsCheck[inx] )) {
				numberPrsnt =true; 
			}
		}
		if(numberPrsnt != true) {
			System.out.println("     *Please include a number");
			System.out.println(" ");
		//	cb.setPassword(null);//set to null again
			failed = true; 
		}
		if(lower != true) {
			System.out.println("     *Please include a lowercase character");
			System.out.println(" ");
		//	cb.setPassword(null);//set to null again
			failed = true; 
			
		}
		if(upper !=true) {
			System.out.println("     *Please include an uppercase character");
			System.out.println(" ");
		//	cb.setPassword(null);//set to null again
			failed = true; 
			
		}
		if(initialPrompt.length() <8) {//easy shortness check -
			System.out.println("     *Password must be a minimum of 8 characters long");
		//	cb.setPassword(null);//set to null again
			failed = true; 
			
		}	
		if(failed  == true ) {
			backUp.custPassword(scan, cb, backUp);
			return "failure";
		}else {
			cb.setPassword(initialPrompt);
			return initialPrompt;						//   -------                --returned -^4-	
		}
		
	}
	//=======================EMPLOYEE STUFF--------------------------------------
	void displayAdmCons() {
		System.out.println("================================");
		System.out.println("   ~Welcome Administrator~  ");
		System.out.println("------------=-=-----------------");
		System.out.println(" -  Review Applications - Type 1");
		System.out.println(" -  Find an Account     - Type 2"); //<--v-
		//cancel option here. view logs here.. personal information through here
		System.out.println(" ====================== - Type 'e' to exit");
	}
	void adminConsole(Scanner scan, RegLoginRun backUp){
		System.out.println("========================--------");
		System.out.println("============== Admin console-===");
		System.out.println("================================");
		String initialPrompt = scan.next();
		if(initialPrompt.equalsIgnoreCase("e"))
			backUp.presentOptions(scan, backUp, 0);
			
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" Enter a number ");
			backUp.adminConsole(scan, backUp);
			backUp.adminConsole(scan, backUp);//Opportunity to try again (without popping the whole display menu again. 
		}
		if(parseAtt == 1) 
		{
			RegLoginRun rlr = new RegLoginRun(); 
				System.out.println("=========================================== ");
				System.out.println(" misa' approve only very rich and wise ppl");
				System.out.println(" with muya muya mullah!! heee heeheheheehee!");
				System.out.println("===========================================");
				System.out.println("          --Application Console--");
				rlr.applicationsToApprove(rlr);
		}
		else if(parseAtt == 2) {
				backUp.searchAccount(scan, backUp); 
		}

	}
	void applicationsToApprove(RegLoginRun rlr) {
		
		boolean onePrint = true; 
		
		
		int index = 1; 
		//for(int i =1; i<30; i++) {
		while(index <30) {
		String str = "./CurrentApplications/"+index+"-"+".txt";
		int id = index; 
		String found= rlr.reviewAccnt(str);
		
			if(found.isEmpty()) {
				//do nothing
			}	else {
				if(onePrint== true) {
					System.out.println("Account ID -    Name           -  Salary - HouseOwner - CarOwner  ");
					System.out.println("--");
					onePrint =false; 
				}
				StringBuilder sb = new StringBuilder(found);
				int ran =0; 
				boolean secondRide = false; 
				while(found.length() >0) {
					
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
				if( found.equals("")){
					//System.out.println("--");
				}
				
				if(found.length() >3 & secondRide == true )//just using 3 to account for random spaces during testing- 
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
			
					
					String between1 = "";
					String space1 = " "; 
					for(int lengthDetermine1 = (grabName + lstNm).length();lengthDetermine1<21; lengthDetermine1++ ) {
						between1 = between1 + space1;
					}
					String between2 = "";
					for(int i=0; sal.length() +i< 9;  i++) {
						between2 = between2 + space1;
					}
					String between3 = "";
					if(hs.equals("false")) {
						between3 ="        ";
					}else {
						between3= "         ";
					}
					//estimate spacing-^-v-
					int th = String.valueOf(id).length();
					String unused="";
					if(th <2) {
						 unused = " ";
					}
			System.out.println(" "+unused+"      "+grabName+" "+lstNm +" "+between1+ sal +between2+hs+between3+co);
				}
				//-=-=spacing
				String between1 = "";
				String space1 = " "; 
				for(int lengthDetermine1 = (grabName + lstNm).length();lengthDetermine1<21; lengthDetermine1++ ) {
					between1 = between1 + space1;
				}
				String between2 = "";
				for(int i=0; sal.length() +i< 9;  i++) {
					between2 = between2 + space1;
				}
				String between3 = "";
				if(hs.equals("false")) {
					between3 ="        ";
				}else {
					between3= "         ";
				}
				//estimate spacing-^-v-
				int th = String.valueOf(id).length();
				String unused="";
				if(th <2) {
					 unused = " ";
				}
		System.out.println(id+unused+"         "+grabName+" "+lstNm +" "+between1+ sal +between2+hs+between3+co); //no no.. I know.. there is definatley a cleaner way to do the above.. I disgust myself, actually
			//	System.out.println(sb.toString());
				secondRide =true; 
				}
			}	
			index++; 
		}
		System.out.println("-");
		if(onePrint ==false) {
			System.out.println("");
		System.out.println("Type in the ID of the account you'd wish to approve, based on the provided info, above:");
		}else {
			System.out.println("");
			System.out.println("There are currently no applications to process..");
			System.out.println("");
		}
		System.out.println("Enter 'e' to exit");
		
		rlr.takeAction();		
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
	void takeAction() {
		RegLoginRun rlr = new RegLoginRun(); 
		
		Scanner scan = new Scanner(System.in);
		String initialPrompt = scan.next();
		if(initialPrompt.equalsIgnoreCase("e")) {//-------------------------------------------------------
			rlr.displayAdmCons();
			rlr.adminConsole(scan, rlr);
		}else {
		//=-=-=-=-=-
		int parseAtt=0; 
		try {
		parseAtt = Integer.parseInt(initialPrompt);//<<<< 
		}
		catch (NumberFormatException nfe)
		{
			System.out.println(" 	*"+initialPrompt+" is not a number ");
			rlr.takeAction();
		}		
		if(parseAtt != 0) {
			//file reference-er-izer
			String file=	"./CurrentApplications/"+parseAtt+"-"+".txt";
			//read/ return old data
		String userData=	rlr.saveFile(file);
		//	System.out.println(userData); test-
			//save this to the approved account folder- 
			String approvalGiven = "./ExistingAccounts/"+parseAtt+"-"+".txt";
			rlr.writeAccount(approvalGiven,userData);
			
			
			//finally, delete the old file
		File deletingAction = new File(file);
		deletingAction.delete(); 
		System.out.println("Enter ID of account to approve (Enter 'e' to exit): ");
		rlr.takeAction(); 
		}
		}
	}
	 String saveFile(String filename)
		{
			 	String workplz ="";
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
			{
				Object obj = ois.readObject();  // de-serialization
				workplz = obj.toString();
				// returns if txt file of a particular name is found
			} 
			catch (FileNotFoundException e)
			{
				//e.printStackTrace();   <--- we don't want this to print but this is a good thing! :) 
				
				// returns if a file of a particular name is not found
			}
			catch (IOException e) 
			{
				//e.printStackTrace();
				
				 // returns if a file of a particular name is not found
			} 
			catch (ClassNotFoundException e)
			{
				//e.printStackTrace();
			
				// returns if a file of a particular name is not found
			}			
		return workplz; 		
		}
	void searchAccount(Scanner scan, RegLoginRun backUp) {
		System.out.println("--=-=-=-=-=-=-=-=-=-=-=-=-=-=--");
		System.out.println("= Search via account number..");
		System.out.println("= Enter account number: ");
		String initialPrompt = scan.next();
		String filename = "./ExistingAccounts/"+initialPrompt+"-.txt";
		Accounts ac = new Accounts(); 
		String found=  ac.reviewAccnt(filename) ;// should retrieve the droids we're looking for
		//System.out.println("found : "  +found); test-
		boolean Admin = true; 
		ac.accntInfo(found, filename, Admin);   /////shit
	///moving over to other things after...
	}
	static void writeObject(String filename, String obj)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
		{
			oos.writeObject(obj);   // serialization
		} 
		catch (IOException e) 
		{
			//e.printStackTrace(); nothing for now.. 
		}
	}
	void writeAccount(String accountName, String ownerDetails)
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accountName)))
		{
			System.out.println("  *Approved* ");
			oos.writeObject(ownerDetails);   // serialization adding a 1 at the beginning as to signify that the account has not been approved, yet
		//	System.out.println("written: " + ownerDetails); test-
		} 
		catch (IOException e) 
		{
			System.out.println("ioexception.. ");
			//e.printStackTrace(); nothing for now.. 
		}
	}
	
	 boolean readObject(String filename)
	{
		 	boolean work = true; 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
		{
			Object obj = ois.readObject();  // de-serialization
			
			// returns if txt file of a particular name is found
		} 
		catch (FileNotFoundException e)
		{
			//e.printStackTrace();   <--- we don't want this to print but this is a good thing! :) 
			work = false; 
			// returns if a file of a particular name is not found
		}
		catch (IOException e) 
		{
			//e.printStackTrace();
			
			 // returns if a file of a particular name is not found
		} 
		catch (ClassNotFoundException e)
		{
			//e.printStackTrace();
		
			// returns if a file of a particular name is not found
		}			
	return work; 		
	}
	 boolean readAccnt(String filename)//------------------------				--^-13- use f+'cntr' on '13'
		{
		// System.out.println("even called?");
			 	boolean match = false; 
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
			{
				Object obj = ois.readObject();  // de-serialization
				System.out.println("making it here?");
				
				//match = true; something in here is causing an IOException (in the actual try )
			} 
			catch (FileNotFoundException e)
			{
				//e.printStackTrace();   <--- we don't want this to print but this is a good thing! :) 
				match = false; 
				
			//	System.out.println("                              Application started ");
				// returns if a file of a particular name is not found
			}
			catch (IOException e) 
			{
				//e.printStackTrace();
				 // returns if a file of a particular name is not found
				System.out.println("landing here?");
				match = true; 
				 // I don't understand this exception but it seems to 
				//  still indicates that there is a file of that title..  we don't build bridges.. running with it..
			} 
			catch (ClassNotFoundException e)
			{
				//e.printStackTrace();
				System.out.println("You did something very wrong and should feel ashamed.");
				// returns if a file of a particular name is not found
			}			
		return match; 		
		}
		
}// end of class
	
	
	