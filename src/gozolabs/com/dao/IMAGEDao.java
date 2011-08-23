package gozolabs.com.dao;

import java.util.List;

import gozolabs.com.EMFService;
import gozolabs.com.entities.AroundImages;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Blob;

public enum IMAGEDao {
	INSTANCE;
	
	public List<AroundImages> listImages() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from gozolabs.com.entities.AroundImages m");
		List<AroundImages> myimages = q.getResultList();
		return myimages;
	}

	public void add(String name, Blob newimage) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			AroundImages todo = new AroundImages(name, newimage);
			em.persist(todo);
			em.close();
		}
	}

	public List<AroundImages> getImage(String name) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from gozolabs.com.entities.AroundImages t where t.filename = :name");
		q.setParameter("filename", name);
		List<AroundImages> image = q.getResultList();
		return image;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			AroundImages image = em.find(AroundImages.class, id);
			em.remove(image);
		} finally {
			em.close();
		}
	}
}
