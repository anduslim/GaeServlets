package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.EMFService;
import gozolabs.com.entities.Addresses;
import gozolabs.com.entities.AroundImages;
import gozolabs.com.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Blob;

public enum ADDRESSDao {
	INSTANCE;
	
	public List<Addresses> listCategory() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from gozolabs.com.entities.Addresses m");
		List<Addresses> addList = q.getResultList();
		return addList;
	}

	public void add(String buildingname, String streetname, String unitno, String block, String postalcode) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Addresses address = new Addresses(buildingname, unitno, block, streetname, postalcode);
			em.persist(address);
			em.close();
		}
	}

	public List<Addresses> getCategory(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from gozolabs.com.entities.Addresses t where t.name = :name");
		q.setParameter("name", name);
		List<Addresses> catList = q.getResultList();
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
