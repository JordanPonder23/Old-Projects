package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * JPA annotations (Java Persistence API)-
 * 	Is a standardized api that deals with mapping java objects to DBs.
 *  Hibernate implements JPA annotations.
 *  
 *  We often choose JPA annotations over hibernate annotations because
 *  in the future if we want to change ORM frameworks we don't have to use
 *  different annotations. We can simply switch out the framework itself.
 */

@Entity
@Table(name="Super_Villain")
public class SuperVillain {
	
	@Id
	@Column(name="svill_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int svillId;
	
	@Column(name="name", unique=true, nullable=false)
	private String name;
	
	@Column(name="superpower")
	private String superpower;
	
	@Column(name="bounty")
	private int bounty;
	
	//a proxy will be in the place of something that hasn't been loaded yet
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Crime> crimes;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="Prison_FK")
	private SuperPrison superPrisonVar;
	
	
	public SuperVillain() {
	}

	public SuperVillain(String name, String superpower, int bounty, List<Crime> crimes,
			SuperPrison superPrison) {
		super();
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrisonVar = superPrison;
	}
	
	public SuperVillain(int svillId, String name, String superpower, int bounty, List<Crime> crimes,
			SuperPrison superPrison) {
		super();
		this.svillId = svillId;
		this.name = name;
		this.superpower = superpower;
		this.bounty = bounty;
		this.crimes = crimes;
		this.superPrisonVar = superPrison;
	}


	public int getSvillId() {
		return svillId;
	}


	public void setSvillId(int svillId) {
		this.svillId = svillId;
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


	public List<Crime> getCrimes() {
		return crimes;
	}


	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}


	public SuperPrison getSuperPrison() {
		return superPrisonVar;
	}


	public void setSuperPrison(SuperPrison superPrison) {
		this.superPrisonVar = superPrison;
	}


	@Override
	public String toString() {
		return "\nSuperVillain [svillId=" + svillId + ", name=" + name + ", superpower=" + superpower + ", bounty="
				+ bounty + ", crimes=" + crimes + ", \n\tsuperPrison=" + superPrisonVar + "]";
	}

	
	
	
}
