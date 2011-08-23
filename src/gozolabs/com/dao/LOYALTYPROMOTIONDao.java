package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.PMF;
import gozolabs.com.entities.LoyaltyPromotions;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public enum LOYALTYPROMOTIONDao {
	INSTANCE;
	
	@SuppressWarnings("unchecked")
	public List<LoyaltyPromotions> listLoyaltyPromotion() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("List Loyalty Promotion\n");

		String query = "select from " + LoyaltyPromotions.class.getName();
		return (List<LoyaltyPromotions>) pm.newQuery(query).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LoyaltyPromotions> getLoyaltyPromotionsById(String inId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single LoyaltyPromotions, name: " + inId +"\n");
	    
		try {
		    Query query = pm.newQuery(LoyaltyPromotions.class);
		    if (!inId.isEmpty()) {
		    	query.setFilter("id == inId");
		    	query.declareParameters("String inId");
		    	return (List<LoyaltyPromotions>)query.execute(inId);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	

	public void add(LoyaltyPromotions loyaltyPromotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(loyaltyPromotion);
		} finally {
			pm.close();
		}
	}
	

	public void remove(LoyaltyPromotions loyaltyPromotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			loyaltyPromotion = pm.getObjectById(LoyaltyPromotions.class, loyaltyPromotion.getId());
			pm.deletePersistent(loyaltyPromotion);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	
	public void updateLoyaltyPromotion(LoyaltyPromotions loyaltyPromotion) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String block = loyaltyPromotion.getBlock();
		Long id = loyaltyPromotion.getId();
		String reward = loyaltyPromotion.getReward();
		String postalcode = loyaltyPromotion.getPostalCode();
		String streetname = loyaltyPromotion.getStreetName();
		String tnc = loyaltyPromotion.getTnc();

		try {
			pm.currentTransaction().begin();
			loyaltyPromotion = pm.getObjectById(LoyaltyPromotions.class, loyaltyPromotion.getId());
			loyaltyPromotion.setBlock(block);
			loyaltyPromotion.setId(id);
			loyaltyPromotion.setPostalCode(postalcode);
			loyaltyPromotion.setReward(reward);
			loyaltyPromotion.setStreetName(streetname);
			loyaltyPromotion.setTnc(tnc);
			pm.makePersistent(loyaltyPromotion);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
}
