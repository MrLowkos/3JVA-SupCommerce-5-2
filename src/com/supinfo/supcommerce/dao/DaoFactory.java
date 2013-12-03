package com.supinfo.supcommerce.dao;

import com.supinfo.supcommerce.dao.jpa.JpaCategoryDao;
import com.supinfo.supcommerce.dao.jpa.JpaProductDao;
import com.supinfo.supcommerce.util.PersistenceManager;

/**
 * Factory to retrieve Data Access Object of Product and Category entities.
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public class DaoFactory {
	
	/**
	 * @return New Category entities Persistence manager.
	 */
	public static CategoryDao getCategoryDao() {
		return new JpaCategoryDao(PersistenceManager.getEntityManagerFactory());
	}
	
	/**
	 * @return New Product entities Persistence manager.
	 */
	public static ProductDao getProductDao() {
		return new JpaProductDao(PersistenceManager.getEntityManagerFactory());
	}
	
}
