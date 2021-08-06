package ACTUAL.bankApp;

public class ascii {

	public static void main(String[] args) {
		
	String found = "[200][300][-150]";
	ascii ai = new ascii(); 
	ai.calcSum(found);
	
	}
	int calcSum(String found) //--found passed from above ^^ (openAccnt) 
	{
		
		int returnSum =0; 
		int cycleindex = found.length();//not an exact incrimentor.. Just want found.length to have a variable.. idk why.. 
		
		char[] cycleStr = found.toCharArray();
		int fuckit=0; 
		for(char all : cycleStr) {
			if(all == '[') {
				++fuckit;
			}
		}
		 
		while(fuckit>0) {
		String grab = found.substring(found.indexOf('[')+1, found.indexOf(']')); //grabs a value
		
		int grabbed=0; 
		
		System.out.println("string: "+grab);
		try {
		 grabbed = Integer.parseInt(grab);//this should work every time.. 	--try catch may be smart just for later!! 
		}
		catch(NumberFormatException nfe) {
			break; 
		}
		System.out.println("int "+grabbed);
		returnSum = returnSum+ grabbed;
		System.out.println("for return "+ grabbed);
		
		StringBuilder sb = new StringBuilder(found); 
		
		sb.delete(found.indexOf('['), found.indexOf(']')+1);
		
		found = sb.toString(); 
		
		System.out.println("leftover: "+ found);
		
		fuckit=fuckit-1; 
		}
		System.out.println("duffer? "+returnSum);
		return returnSum; 
	}

}
