package com.example.dbtouching;

/*
 * This class is a blueprint for each object returned as json to AdminConsole page
 * it narrows the criteria for what we need to display from a given manager/ resolver/ overseer
 */
public class Resolvers {
	protected String  lastname; 
	protected String firstname;
	protected String username;
	protected String email;
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
