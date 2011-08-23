package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.PMF;
import gozolabs.com.entities.Addresses;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.LoyaltyPromotions;
import gozolabs.com.entities.Merchants;
import gozolabs.com.entities.Promotions;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public enum MERCHANTDao {
	INSTANCE;
	
	@SuppressWarnings("unchecked")
	public List<Merchants> listMerchant() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("List Merchants\n");

		String query = "select from " + Merchants.class.getName();
		return (List<Merchants>) pm.newQuery(query).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Merchants> getMerchantById(String inId) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single Merchant, id: " + inId +"\n");
	    
		try {
		    Query query = pm.newQuery(Merchants.class);
		    if (!inId.isEmpty()) {
		    	query.setFilter("id == inId");
		    	query.declareParameters("String inId");
		    	return (List<Merchants>)query.execute(inId);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	

	public void add(Merchants merchant) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(merchant);
		} finally {
			pm.close();
		}
	}
	

	public void remove(Merchants merchant) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			merchant = pm.getObjectById(Merchants.class, merchant.getId());
			pm.deletePersistent(merchant);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	
	public void updateMerchant(Merchants merchant) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String name = merchant.getName();
		Long id = merchant.getId();
		String contactno = merchant.getContactNo();
		String description = merchant.getDescription();
		boolean hasloyalty = merchant.isHasLoyalty();
		LoyaltyPromotions loyaltypromotion = merchant.getLoyaltyPromo();
		List <Promotions> promotions = merchant.getPromotions();
		List <Addresses> addresses = merchant.getAddresses();
		List <AroundImages> brandimages = merchant.getBrandImages();
		List <AroundImages> images = merchant.getImages();

		try {
			pm.currentTransaction().begin();
			merchant = pm.getObjectById(Merchants.class, merchant.getId());
			merchant.setName(name);
			merchant.setId(id);
			merchant.setContactNo(contactno);
			merchant.setDescription(description);
			merchant.setHasLoyalty(hasloyalty);
			merchant.setLoyaltyPromo(loyaltypromotion);
			merchant.setPromotionList(promotions);
			merchant.setAddresses(addresses);
			merchant.setBrandImage(brandimages);
			merchant.setImages(images);
			pm.makePersistent(merchant);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
}
