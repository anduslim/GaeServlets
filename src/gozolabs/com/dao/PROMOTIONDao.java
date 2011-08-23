package gozolabs.com.dao;

import java.util.Date;
import java.util.List;

import gozolabs.com.PMF;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;
import gozolabs.com.entities.Merchants;
import gozolabs.com.entities.Promotions;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public enum PROMOTIONDao {
	INSTANCE;
	
	@SuppressWarnings("unchecked")
	public List<Promotions> listPromotion() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("List Promotion\n");

		String query = "select from " + Promotions.class.getName();
		return (List<Promotions>) pm.newQuery(query).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Promotions> getPromotionsById(String inId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single Promotions, name: " + inId +"\n");
	    
		try {
		    Query query = pm.newQuery(Promotions.class);
		    if (!inId.isEmpty()) {
		    	query.setFilter("id == inId");
		    	query.declareParameters("String inId");
		    	return (List<Promotions>)query.execute(inId);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	

	public void add(Promotions promotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(promotion);
		} finally {
			pm.close();
		}
	}
	

	public void remove(Promotions promotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			promotion = pm.getObjectById(Promotions.class, promotion.getId());
			pm.deletePersistent(promotion);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	
	public void updatePromotion(Promotions promotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Category category = promotion.getCategory();
		Long id = promotion.getId();
		String title = promotion.getTitle();
		String description = promotion.getDescription();
		Date startdate = promotion.getStartDate();
		Date enddate = promotion.getEndDate();
		boolean isLive = promotion.isLive();
		List<AroundImages> images = promotion.getImages();
		Merchants merchant = promotion.getMerchant();

		try {
			pm.currentTransaction().begin();
			promotion = pm.getObjectById(Promotions.class, promotion.getId());
			promotion.setCategory(category);
			promotion.setId(id);
			promotion.setDescription(description);
			promotion.setTitle(title);
			promotion.setStartDate(startdate);
			promotion.setEndDate(enddate);
			promotion.setLive(isLive);
			promotion.setMerchant(merchant);
			promotion.setImages(images);
			pm.makePersistent(promotion);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
}
