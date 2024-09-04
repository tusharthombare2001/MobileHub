package com.MobileDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Mobiles.User;
import com.Utility.HibernateUtil;




public class UserDao {
	
	public User findByUsername(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = null;
		
		try {
			Query<User> query = session.createQuery("from User where username = :username", User.class);
			query.setParameter("username", username);
			user = query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
	
	public void saveUser (User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();		
		}catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
