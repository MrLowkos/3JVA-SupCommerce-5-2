package com.supinfo.supcommerce.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public class PersistenceManager {
	
	private static EntityManagerFactory	emf;
	
	// Private constructor - Instanciation is locked (Singleton Patern)
	private PersistenceManager() {
	}
	
	/**
	 * Static method to assure that EntityManagerFactory can be only instanciate once (Singleton Patern)
	 * 
	 * @return emf
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("supcommerce-pu");
		}
		return emf;
	}
	
	/**
	 * 
	 */
	public static void closeEntityManagerFactory() {
		if (emf != null && emf.isOpen()) {
			emf.close();
			emf = null;
		}
	}
}
