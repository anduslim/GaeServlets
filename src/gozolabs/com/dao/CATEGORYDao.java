package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.PMF;
import gozolabs.com.entities.Category;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public enum CATEGORYDao {
	INSTANCE;
	
	@SuppressWarnings("unchecked")
	public List<Category> listCategory() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("List Category\n");

		String query = "select from " + Category.class.getName();
		return (List<Category>) pm.newQuery(query).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategory(String inName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single Category, name: " + inName +"\n");
	    
		try {
		    Query query = pm.newQuery(Category.class);
		    if (!inName.isEmpty()) {
		    	query.setFilter("name == inName");
		    	query.declareParameters("String inName");
		    	return (List<Category>)query.execute(inName);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	

	public void add(Category category) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(category);
		} finally {
			pm.close();
		}
	}
	

	public void remove(Category category) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			category = pm.getObjectById(Category.class, category.getId());
			pm.deletePersistent(category);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	
	public void updateCategory(Category category) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String name = category.getName();
		Long id = category.getId();

		try {
			pm.currentTransaction().begin();
			category = pm.getObjectById(Category.class, category.getId());
			category.setName(name);
			category.setId(id);
			pm.makePersistent(category);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
}
