package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Super_Prison")
public class SuperPrison {
	@Id
	@Column(name="sp_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int spid;
	
	@Column(name="sp_Name")
	private String spName;
	
	@Column(name="sp_Location")
	private String spLocation;
	
	@OneToMany(mappedBy= "superPrisonVar",fetch= FetchType.LAZY)
	private List<SuperVillain> villList= new ArrayList<>();
	
	public SuperPrison() {
	}
	
	public SuperPrison(String spName, String spLocation) {
		super();
		this.spName = spName;
		this.spLocation = spLocation;
	}
	
	public SuperPrison(String spName, String spLocation, List<SuperVillain> villList) {
		super();
		this.spName = spName;
		this.spLocation = spLocation;
		this.villList = villList;
	}

	public SuperPrison(int spid, String spName, String spLocation, List<SuperVillain> villList) {
		super();
		this.spid = spid;
		this.spName = spName;
		this.spLocation = spLocation;
		this.villList = villList;
	}

	public int getSpid() {
		return spid;
	}

	public void setSpid(int spid) {
		this.spid = spid;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpLocation() {
		return spLocation;
	}

	public void setSpLocation(String spLocation) {
		this.spLocation = spLocation;
	}

	public List<SuperVillain> getVillList() {
		return villList;
	}

	public void setVillList(List<SuperVillain> villList) {
		this.villList = villList;
	}

	@Override
	public String toString() {
		return "\n\tSuperPrison [spid=" + spid + ", spName=" + spName + ", spLocation=" + spLocation + ", villList="
				+ villList + "]";
	}

	
	
	
}
