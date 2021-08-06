package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.SuperVillain;

@Repository("VillainDao007")
public class VillainDaoImpl implements VillainDao {

	/*
	 * This method is a mock Dao implementation.
	 * Instead of going to a database it has a hardcoded list of villains.
	 */
	@Override
	public List<SuperVillain> selectAll() {
		List<SuperVillain> vills = new ArrayList<>();
		
		vills.add(new SuperVillain("Danny Boy", "Technopath", 280_000));
		vills.add(new SuperVillain("Rain Man", "SuperSpeed", 210_000));
		vills.add(new SuperVillain("Claire", "Transformation", 220_000));
		vills.add(new SuperVillain("Vois", "All Power", 1_000_000_000));
		vills.add(new SuperVillain("Sharkesha", "Kneeing people in the face", 100_000));
		
		return vills;
	}

}
