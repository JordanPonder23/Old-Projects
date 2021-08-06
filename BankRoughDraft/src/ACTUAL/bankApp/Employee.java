package ACTUAL.bankApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Employee {
		
	private String empUsername; 
	private String empPassword;
	
//---------------------------------	
	public String getEmpUsername() {
		return empUsername;
	}
	public void setEmpUsername(String empUsername) {
		this.empUsername = empUsername;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	} 
	
	boolean empCredCheck() {
		Scanner scan = new Scanner(System.in); 
		System.out.println("Enter Username: ");
		String usernmEntry = scan.next(); //       !!!! spaces still break this :/ 
		System.out.println("Enter Password: ");
		String psswrdEntry = scan.next(); //  ---        -- ^ !!
		Employee empInst = new Employee();
		String filename = "./Admin/EmployeeAdmin.txt";
		//empInst.readObject(filename);
		if((usernmEntry+"-"+psswrdEntry).equals(empInst.readObject(filename))) {
			return true; 
		}
		else {
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
						//System.out.println(stringable); test-
				//System.out.println("even making it here? "); //test- 
				// returns if txt file of a particular name is found
			} 
			catch (FileNotFoundException e)
			{
				//e.printStackTrace();   <--- we don't want this to print but this is a good thing! :) 
				work = false; 
		//	System.out.println("not created "); test-
				// returns if a file of a particular name is not found
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
	
}
