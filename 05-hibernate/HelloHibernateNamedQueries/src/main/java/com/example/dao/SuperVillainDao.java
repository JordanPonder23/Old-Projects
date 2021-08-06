package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.model.SuperVillain;
import com.example.util.HibernateUtil;

/*
 * Session methods
 * 
 * save() and persist()			result in a sql insert
 * update() and merge()			result in a sql update
 * delete()						result in a sql delete
 * saveOrUpdate()				results in either a sql insert or update(depending)
 * get() and load()				results in a sql select
 * 
 * get()	will go to the database immediately
 * load()	will use a placeholder (called a proxy) until you need the value
 * 
 * update()	will not allow duplicate ids inside of your cache
 * merge() will insert into the cache or overwrite the existing cache value
 */
public class SuperVillainDao {

	public SuperVillainDao() {
	}

	public void insert(SuperVillain myVill) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(myVill);

		tx.commit();
		/*ses.close();*/
	}

	public void update(SuperVillain myVill) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(myVill);

		tx.commit();
		/*ses.close();*/
	}

	public SuperVillain selectById(int id) {
		Session ses = HibernateUtil.getSession();

		SuperVillain myVill = ses.get(SuperVillain.class, id);

		/*ses.close();*/

		return myVill;
	}

	public SuperVillain selectByName(String name) {
		Session ses = HibernateUtil.getSession();
		
		List<SuperVillain> villList= ses.getNamedQuery(
				"HQL_GET_VILLAIN_BY_NAME").list();
		
		///returning
		if(villList.size()==0) {
			System.out.println("PANIC!!!!!!!!!!");
			return null;
		}
		return villList.get(0);
	}

	public List<SuperVillain> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<SuperVillain> villList =
				ses.getNamedQuery("HQL_GET_ALL").list();

		/*ses.close();*/

		return villList;
	}
}
