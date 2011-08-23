package gozolabs.com.dao;

import java.io.IOException;
import java.util.List;

import gozolabs.com.EMFService;
import gozolabs.com.PMF;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Transaction;

public enum CATEGORYDao {
	INSTANCE;
	
	public List<Category> listCategory() {
//		EntityManager em = EMFService.get().createEntityManager();
//		// Read the existing entries
//		Query q = em.createQuery("select m from gozolabs.com.entities.Category m");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Category> catList;
	    System.out.println("List Category\n");
		Query query = pm.newQuery(Category.class);
		catList = (List<Category>)query.execute();
		System.out.println("Out:" + query.toString());
		return catList;
	}

	public void add(String name) {
		synchronized (this) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {

				Category cate = new Category(name);
				pm.makePersistent(cate);
				//newTrans.commit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}   
//			finally {
//				if (newTrans.isActive())
//				{
//					newTrans.rollback();
//				}
//			}
//			em.close();
		}
	}

	public List<Category> getCategory(String inName) {
		//EntityManager em = EMFService.get().createEntityManager();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Category> catList = null;
	    System.out.println("Single Category, name: " + inName +"\n");
		try {
		    Query query = pm.newQuery(Category.class);
		    System.out.println("name" + inName);
		    if (!inName.isEmpty()) {
		    	query.setFilter("name == inName");
		    	query.declareParameters("String inName");
		    	catList = (List<Category>)query.execute(inName);
		    	System.out.println("Out:" + query.toString());
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}   
//			em.getTransaction().begin();
//			Query q = em
//					.createQuery("select t from gozolabs.com.entities.Category t where t.name = :name");
//			q.setParameter("name", name);
			//catList = q.getResultList();
//		}
//		finally {
//			if (em.getTransaction().isActive())
//			{
//				em.getTransaction().rollback();
//			}
//		}
//		em.close();
		
		return catList;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Category cate = em.find(Category.class, id);
			em.remove(cate);
		} finally {
			em.close();
		}
	}
}
