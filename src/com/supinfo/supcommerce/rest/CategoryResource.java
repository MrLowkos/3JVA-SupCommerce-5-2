package com.supinfo.supcommerce.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.entity.Category;

/**
 * class CategoryRessource
 * <p>
 * Web Service REST to expose the Category class
 * </p>
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 5.2
 */
@Path("/categories")
public class CategoryResource {
	
	private final CategoryDao	categoryDao;
	
	/**
	 * Constructor
	 */
	public CategoryResource() {
		categoryDao = DaoFactory.getCategoryDao();
	}
	
	/**
	 * Lors d'une requête HTTP POST avec un body qui contient la representation JSON ou XML d'une Category, ajoute la
	 * Category en base.
	 * 
	 * @param category
	 *            Category à ajouter en base.
	 * @return Category ajoutée en base.
	 */
	@POST
	public Category addCategory(Category category) {
		return categoryDao.addCategory(category);
	}
	
	/**
	 * Lors d'une requête HTTP GET sur /resources/categories/XX avec un accept content-type "application/xml" ou
	 * "application/json", retourne la representation xml ou json de la Category dont l'ID est celui passé en paramètre.
	 * 
	 * @param categoryId
	 *            ID de la Category à retrouver en base.
	 * @return Category qui sera ensuite transcrite pour le client en sa representation xml ou json.
	 */
	@GET
	@Path("/{categoryId}")
	public Category getCategory(@PathParam("categoryId") Long categoryId) {
		final Category category = categoryDao.findCategoryAndProductsById(categoryId);
		
		return category;
	}
	
	/**
	 * Lors d'une requête HTTP PUT avec un body qui contient la representation JSON ou XML d'une Category, modifie la
	 * Category en base par celle donnée.
	 * 
	 * @param category
	 *            Category à modifier en base.
	 * @return Category modifiée en base.
	 */
	@PUT
	public Category updateCategory(Category category) {
		return categoryDao.updateCategory(category);
	}
}
