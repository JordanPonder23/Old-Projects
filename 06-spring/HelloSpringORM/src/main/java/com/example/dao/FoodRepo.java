package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Food;

@Repository("foodRepo")
@Transactional
public class FoodRepo {

	@Autowired
	private SessionFactory sesFact;
	
	public FoodRepo() {
	}
	
	//@Transactional
	public void insert(Food food) {
		/*Session ses = sesFact.openSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(food);
		
		tx.commit();
		ses.close();*/
		sesFact.getCurrentSession().save(food);
	}
	
	//@Transactional
	public void update(Food food) {
		/*Session ses = sesFact.openSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(food);
		
		tx.commit();
		ses.close();*/
		sesFact.getCurrentSession().update(food);
	}
	
	//@Transactional
	public Food selectById(int id) {
		/*Session ses= sesFact.openSession();
		
		Food food = ses.get(Food.class, id);
		
		ses.close();
		return food;*/
		return sesFact.getCurrentSession().get(Food.class, id);
	}
	
	//@Transactional
	public List<Food> selectAll(){
		/*Session ses = sesFact.openSession();
		
		List<Food> foodList = ses.createQuery("from Food", Food.class).list();
		
		ses.close();
		return foodList;*/
		return sesFact.getCurrentSession()
				.createQuery("from Food", Food.class)
				.list();
	}
}
