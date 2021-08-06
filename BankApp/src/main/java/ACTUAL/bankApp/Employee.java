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
		
		//SQL Connection for admin shit
		SQLConnections sqlc = new SQLConnections();
		//	System.out.println("password entered: " +psswrdEntry); -test-
		if((psswrdEntry).equals(sqlc.readAdmin(usernmEntry))) {
			return true; 
		}
		else {
			return false; 
		}	
	}
	
	
}
