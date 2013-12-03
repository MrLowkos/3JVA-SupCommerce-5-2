package com.supinfo.supcommerce.dao;

import java.util.List;

import com.supinfo.supcommerce.entity.Category;

/**
 * Interface - Persistence management of Category Entities.
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public interface CategoryDao {
	
	/**
	 * Add category in database.
	 * 
	 * @param category
	 *            {@link Category} to add.
	 * @return added {@link Category}.
	 */
	Category addCategory(Category category);
	
	/**
	 * Retrieve Category by ID in database.
	 * 
	 * @param id
	 *            {@link Long} Primary Key.
	 * @return {@link Category} associated to ID .
	 */
	Category findCategoryById(Long id);
	
	/**
	 * @return All categories in database.
	 */
	List<Category> getAllCategories();
	
	/**
	 * Retrieve Category by ID and all Product associated.
	 * 
	 * @param id
	 *            {@link Long} Primary Key.
	 * @return {@link Category} associated to ID and Product associated.
	 */
	Category findCategoryAndProductsById(Long id);
	
	/**
	 * Update Category in database.
	 * 
	 * @param category
	 *            Category to update.
	 * @return Category updated.
	 */
	Category updateCategory(Category category);
	
}
