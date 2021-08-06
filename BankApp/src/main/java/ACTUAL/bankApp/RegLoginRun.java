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
			System.out.println("Nice to meet ya, "+cb.getName()+"!");
			
			System.out.println();
			RandomGen rg= new RandomGen(); 
			String RandomAttmpt = rg.pseudoRandomPasswordGenerator();
			//for some reason it will spam a 'Z' and still create it..eg. 3ZZZZZZZZ idk why
			
			
			while(RandomAttmpt.equals("failed") ||rg.checkZneaky(RandomAttmpt)== false) {	
				RandomAttmpt = rg.pseudoRandomPasswordGenerator();
			}			
				rg.confirmOrDeny(scan, backUp, cb);//see class: 'RandomGen' for related methods			
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
		
		System.out.println("             =- Enter account # -=");
		System.out.println("(Enter your account ID to access your account)");
		System.out.println("          ~ 'e' for exit, commrad ~ ");
		
		Scanner scan = new Scanner(System.in); //------------------------------------------                           
		String initialPrompt = scan.next();
		if(initialPrompt.equalsIgnoreCase("e")) {
			rlr.accountStuff();// go back! 
		}
		int parseAtt = 0; 
		try {
			parseAtt = Integer.parseInt(initialPrompt); 
		}catch (NumberFormatException nfe)
		{
			System.out.println(" **Enter a NUMBER, please! ");
			System.out.println("             ---------------------");//a less "poppy" second instance of the menu. 
			rlr.accountSearch(); //re-run
		}
		SQLConnections sqlc = new SQLConnections(); 
		String accountIdPassible = String.valueOf(parseAtt);
	 if(sqlc.retrieveAccount(accountIdPassible)) {
		 CustomerBlueprint cb = new CustomerBlueprint(); 
		 System.out.println();
		 cb.accountOptions(scan, cb, accountIdPassible);
	 }	 
	 else {
		 System.out.println(" Did not validate properly ");	
	 rlr.accountSearch();
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
		//FLEXIN 
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
			rlr.accountApply(scan, rlr, accntNmbr);//joint
		}else if(parseAtt ==2) {
			System.out.println("             -=-=-=-=-=-=-=-=-=-=-");
			rlr.accountSearch();   //we can use a variable to pass this into another method, I think! 
		}
		
	}
	void applyForm(Scanner scan, RegLoginRun rlr, int applying, String accountInfo, String ownerdetails, boolean lastIteration) {         ///  applying variable is passed ---    --- 7-v- 
		CustomerBlueprint cb = new CustomerBlueprint(); 
		System.out.println("APPLY FORM BEGIN:");//SHIT
		boolean scnd = false; 
		String accountName = accountInfo; //unique account name passed the squiggle will diff
		//System.out.println(accountName); TEST- 
		String ownerDetails = ownerdetails;
		 lastIteration = false; 
		//SHIT
		 String dillinger ="";
		while(applying >0) {							//!!!!! Serialization may change this!!! 
			if(applying<0) {
				break; 
			}
			if(scnd == true) {
				System.out.println("  ~2nd Person");
				System.out.println("=============================");
				//THS IS WHERE WE CAN ASK THEM IF THEY WANT A USERNAME AND PASSWORD TOO! 
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
							rlr.applyForm(scan, rlr, applying, accountName, ownerDetails, lastIteration);
							break; /// 							THIS IS WHERE TO CHECK TO SEE IF WE CAN BREAK OUT OF THE WHOLE WHILE
			}
			
			if(scnd == true) {//all second person stuff
				System.out.println("What is your first name?");
				String frsName = scan.next(); scan.reset(); 
				cb.setName(frsName);
				System.out.println("What user-name would you like, "+cb.getName()+"?");
				String scndUserUsername = scan.next(); 
				cb.setUsername(scndUserUsername);
				System.out.println("Your username is now: " + scndUserUsername);
				System.out.println();
				System.out.println("Set a password: ");
				String newPassword= rlr.custPassword(scan, cb, rlr); // second user is accountable to same rules
				cb.setPassword(newPassword);
				System.out.println();
				System.out.println("Your password has been set to " + cb.getPassword());
				System.out.println();
			}
			if(cb.getName()==null) { // if you are registering from the normal customer login, it won't popup so we need to add it with the trigger-condition being that it is null. 
				System.out.println("What is your first name?");
				String frsName = scan.next(); scan.reset(); 
				cb.setName(frsName);
			}
			System.out.println("What is your last name, "+ cb.getName());
			String lstName = scan.next();
			cb.setLastName(lstName); scan.reset(); 
			System.out.println(cb.getLastName() +" recorded");
			System.out.println();
			System.out.println("Do you have a car, " +cb.getName()+" "+cb.getLastName()+"?");
			System.out.println("  - Type ('y' or 'n')");
			String carPrsnt = scan.next();
			if(carPrsnt.equalsIgnoreCase("y") ) {
				cb.setCar(true);
				System.out.println("Car recorded");
				System.out.println();
			}
			else
			{//you enter the wrong value then it just assumes no car - 
				cb.setCar(false);
				System.out.println("No car recorded");
				System.out.println();
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
			String rtrnd= accntDetailsConfirmation(); //YES OR NO OPTION
			
			if(lastIteration==true) {
				SQLConnections sqlc = new SQLConnections(); 
				sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(), sqlc.freshUser(3));
			//	System.out.println("break out?"); test-
				dillinger= dillinger+" "+ cb.getUsername();
			//	System.out.println("passing in to sqlc"); -test-
				///----------------------------------------------------------------------------------------------------log created here v-
				// grabLogID( createLogQuery() );  
				sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(), sqlc.createNewAccount(dillinger, true)  );
				applying = -1; 
				break; 
				
			}
			else if( rtrnd.equals("tru") & scnd != true & lastIteration!=true) {
				//System.out.println("REACHED THE FIRST USER RUN"); -test-
				
				SQLConnections sqlc = new SQLConnections(); 
				//System.out.println("before sql class: "+cb.getLastName()+" " + cb.getSalary()+" " );
				sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(),sqlc.firstUser());
				dillinger = cb.getUsername() ;
				if(applying<=1) {
					//System.out.println("REACHED!!!!!"); //-test-
					///----------------------------------------------------------------------------------------------log created here v-
					sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(), sqlc.createNewAccount(dillinger, false)  );
					//single user shit for account shit
				}////
				lastIteration= true; 
				scnd =true; 
				scan.reset();//
				//do the thing
				applying -= 1; 
			}else if(rtrnd.equals("tru")& scnd == true & lastIteration == true) {
				SQLConnections sqlc = new SQLConnections(); 
				sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(), sqlc.freshUser(3));
			//	System.out.println("REACHED THE SECOND USER RUN"); test-
			}
			else if(lastIteration==true) {
				
				//System.out.println("REACHED THE SECOND USER RUN");
				//System.out.println("extrastatement?");
				applying = -1; 
				break; 
				
			}
			else if (rtrnd.equals("fals")) {
			
				
				System.out.println("Here is the form again! ");
				scan.reset();//
				
				rlr.applyForm(scan, rlr, applying, accountInfo, ownerDetails, lastIteration); //represent form.. 
			}else {
				System.out.println("      *invalid input, gurl! Try again!");
				scan.reset();//
				rlr.accntDetailsConfirmation(); // valid input
			} 
		}
		
		
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
				
				//do-the-thing
				
		String initialPrompt = scan.next();
		int jointValue = 1; 
		if(initialPrompt.equalsIgnoreCase("y") || initialPrompt.equalsIgnoreCase("n") )
		{
				if(initialPrompt.equalsIgnoreCase("y")) {
					jointValue++; //table for two 
					String emptyString =""; //empty string is just for initiating owner details on other end
	         	rlr.applyForm(scan, rlr, jointValue, accntInfo, emptyString, false);  /// joint value is passed into 'applyform()', as 'int applying' -7-^-
				}//sql shit
				else if(initialPrompt.equalsIgnoreCase("n")) {
					String emptyString ="";
					rlr.applyForm(scan, rlr, jointValue, accntInfo, emptyString, false); //sql shit 
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
		if(initialPrompt.length() < 20 && initialPrompt.length() >= 6) {
		cb.setUsername(initialPrompt);
		//take user to login page to test credentials
		
		SQLConnections sqlc = new SQLConnections(); 
		
		Random fluffseed = new Random();
		int seed = fluffseed.nextInt(14);
		sqlc.inputConnections(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword(), sqlc.userSignUp(seed));
																
		System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-");
		System.out.println("Let's test those newly created credentials real quick by having you login for first time!");
		//take end user to login portal.. 
		int failCount = 0; 
			if(backUp.loginPortal(scan, cb, backUp, failCount)==true) {//---calling 'loginPortal(1,2,3), above -^-6-
				System.out.println("      login successfull!");
				System.out.println("$-$-$-$-$-$-$-$-$-$-$-$-$-$-$-$");
				System.out.println("~ Will this be a joint account?");
				System.out.println("  - Type ('y' or 'n')");
				Random rndm = new Random(System.currentTimeMillis());
				int accntNmbr = rndm.nextInt(30);
				backUp.accountApply(scan, backUp, accntNmbr);//shit
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
		System.out.println("========================--------");
		System.out.println("============== Admin console-===");
		System.out.println("================================");
	}
	void adminConsole(Scanner scan, RegLoginRun backUp){
		
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
				System.out.println(" ====Approve the Customer Application====");
				System.out.println(" ========================================");
				System.out.println("===========================================");
				System.out.println("          --Application Console--");
				rlr.applicationsToApprove(rlr);
				
		}
		else if(parseAtt == 2) {
				String rtrnAid=  backUp.searchAccount(scan, backUp);
				System.out.println("~-------------------------------------------------------~");
				System.out.println(" ~-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-~");
				System.out.println("  ~ Press '1' to view Transaction Log of this account ~ ");
				System.out.println("   ~     ( Type 'e' to logout when you're done )     ~");
			//	System.out.println("THE LOG RETURNED WAS " + returnedLogId);
				 initialPrompt = scan.next();
				 
				if(initialPrompt.equalsIgnoreCase("e")) {
					backUp.displayAdmCons();
				backUp.adminConsole(scan, backUp);
				}else {
				 parseAtt=0; 
				try {
				parseAtt = Integer.parseInt(initialPrompt);
			
				}
				catch (NumberFormatException nfe)
				{
					
					backUp.displayAdmCons();
					backUp.adminConsole(scan, backUp);//Opportunity to try again (without popping the whole display menu again. 
				}
				if(parseAtt == 1) {
					
					SQLConnections sqlc = new SQLConnections(); 
					sqlc.printAccountHistory(rtrnAid);//check
					System.out.println("   ~     ( Enter 'e' to logout when you're done )     ~");
					String newPrompt = scan.next();
					
					
					if(newPrompt.equalsIgnoreCase("e")) {
						backUp.displayAdmCons();
					backUp.adminConsole(scan, backUp);}
				}
				}
		}

	}
	void applicationsToApprove(RegLoginRun rlr) {
			SQLConnections sqlc = new SQLConnections(); 
			sqlc.reviewAppConnection();
		
			System.out.println("~                                                         ~");
			System.out.println("  Enter the application to approve customer('s) account! ");
				System.out.println();
			System.out.println("(Press 'e' to escape back to Admin Console)");
		rlr.takeAction(false);		
	}
	
	void takeAction(boolean rnd2) {
		
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
			rlr.takeAction(false);
		}		
		if(parseAtt==1) {
			SQLConnections sqlc = new SQLConnections(); 
			sqlc.reviewAppConnection();
			System.out.println("~                                                         ~");
			System.out.println("  Enter the application to approve customer('s) account! ");
				System.out.println();
			System.out.println("(Press 'e' to escape back to Admin Console)");
			System.out.println("           [See pending applications again] ");
			System.out.println("                           [   Press '1'  ]");
			rlr.takeAction(true);
		}else {
		if(parseAtt != 0) {
		String	parseAtt2 = String.valueOf(parseAtt);//I just parsed it to ensure that they didn't enter
		// some random string in the first place
		SQLConnections sqlc = new SQLConnections(); 
	if(sqlc.approveApp(parseAtt2))
		System.out.println("Successfully updated application, '"+parseAtt2+", to account: #"+parseAtt2);
	//-------everytime v-- 
	System.out.println("~                                                         ~");
	System.out.println("  Enter the application to approve customer('s) account! ");
		System.out.println();
	System.out.println("(Press 'e' to escape back to Admin Console)");
	System.out.println("           [See pending applications again] ");
	System.out.println("                           [   Press '1'  ]");
	
	
		rlr.takeAction(true); //you can do this until you decide to hit 'e'
		}
		}
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
	String searchAccount(Scanner scan, RegLoginRun backUp) {
		String returnedLog = "";
		System.out.println("--=-=-=-=-=-=-=-=-=-=-=-=-=-=--");
		System.out.println("=-Search via account number..-=");
		System.out.println("=-Enter account number(or'e')-=");
		//logic here sql fart
		RegLoginRun rlr = new RegLoginRun(); 
		String initialPrompt = scan.next();
		if(initialPrompt.equalsIgnoreCase("e")) {
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
			rlr.searchAccount(scan, rlr);
		}	
		String account_search = String.valueOf(parseAtt); 
		SQLConnections sqlc = new SQLConnections(); 
		 returnedLog =sqlc.searchAccount(account_search);
		 returnedLog =account_search; 
		}
		return returnedLog;
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
	
	
	