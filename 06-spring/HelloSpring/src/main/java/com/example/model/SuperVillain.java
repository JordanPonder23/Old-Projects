package com.example.model;
/* Plain Old Java Object (POJO) vs Java Bean
 * 
 * ANY object that exist in java is a POJO.
 * 
 * An object is ALSO considered a java bean if it
 * 	has certain characteristics:
 * 		-private variables
 * 		-public getters and setters
 * 		-serializable
 * 		-public no-arg constructor
 * 
 * 	Java Bean is a subset of POJOs
 */
public class SuperVillain {
	private String name;
	private String superpower;
	private int bounty;
	
	public SuperVillain() {
	}

	public SuperVillain(String name, String superpower, int bounty) {
		super();
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSuperpower() {
		return superpower;
	}

	public void setSuperpower(String superpower) {
		this.superpower = superpower;
	}

	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

	@Override
	public String toString() {
		return "SuperVillain [name=" + name + ", superpower=" + superpower + ", bounty=" + bounty + "]";
	}
}
