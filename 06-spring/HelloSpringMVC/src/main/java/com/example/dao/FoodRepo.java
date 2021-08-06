package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Food;

@Repository("foodRepo")
@Transactional
public class FoodRepo {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SessionFactory sesFact;
	
	public FoodRepo() {
	}
	
	//@Transactional
	public void insert(Food food) {
		sesFact.getCurrentSession().save(food);
	}
	
	//@Transactional
	public void update(Food food) {
		sesFact.getCurrentSession().update(food);
	}
	
	//@Transactional
	public Food selectById(int id) {
		return sesFact.getCurrentSession().get(Food.class, id);
	}
	
	//@Transactional
	public List<Food> selectAll(){
		return sesFact.getCurrentSession()
				.createQuery("from Food", Food.class)
				.list();
	}
}
