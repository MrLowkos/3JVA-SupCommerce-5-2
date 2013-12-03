package com.supinfo.supcommerce.dao;

import java.util.List;

import com.supinfo.supcommerce.entity.Product;

/**
 * Interface - Persistence management of Product Entities.
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public interface ProductDao {
	
	/**
	 * Add product in database.
	 * 
	 * @param product
	 *            {@link Product} to add.
	 * @return added {@link Product}.
	 */
	Product addProduct(Product product);
	
	/**
	 * Retrieve Product by ID in database.
	 * 
	 * @param id
	 *            {@link Long} Primary Key.
	 * @return {@link Product} associated to ID .
	 */
	Product findProductById(Long id);
	
	/**
	 * @return All products in database.
	 */
	List<Product> getAllProducts();
	
	/**
	 * Remove ProductS by ID in database.
	 * 
	 * @param id
	 *            {@link Long} Primary Key.
	 */
	void removeProduct(Long id);
	
	/**
	 * Retrieve all products lower than a specific price.
	 * 
	 * @param maxPrice
	 * @return All products lower than a specific price (maxPrice).
	 */
	List<Product> getCheaperProductsThan(float maxPrice);
	
}
