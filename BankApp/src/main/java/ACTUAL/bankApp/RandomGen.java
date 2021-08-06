package ACTUAL.bankApp;

import java.util.Random;
import java.util.Scanner;

public class RandomGen {
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
		//	System.out.println("!"); //testing-
			}catch(ArrayIndexOutOfBoundsException exception2) {
			try {	pseudoRnd = pseudoRnd +libCycle[temp-35]; 
			//	System.out.println("!!");  //testing-
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
		CustomerBlueprint cb = new CustomerBlueprint(); 
		cb.setPassword(pseudoRnd); //sets to 'failed' every time 
								  //it throws array out of bounds 
								 //too many times but we just re-write it on other runs 
		//System.out.println("password has been set to : "+cb.getPassword() ); -test-
		return pseudoRnd;
	}
	
	String returnPasswdPreference(String input) {
		String rtrnInput="";
		if(input.equals("1")) {
			rtrnInput= "yes";
		}else if(input.equals("2")) {
			rtrnInput= "no";
		} else {
			rtrnInput ="bad";
		}
		
		return rtrnInput;
	}
	void confirmOrDeny(Scanner scan, RegLoginRun backUp, CustomerBlueprint cb) {
		RandomGen rg = new RandomGen(); 
		System.out.println();
		System.out.println("'Yes' (Use auto-generated password) - Type 1");
		System.out.println("'No' (Create my own password) - Type 2");
		String passwdPref = scan.next(); 
		if(rg.returnPasswdPreference(passwdPref).equals("yes")) {
			//set password earlier ------------------------------------^-44
			//skip to username creation
			backUp.collectUsername(scan, cb, backUp); 
		}else if(rg.returnPasswdPreference(passwdPref).equals("no")) {
			backUp.passwordProcessing(scan, backUp, cb); //-----
		}else if(rg.returnPasswdPreference(passwdPref).equals("bad")) {
			//rerun this method
			System.out.println("        *invalid input");
			rg.confirmOrDeny(scan, backUp, cb);
		}
	}
	//randomly spams the 'Z' key method -fix
	boolean checkZneaky(String rnmdAttmpt) {
	//	System.out.println(rnmdAttmpt);
		boolean returnTrue = true; 
		char[] check =  rnmdAttmpt.toCharArray();	
			
				Character charObject1 = new Character(check[1]);
				Character charObject3 = new Character(check[2]);
				Character charObject2 = new Character(check[4]);
				//System.out.println("comparing "+ charObject1.toString()+" " +charObject2.toString());
			if(charObject1.equals(charObject2) & charObject3.equals(charObject1)) {
				returnTrue= false; //not a good password
			//	System.out.println("made it");
				
			}
							
		return returnTrue;//prevents three characters in a row per password- 
	}
}






