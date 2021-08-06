package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Crime;
import com.example.model.SuperVillain;
import com.example.util.HibernateUtil;

public class CrimeDao {

	public CrimeDao() {
	}

	public void insert(Crime crime) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(crime);

		tx.commit();
		/*ses.close();*/
	}

	public void update(Crime crime) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(crime);

		tx.commit();
		/*ses.close();*/
	}

	public Crime selectById(int id) {
		Session ses = HibernateUtil.getSession();

		Crime crime = ses.get(Crime.class, id);

		/*ses.close();*/

		return crime;
	}

	public Crime selectByName(String name) {
		return null;
	}

	public List<Crime> selectAll() {
		Session ses = HibernateUtil.getSession();

		List<Crime> crimeList =
				ses.createQuery("from Crime",
						Crime.class).list();

		/*ses.close();*/

		return crimeList;
	}
}
