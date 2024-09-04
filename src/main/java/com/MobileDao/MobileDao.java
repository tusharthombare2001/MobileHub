package com.MobileDao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Mobiles.Mobile;
import com.Utility.HibernateUtil;


public class MobileDao {

	public void saveMobileDetails(Mobile mobile) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(mobile);
			transaction.commit();
			System.out.println("mobile details save succesfully");

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			System.out.println("session closed.");
		}

	}

	public String deleteMobileById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Mobile mobile = session.get(Mobile.class, id);
			if (mobile != null) {
				session.delete(mobile);
				transaction.commit();
				return "Deleted Successfully";
			} else {
				return "Product Not exists for delete";
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return "Something went wrong";
		} finally {
			session.close();
		}
	}

	public List<Mobile> getAllMobileData() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Mobile> mobiles = null;
		try {
			Query<Mobile> query = session.createQuery("from Mobile", Mobile.class);
			mobiles = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return mobiles;
	}

	public Mobile getMobileById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Mobile mobile = null;

		try {
			mobile = session.get(Mobile.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return mobile;
	}
	
	
	
	
	public String updateMobileName(int id , String newName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Mobile mobile = session.get(Mobile.class, id);
			if(mobile != null) {
				mobile.setMobileName(newName);
				session.update(mobile);
				transaction.commit();
				return "mobile name updated succesfully";
			}else {
				return "Mobile not found for update";
			}
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return "Somthing went Wrong";
			
		}finally {
			session.close();
		}
	}
	
	
	public String updateMobileQuantity(int id, int newQuantity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Mobile mobile = session.get(Mobile.class, id);
			if(mobile != null) {
				mobile.setMobileQuantity(newQuantity);
				session.update(mobile);
				transaction.commit();
				return "Mobile quantity update succesfully";		
			}else {
				return "mobile not found";
			}
		}catch(Exception e) {
			if(transaction!= null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return "somthing went Wrong";
		}finally {
			session.close();
		}
	}
	
	
	public String updateMobileCost (int id, int newCost) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Mobile mobile = session.get(Mobile.class, id);
			if(mobile != null) {
				mobile.setMobileCost(newCost);
				session.update(mobile);
				transaction.commit();
				return "Mobile cost updated succesfully";
			}else {
				return "Mobile not found for update cost";
			}
					
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return " somthing went Wrong";
		}finally{
			session.close();
		}
		
	}
	
	
	
	public List<Mobile> getMobilileByCostRange(int minCost, int maxCost){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Mobile> mobiles = null;
		
		try {
			Query<Mobile> query = session.createQuery("from Mobile where mobileCost between :minCost and :maxCost", Mobile.class);
			query.setParameter("minCost", minCost);
			query.setParameter("maxCost", maxCost);
			  mobiles = query.list();
		}catch (Exception e ) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return mobiles;
	}
	
	
	public List <Mobile> getMobileByBrandName(String brandName){
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM Mobile m WHERE m.brand.name = :brandName";
		Query <Mobile> query = session.createQuery(hql, Mobile.class);
		query.setParameter("brandName", brandName);
		List<Mobile> mobiles = query.list();
		session.close();
		return mobiles;
	}

}

