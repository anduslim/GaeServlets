package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.PMF;
import gozolabs.com.entities.Addresses;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


public enum ADDRESSDao {
	INSTANCE;
	
	@SuppressWarnings("unchecked")
	public List<Addresses> listAddresses() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("List Addresses\n");

		String query = "select from " + Addresses.class.getName();
		return (List<Addresses>) pm.newQuery(query).execute();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Addresses> getAddressByBuilding(String inName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single Address By Building, name: " + inName +"\n");
	    
		try {
		    Query query = pm.newQuery(Addresses.class);
		    if (!inName.isEmpty()) {
		    	query.setFilter("buildingName == inName");
		    	query.declareParameters("String inName");
		    	return (List<Addresses>)query.execute(inName);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	
	@SuppressWarnings("unchecked")
	public List<Addresses> getAddressByPostal(String inPostal) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    System.out.println("Single Address By Postal, name: " + inPostal +"\n");
	    
		try {
		    Query query = pm.newQuery(Addresses.class);
		    if (!inPostal.isEmpty()) {
		    	query.setFilter("postalCode == inPostal");
		    	query.declareParameters("String inPostal");
		    	return (List<Addresses>)query.execute(inPostal);
		    }
	    }catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;   
	}
	

	public void add(Addresses address) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(address);
		} finally {
			pm.close();
		}
	}
	

	public void remove(Addresses address) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.currentTransaction().begin();
			address = pm.getObjectById(Addresses.class, address.getId());
			pm.deletePersistent(address);

			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
	
	
	public void updateAddress(Addresses address) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Long id = address.getId();
		String buildingname = address.getBuildingName();
		String block = address.getBlock();
		String unitno = address.getUnitNo();
		String streetname = address.getStreetName();
		String postalcode = address.getPostalCode();

		try {
			pm.currentTransaction().begin();
			address = pm.getObjectById(Addresses.class, address.getId());
			address.setId(id);
			address.setBuildingName(buildingname);
			address.setBlock(block);
			address.setUnitNo(unitno);
			address.setStreetName(streetname);
			address.setPostalCode(postalcode);
			pm.makePersistent(address);
			pm.currentTransaction().commit();
		} catch (Exception ex) {
			pm.currentTransaction().rollback();
			throw new RuntimeException(ex);
		} finally {
			pm.close();
		}
	}
}
