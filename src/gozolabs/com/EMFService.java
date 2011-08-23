package gozolabs.com;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFService {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("eventual-reads-short-deadlines");

	private EMFService() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}