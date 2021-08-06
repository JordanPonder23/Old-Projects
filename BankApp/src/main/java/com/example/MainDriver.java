package com.example;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Random;

public class MainDriver{
	
	private static String url= "jdbc:oracle:thin:@jponder23.cv1rmjxwr5fp.us-east-2.rds.amazonaws.com:1521:orcl";

			private static String username="myusfdb";

			private static String password= "#3Marine33";	
	
	public static void main(String[] args) {
		statementExample(url,username,password, 8);
		pseudoRandomPasswordGenerator();
	}
	public static void statementExample(String url, String id, String namingAttmept, int fluffSeed) {
				Random rnd = new Random(); 
				Random rnd2 = new Random(); 
				int fluff = rnd.nextInt(fluffSeed);
				boolean uPdN = rnd2.nextBoolean(); 
				if(uPdN)
					fluff= fluff - (fluff*2);//half the time it will drop so we don't waste a whole
														// bunch of sequence oppertunities going up too fast. 
				
					
		try(Connection conn =
				DriverManager.getConnection(url, id, namingAttmept))
		{
			String sql = "INSERT INTO CustomerTable(Cid, First_name) "+
					"VALUES("+"custIDgen.NEXTVAL+"+fluff+", '" + "jilly"+ "' )";
			Statement statement = conn.createStatement();
			int numOfRowsChanged = statement.executeUpdate(sql);
			System.out.println("The # of rows changed: "+ numOfRowsChanged);
			
		}catch(SQLIntegrityConstraintViolationException d) {
			fluffSeed = fluffSeed++; // we should be able to dodge the unique key constraint with this 
									//and still add a small element of pseudo-random to the incrementation of the Cid 
			statementExample(url,id,namingAttmept, fluffSeed );
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static String pseudoRandomPasswordGenerator() {
		String pseudoRnd ="";
		Random initialRandom = new Random(); 
		String potent ="ZabHrfFD7x.Xo6cb|Ji?{N?vjMS4dzg|Sdsl,xBV5q{Pv1.bnaRTh9}keeAr2t3W8sNm+]pCMUIO-xw[K.sdLjky_us0qwA,Gnc,=zlY";
		String potent2 ="BcvjkXo67jhs8kyCM2i0qweA,MlabvJ3+|sdx{PPLKVZn5a4]zgfxqfghWYYERT9_uN=zlbnm,.?Hrt.UIO[Ar1pSG}-cxweFDSdsVM";
		String lib="";
		if(initialRandom.nextBoolean())
			lib = potent;
		else
			lib= potent2;
		
		char[] libCycle = lib.toCharArray();
	//	System.out.println("library length: " + libCycle.length); test-
		Random rnd = new Random(); 
		boolean skert=rnd.nextBoolean();
		Random rnd2 = new Random(); 
		boolean skert2= rnd2.nextBoolean();
		
		Integer milli=0;
		if(skert)
		 milli = (int) System.currentTimeMillis();
		else
			milli = (int) System.currentTimeMillis() +55;
			
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(milli);
	//	System.out.println(sb.toString()); -test
		char[] iterable = sb.toString().toCharArray();
		int randomTimes = Integer.parseInt(iterable[9]+""); 
		int randomTimes2 = Integer.parseInt(iterable[8]+""); 
		randomTimes2 = randomTimes2*randomTimes2;
		if(skert)
			randomTimes2 = randomTimes2 +13;
		
		if(skert2)
			randomTimes2 = randomTimes2 +1; 
		
		//System.out.println("startpt: "+ randomTimes2); test-
		
		//System.out.println("random times: " +randomTimes ); test-
		int startValue = 0; 
		int breakpoint = 0; 
		for(char c : iterable) {
			String s = c +"";
			startValue = startValue+Integer.parseInt(s);
			if(breakpoint ==randomTimes )
				break;
			else
				breakpoint++;
		}
	//	System.out.println("incriment " +startValue ); test-
		pseudoRnd = pseudoRnd +libCycle[randomTimes2];//first char
		int temp =0; 
		boolean testLength =true;
		String returnFailed = "";
		while(pseudoRnd.length() <18 & testLength ==true) {
		 temp = temp+ randomTimes2 + startValue; 
		if(temp >= 103) {
			temp = temp - 103;
		}
		
		try {
		pseudoRnd = pseudoRnd +libCycle[temp]; 
		}catch(ArrayIndexOutOfBoundsException exception) {
			try {
			pseudoRnd = pseudoRnd +libCycle[temp+ 5]; //gotta catch'em all
			System.out.println("!"); //testing-
			}catch(ArrayIndexOutOfBoundsException exception2) {
			try {	pseudoRnd = pseudoRnd +libCycle[temp-35]; 
				System.out.println("!!");  //testing-
				}catch(ArrayIndexOutOfBoundsException besteffort){
					returnFailed ="failed";	//it actually fails quite a bit --Just re-run it, until it returns somethign usable
			}
		}
	}
		if(pseudoRnd.length() > 10) {
			Random rollForLength = new Random(); 
			int prob =  rollForLength.nextInt(100);
			if(prob >65)
			testLength= rollForLength.nextBoolean();
		}
	}//end of while loop
		//==-if-it-fails-==\
		if(returnFailed== "failed") {
			pseudoRnd=returnFailed;//return as failed for a re-run
		System.out.println("failed");	
		}
		else {
		//-----if successful pseudo-random password was generated: do the thing-v-
		//System.out.println("We've generated a pseudo-random password for you to use: "+pseudoRnd +" of length: " +pseudoRnd.length());
		System.out.println("We've generated a pseudo-random password for you to use: "
		+"-> "+pseudoRnd+" <-" );
		System.out.println();
		System.out.println("Use, "+pseudoRnd+" as your password? ");
//option to change password via email? mvp for now... but it would be cool. 
		}
		return pseudoRnd;
	}
}
