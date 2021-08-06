package ACTUAL.bankApp;

public class test {

	public static void main(String[] args) {
		String ownerDetails = "thisguy~thatguy~200~true~false~fart~stick~1000~false~false";
		
	//	StringBuilder sb = new StringBuilder(ownerDetails); 
		int squiggleIterate=0;
		char[] cunt = ownerDetails.toCharArray(); 
		
		for(char squiggle : cunt) {
			
			if(squiggle == '~') {
				
				squiggleIterate = squiggleIterate+ 1; 
				System.out.println(squiggleIterate);
								
				if(squiggleIterate== 5) {
					int index = ownerDetails.indexOf(squiggle);
					
					StringBuilder sb = new StringBuilder(ownerDetails); 
					sb.delete(0, index);
						System.out.println(sb.toString());				
				}
			}
		}

	}

}
