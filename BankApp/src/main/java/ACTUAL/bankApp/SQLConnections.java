package ACTUAL.bankApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SQLConnections {
	
	private static String url= "jdbc:oracle:thin:@jponder23.cv1rmjxwr5fp.us-east-2.rds.amazonaws.com:1521:orcl";

	private static String username="myusfdb";

	private static String password= "#3Marine33";
	//--==========================\
	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		SQLConnections.url = url;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		SQLConnections.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		SQLConnections.password = password;
	}
	//--/
	
	public static void inputConnections(String url, String id, String namingAttmept, String sql  ) {
		
		try(Connection conn =
				DriverManager.getConnection(url, id, namingAttmept))
		{
			Statement statement = conn.createStatement();
			int numOfRowsChanged = statement.executeUpdate(sql);
	//		System.out.println("The # of rows changed: "+ numOfRowsChanged); //-test-
			
		}catch(SQLIntegrityConstraintViolationException d) {
			SQLConnections sqlc = new SQLConnections(); // we should be able to dodge the unique key constraint with this 
									//and still add a small element of pseudo-random to the incrementation of the Cid 
			String adjust = sqlc.userSignUp(15);
			sqlc.inputConnections(url,id,namingAttmept, adjust);
			System.out.println(" -"); //just something to show we had to re-seed in order to find a unique value
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public String userSignUp(int fluffSeed) {
		Random rnd = new Random(); 
		Random rnd2 = new Random(); 
		fluffSeed = Math.abs(fluffSeed); 
		int fluff=0;
		try{ fluff = rnd.nextInt(fluffSeed);
		}catch(IllegalArgumentException e) {
			fluff=1; //oh well.. at least we can catch it
		}
		boolean uPdN = rnd2.nextBoolean(); 
		if(uPdN)
			fluff= fluff - (fluff*2);//half the time it will drop so we don't waste a whole
												// bunch of sequence oppertunities going up too fast. 
		
		CustomerBlueprint cb = new CustomerBlueprint(); 
		cb.getName();
	String	passingStatement = "INSERT INTO CustomerTable(Cid, First_name, Username, Password_) "+
				"VALUES("+"custIDgen.NEXTVAL+"+fluffSeed+", "
						+ "'" + cb.getName()+ "','"+cb.getUsername()+"','"+cb.getPassword()+"' )";
	//System.out.println(" passing statement reached ! ");
		
		return passingStatement; 
	}
	public String firstUser() {
		CustomerBlueprint cb = new CustomerBlueprint(); 
		String HouseLiver = "No";
		String cardriver = "No"; 
		
	//	System.out.println(cb.isHouse()); test-
		if(cb.isHouse())
			 HouseLiver = "Yes";
		
	//	System.out.println(cb.isCar()); test-
		if(cb.isCar())
			cardriver = "Yes";
			
		String	passingStatement = "UPDATE CustomerTable SET Last_Name='"
		+cb.getLastName()+"', SALARY='"+cb.getSalary()+"',carowner='"+cardriver+"',homeowner='"+HouseLiver
				+ "' WHERE Username ='"+cb.getUsername()+"'";
	//	System.out.println("why doesn't this update (passing statement created for first user(=): " +passingStatement); test-
		return passingStatement; 
	}
	public String freshUser(int fluffSeed) {
		
		CustomerBlueprint cb = new CustomerBlueprint(); 
		
		Random rnd = new Random(); 
		Random rnd2 = new Random(); 
		fluffSeed = Math.abs(fluffSeed); 
		int fluff=0;
		try{ fluff = rnd.nextInt(fluffSeed);
		}catch(IllegalArgumentException e) {
			fluff=1; //oh well.. at least we can catch it
		}
		boolean uPdN = rnd2.nextBoolean(); 
		if(uPdN)
			fluff= fluff - (fluff*2);//half the time it will drop so we don't waste a whole
												// bunch of sequence oppertunities going up too fast. 
		
		String HouseLiver = "No";
		String cardriver = "No"; 
		
	//	System.out.println(cb.isHouse()); test-
		if(cb.isHouse())
			 HouseLiver = "Yes";
		
	//	System.out.println(cb.isCar()); test- 
		if(cb.isCar())
			cardriver = "Yes";
		
		String	passingStatement = "INSERT INTO CustomerTable(Cid, First_name, Last_Name, Username, Password_, Salary, carowner, homeowner) "+
				"VALUES("+"custIDgen.NEXTVAL+"+fluffSeed+", "
						+ "'" + cb.getName()+ "','"+cb.getLastName()+"','"+cb.getUsername()+"','"+cb.getPassword()+"',"+cb.getSalary()+",'"+cardriver+"','"+HouseLiver+"')";
		//System.out.println("passing second user in: " +passingStatement); test-
		return passingStatement; 
	}
	public static String createNewAccount(String usernames, boolean joint) {
		String	passingStatement ="";
		String twoUsername="";
		String oneUsername="";
		if(joint) {
		//we pass the usernames
		StringBuilder sb = new StringBuilder(usernames);
		int spaceArea= sb.indexOf(" ");
		 oneUsername = sb.substring(0, spaceArea);
	//	System.out.println("username's split: "); test-
	//	System.out.println(oneUsername); 			test-
		 twoUsername = sb.substring(spaceArea+1); 
	//	System.out.println(twoUsername);			test-
		 	passingStatement = "INSERT INTO ACCOUNTSTABLE(AID, BALANCE, CUST1, CUST2, APPROVED) "
					+ "VALUES(accountNumber.NEXTVAL, 0, get_User('"+oneUsername+"'), get_User('"+twoUsername+"'),'Not' )";
				
		}
		else {
			passingStatement = "INSERT INTO ACCOUNTSTABLE(AID, BALANCE, CUST1, APPROVED) "
				+ "VALUES(accountNumber.NEXTVAL, 0, get_User('"+usernames+"'),'Not' )";
		}
		return passingStatement ; 
	}
	
	//----------------readDB-------------------\\
	public static String readingConnections(String usrnm) {
		SQLConnections sqlc = new SQLConnections(); 
		String returnedUsername=" ";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select PASSWORD_ from CustomerTable where Username =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, usrnm);
			 ResultSet rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 returnedUsername= 	rs.getString("PASSWORD_");
			 }
			 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("to :"+returnedUsername); //-test-
		return returnedUsername; 
	}
	//===Read Admin/Employee
	public static String readAdmin(String usrnm) {
		SQLConnections sqlc = new SQLConnections(); 
		String returnedUsername="";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select APASSWORD from Admintable where Username =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, usrnm);
			 ResultSet rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 returnedUsername= 	rs.getString("APASSWORD");
			 }
			// System.out.println("returned: "+returnedUsername); -test-
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("to :"+returnedUsername); //-test-
		return returnedUsername; 
	}
	
	public static void reviewAppConnection() {
		
		SQLConnections sqlc = new SQLConnections(); 
		String returnID="";
		String c1Name = "";String c2Name = "";
		String c1Lst ="";String c2Lst ="";
		String c1slry = "";String c2slry = "";
		String cr1 ="";String cr2 ="";
		String h1 ="";String h2 ="";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			System.out.println("=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=");
			System.out.println("=---=-Pending-on-Your-Approval-=-------------=");
			System.out.println("=---=-=-=-=-=-=-=-=-=-=-=-=-=----------------=");
			String sql = "select * from ACCOUNTSTABLE where APPROVED =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, "Not");
			 String CustomerId1 ="";
			 String CustomerId2 ="";
			 ResultSet rs = pstmt.executeQuery();
			 while(rs.next()) {
				 returnID= 	rs.getString("AID");
				 System.out.println("Application #: "+returnID);
				 CustomerId1 =rs.getString("CUST1");
				 if(CustomerId1!=null) {
					 String sqlC1 = "select * from CUSTOMERTABLE where cid =?";
					 PreparedStatement pstmt1 = conn.prepareStatement(sqlC1);
					 pstmt1.setString(1, CustomerId1);
					 ResultSet Cset = pstmt1.executeQuery();
					 while(Cset.next()) {
						 c1Name = Cset.getString("FIRST_NAME");
						 c1Lst = Cset.getString("LAST_NAME");
						 c1slry = Cset.getString("SALARY");
						 cr1 = Cset.getString("CAROWNER");
						 h1 = Cset.getString("HOMEOWNER");
						 System.out.println("=================== Primary Account Owner");
						 System.out.println("Customer:  "+c1Name+" "+c1Lst);
						 System.out.println("           "+c1Name+"'s "+"Salary (peanuts a year): "+"$"+c1slry+".00");
						 System.out.println("           Homeowner: " + h1);
						 System.out.println("           Carowner:  " + cr1);
						
					 }
				 }
			//	 System.out.println("customer 1: " +CustomerId1);//-test-
				 CustomerId2 =rs.getString("CUST2");
				 if(CustomerId2==null)
					 System.out.println("");
				 if(CustomerId2!=null) {
					 String sqlC2 = "select * from CUSTOMERTABLE where cid =?";
					 PreparedStatement pstmt2 = conn.prepareStatement(sqlC2);
					 pstmt2.setString(1, CustomerId2);
					 ResultSet Cset2 = pstmt2.executeQuery();
					 while(Cset2.next()) {
						 c2Name = Cset2.getString("FIRST_NAME");
						 c2Lst = Cset2.getString("LAST_NAME");
						 c2slry = Cset2.getString("SALARY");
						 cr2 = Cset2.getString("CAROWNER");
						 h2 = Cset2.getString("HOMEOWNER");
						 System.out.println("--------------- Secondary Account Owner");
						 System.out.println("Customer:  "+c2Name+" "+c2Lst);
						 System.out.println("           "+c2Name+"'s "+"Salary (peanuts a year): "+"$"+c2slry+".00");
						 System.out.println("           Homeowner: " + h2);
						 System.out.println("           Carowner:  " + cr2);
						 System.out.println("");
					 }
				 }
			//	 System.out.println("customer 2: " +CustomerId2);//-test-
				 continue;
			 }
						
			// System.out.println("returned: "+returnedUsername); -test-
		}catch(SQLException e) {
			//e.printStackTrace();
			System.out.println(" *** You are not currently connected to Database");
		}
		//System.out.println("to :"+returnedUsername); //-test-
	}
	static boolean approveApp(String Account_id) {
		boolean returning = true; // for success
		SQLConnections sqlc = new SQLConnections(); 
		String returnedUsername=" ";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "update ACCOUNTSTABLE set approved='yes' where aid =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, Account_id);
			 Statement statement = conn.createStatement();
			 pstmt.executeQuery();
			
			 
		}
		catch(SQLException e) {
			e.printStackTrace();
			returning = false; 
			System.out.println("There was an error with the connection");
		}
		return returning; 
	}
	static String searchAccount(String Account_id) {
		
		SQLConnections sqlc = new SQLConnections(); 
		String TransactionLog =""; //this value is returned 
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from accountstable where aid =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, Account_id);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
			ArrayList<String> custs = new ArrayList<>();
			ArrayList<String> ReturnCustomer = new ArrayList<>();
			 while(result.next()) {
				 TransactionLog = result.getString("RelatedLOG");
				 String AccountID = result.getString("Aid");
				 System.out.println(AccountID+" found!");
				 String Crbalance = result.getString("balance");
				 System.out.println();
				 System.out.println("Current Balance: $"+Crbalance+".00");
				 String log = result.getString("relatedlog");
				 String Cust1 = result.getString("Cust1");
				// System.out.println("searching for id "+Cust1 ); test-
				 String Cust2 = result.getString("Cust2");
			//-test	 System.out.println("searching for id "+Cust2 ); test-
				 custs.add(Cust1);
				 if(Cust2 != null) {
					 custs.add(Cust2);
				 }
			 }
			 for (String st: custs) {
				// System.out.println("string passed from array "+st); test-
				 ReturnCustomer.add(sqlc.grabCustomer(st));
			 }
			 System.out.println(" - Primary Owner: "+ReturnCustomer.get(0) );
			 System.out.println();
			 try{
				 System.out.println(" - Secondary Owner: "+ReturnCustomer.get(1) );
			 }catch(IndexOutOfBoundsException e) {
				 //do nothing
			 }
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			System.out.println("There was an error with the connection");
		}
		return TransactionLog; 
	}
	static boolean retrieveAccount(String code) {
		boolean truFals = false; 
		//customer searches for accounts queried here- 
		///customer searches
		SQLConnections sqlc = new SQLConnections(); 
		ArrayList<String> returnedUsername = new ArrayList<>(7);
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from accountstable where aid =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, code);
			 ResultSet rs = pstmt.executeQuery(); //account number and current balance
			 
			 int incrment = 1; 
			 while(rs.next()) {
				 
				 truFals = true; 
				// System.out.println("incriment: " + incrment);
				 returnedUsername.add(rs.getString("cust"+incrment));
				// System.out.println(returnedUsername.get(0)); -test-
			//	 System.out.println("id on this account  "+ returnedUsername.get(incrment-1));
				 
				 incrment++;// System.out.println("ever get here? "+incrment);
				 returnedUsername.add(rs.getString("cust"+incrment));
				// System.out.println(returnedUsername.get(1)); -test-
				// System.out.println("id on this account  "+ returnedUsername.get(incrment-1));
				 break;
			 }
		//	System.out.println(returnedUsername.get(0)); -test-
			 ArrayList<String> potentialPassowrds = new ArrayList<String>(); 
			 ArrayList<String> AccountHolders = new ArrayList<String>(); 
			 int index = 0;
			 System.out.println("  ~- -=-=- -=-=- -=-=- -=-=- -=-=- -=-=- -=-=- -=-=- -=-=- -=-=- -~ ");
			 for (String st : returnedUsername) {
				
				 if(st != null) {
					 //create a statement 
					 ArrayList<String> retrieveC = retrieveUser(st);
				//	 System.out.println("retrieveCustomer = "+ retrieveC.get(index));
					 AccountHolders.add(retrieveC.get(0));
				//	System.out.println(retrieveC.get(index));
					 potentialPassowrds.add(retrieveC.get(1));
	 
				 }
				 index =2; // there can only be two ppl on account at a time -  
			 }
			 CustomerBlueprint cb = new CustomerBlueprint(); 
			 if(AccountHolders.size()==2) {
				 System.out.println(" (Re-)Enter, either "+AccountHolders.get(0)+" or "+AccountHolders.get(1)+"s'"
				 		+ " passwords to access this account");
				 String entry = cb.confirmAccountUser(); 
				 	
				 if(entry.equals(potentialPassowrds.get(0))) {
					 System.out.println("-");
					 if(potentialPassowrds.get(0).equals(potentialPassowrds.get(1))) {
						 System.out.println("Welcome User!");
					 }else {
					 System.out.println(" Welcome, "+ AccountHolders.get(0));
					 }
					 cb.setName(AccountHolders.get(0));
				 } else if(entry.equals( potentialPassowrds.get(1)))
				 {
					 System.out.println("-");
					 if(potentialPassowrds.get(0).equals(potentialPassowrds.get(1))) {
						 System.out.println("Welcome User!");
					 }else {
					 System.out.println(" Welcome, "+ AccountHolders.get(1));}
					 cb.setName(AccountHolders.get(1));
				 }else {
					 truFals =false; 
				 }
			 }else if(AccountHolders.size()==1) {
				 
				 System.out.println(" (Re-)Enter your"
					 		+ " password to access this account");
					 String entry = cb.confirmAccountUser(); 
				 if(entry.equals(potentialPassowrds.get(0))) {
					 System.out.println("-");
					 System.out.println(" Welcome, "+ AccountHolders.get(0));
					 cb.setName(AccountHolders.get(0));
				 	}	else {
				 		truFals =false; 
				 	}								 
			 
			 }
				 
			// System.out.println("returned: "+returnedUsername); -test-
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("             ---------------------");
			System.out.println("              **Account not found!");
			truFals = false; 
			RegLoginRun rlr = new RegLoginRun(); 
			rlr.accountSearch();
		}
		return truFals;
	}
	static ArrayList<String> retrieveUser(String custid) {
		SQLConnections sqlc = new SQLConnections(); 
		ArrayList<String> aUser = new ArrayList<>(); 
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from customertable where cid =?";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, custid);
			 ResultSet  rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 aUser.add(rs.getString("first_name"));
				 aUser.add(rs.getString("Password_")); 
			 }
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("   **Trouble Retrieving User(s)");
			
			RegLoginRun rlr = new RegLoginRun(); 
			rlr.accountSearch();
		}
		//tests-
//		System.out.println(" should grab two--------------- ");
//		System.out.println("in retrieveUser() : " +passwords.get(0));
//		System.out.println("next");
//		System.out.println("in retrieveUser() : " +passwords.get(1));
		
		return aUser; 
	}
	
	public String grabCustomer(String cust) {
		String returnedName="";
		SQLConnections sqlc = new SQLConnections(); 
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from customertable where cid =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, cust);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
	//		System.out.println("here?"); -test-
			
			while(result.next()) {
				returnedName = result.getString("FIRST_NAME");
			//	System.out.println("from grab customer "+returnedName); test
				returnedName = returnedName +" " +result.getString("Last_Name") ;
		//		System.out.println("should be full string from grab customer method "+returnedName); tset-
			}
	//		System.out.println("The # of rows changed: "+ numOfRowsChanged); //-test-
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return returnedName;
	}
	
	//-=-=-customer functionalities (withdraw, deposit, status(log) and terminate-
	static String Withdraw(String wdr, String id) {
		SQLConnections sqlc = new SQLConnections(); 
		String retrn ="";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from accountstable where aid =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, id);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
			//-test-
			String st="";
			while(result.next()) {
				 st =result.getString("balance");
				break; 
			}
			int inq = Integer.valueOf(wdr);
			int blc = Integer.valueOf(st);
			if(blc < inq) {
				retrn ="failed";
				System.out.println("          *Withdraw Amount too much");
				CustomerBlueprint cb = new CustomerBlueprint(); 
				Scanner s = new Scanner(System.in);
				cb.accountOptions(s, cb, id);
			}else {
				int newBalance = blc- inq;
				Scanner scan = new Scanner(System.in);
				CustomerBlueprint cb = new CustomerBlueprint(); 
				sqlc.updateBalance(scan, cb, newBalance, id, "Withdraw");
				//update a log
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return retrn;			
	}
	static String Deposit(String dps, String id) {
		SQLConnections sqlc = new SQLConnections(); 
		String retrn ="";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from accountstable where aid =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, id);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
			//-test-
			String st="";
			while(result.next()) {
				 st =result.getString("balance");
				break; 
			}
			int inq = Integer.valueOf(dps);
			int blc = Integer.valueOf(st);
			
				int newBalance = blc+ inq;
				Scanner scan = new Scanner(System.in);
				CustomerBlueprint cb = new CustomerBlueprint(); 
				sqlc.updateBalance(scan, cb, newBalance, id,"Deposit");
				//update a log

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return retrn;			
	}
	static void updateBalance(Scanner scan, CustomerBlueprint cb, int newBalance, String id, String interactionSort) {
		SQLConnections sqlc = new SQLConnections(); 
	
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
		String sql = "update accountstable set balance =?  where aid =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1, String.valueOf(newBalance));
		pstmt.setString(2, id);
		 Statement statement = conn.createStatement();
		ResultSet result = pstmt.executeQuery();
		//create a log for the interaction! 
		sqlc.updateLog(interactionSort,String.valueOf(newBalance), id );
		System.out.println();
		System.out.println("Your balance is now: "+"$"+newBalance+".00");
		System.out.println();
		cb.accountOptions(scan, cb, id);
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	static void updateLog(String interaction, String amnt, String accnt ) {
		CustomerBlueprint cb = new CustomerBlueprint(); 
		SQLConnections sqlc = new SQLConnections(); 
	String	passingStatement = "INSERT INTO logtable(lid, "+interaction+", DATED, customer, accountused) "
				+ "VALUES(LOGNUMBER.NEXTVAL, "+amnt+", CURRENT_TIMESTAMP ,'"+cb.getName() +"', "+accnt+" )";
	//CURRENT_TIMESTAMP'd log of interaction
	try(Connection conn =
			DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword())){
	
		Statement statement = conn.createStatement();
		int numOfRowsChanged = statement.executeUpdate(passingStatement);
		//System.out.println(numOfRowsChanged+" log created");//test-
	}
	
	catch(SQLException e) {
		e.printStackTrace();
	}
	}
	static String grabBalance(String AiD) {
		SQLConnections sqlc = new SQLConnections(); 
		String balnc="";
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String retrieve = "select balance from accountstable where aid =?";
			PreparedStatement pstmt = conn.prepareStatement(retrieve);
			 pstmt.setString(1, AiD);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				String balance = result.getString("balance");
				break;
			}
		}catch(SQLException e) {
			System.out.println("Error Connecting to Database");
		}
		return balnc; 
	}
	static void printAccountHistory(String AiDfk){
		
		SQLConnections sqlc = new SQLConnections(); 
		try(Connection conn =
				DriverManager.getConnection(sqlc.getUrl(), sqlc.getUsername(), sqlc.getPassword()))
		{
			String sql = "select * from logtable where accountused =? order by lid ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, AiDfk);
			 Statement statement = conn.createStatement();
			ResultSet result = pstmt.executeQuery();
			System.out.println("  ~ Interaction list for account: " + AiDfk+" ~");
			int prevBalnce=0; //initiate with an assumption of 0
			int index = 1; 
			String retrnBlnc = grabBalance(AiDfk);
			int act =0; 
			while(result.next()) {
				String withdr = result.getString("withdraw");
				String dpst = result.getString("deposit");
				
				String date = result.getString("DATED");
				//let's trim the seconds a milliseconds off that date
				StringBuilder sb = new StringBuilder(date);
				date =sb.substring(0 ,16);
				String cust = result.getString("customer");
				if(withdr==null) {
					 act= Integer.valueOf(dpst);
					int actualDpst = prevBalnce -act;
					actualDpst = Math.abs(actualDpst);
					System.out.println("_________________________________________]");
					System.out.println(index+". < A deposit of $"+actualDpst+".00 was  ");
					System.out.println("     made on, "+date+", by, "+cust+" >");
					System.out.println();
					System.out.println(" -Former Account balance: $"+ prevBalnce+".00-");
					System.out.println(" -Result Account Balance: $"+dpst+".00-");
					prevBalnce=Integer.valueOf(dpst) ;
				}if(dpst==null) {
					 act= Integer.valueOf(withdr);
					int actualW =   prevBalnce- act;
					actualW = Math.abs(actualW);
					System.out.println("_________________________________________]");
					System.out.println(index+". < A withdraw of $"+actualW+".00 was  ");
					System.out.println("     made on, "+date+", by, "+cust+" >");
					System.out.println();
					System.out.println(" -Former Account balance: $"+ prevBalnce+".00-");
					System.out.println(" -Result Account Balance: $"+withdr+".00-");
					prevBalnce=Integer.valueOf(withdr);
				}
				
				index++; 
			}
			
			}catch(SQLException e) {
				System.out.println("Error Connecting to Database");
			}	
	}
}























