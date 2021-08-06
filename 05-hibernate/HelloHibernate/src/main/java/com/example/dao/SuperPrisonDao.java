package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.SuperPrison;
import com.example.util.HibernateUtil;

public class SuperPrisonDao {
	public SuperPrisonDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void insert(SuperPrison superPrison) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(superPrison);

		tx.commit();
		/*ses.close();*/
	}

	public void update(SuperPrison superPrison) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(superPrison);

		tx.commit();
		/*ses.close();*/
	}

	public SuperPrison selectById(int id) {
		Session ses = HibernateUtil.getSession();

		SuperPrison superPrison = ses.get(SuperPrison.class, id);

		/*ses.close();*/

		return superPrison;
	}

	public SuperPrison selectByName(String name) {
		return null;
	}

	public List<SuperPrison> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<SuperPrison> superPrisonList =
				ses.createQuery("from SuperPrison",
						SuperPrison.class).list();

		/*ses.close();*/

		return superPrisonList;
	}
}
