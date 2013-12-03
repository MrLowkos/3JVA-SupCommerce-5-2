package com.supinfo.supcommerce.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.supcommerce.dao.DaoFactory;
import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

/**
 * class ProductRessource
 * <p>
 * Web Service REST to expose the Product class
 * </p>
 * 
 * @author Elka
 * @version 1.1
 * @since SupCommerce 5.1
 */
@Path("/products")
public class ProductResource {
	
	private final ProductDao	productDao;
	
	/**
	 * Constructor
	 */
	public ProductResource() {
		productDao = DaoFactory.getProductDao();
	}
	
	/**
	 * Lors d'une requête HTTP GET sur /resources/products avec un accept content-type "text/plain", retourne
	 * "Hello World, i'm a web service REST !".
	 * 
	 * @return "Hello World!!"
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloWorld() {
		return "Hello World, i'm a web service REST !";
	}
	
	/**
	 * Lors d'une requête HTTP GET sur /resources/products/XX avec un accept content-type "application/xml" ou
	 * "application/json", retourne la representation xml ou json de la liste des Product en base.
	 * 
	 * @return Liste des Product qui sera ensuite transcrit pour le client en sa representation xml ou json.
	 */
	@GET
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}
	
	/**
	 * Lors d'une requête HTTP GET sur /resources/products/XX avec un accept content-type "application/xml" ou
	 * "application/json", retourne la representation xml ou json du Product dont l'ID est celui passé en paramètre.
	 * 
	 * @param productId
	 *            ID du produit à retrouver en base.
	 * @return Product qui sera ensuite transcrit pour le client en sa representation xml ou json.
	 */
	@GET
	@Path("/{productId}")
	public Product getProduct(@PathParam("productId") Long productId) {
		return productDao.findProductById(productId);
	}
	
	/**
	 * Lors d'une requête HTTP DELETE sur /resources/products/XX, supprimer le Product dont l'ID est celui passé en
	 * paramètre.
	 * 
	 * @param productId
	 *            ID du produit à supprimer en base.
	 */
	@DELETE
	@Path("/{productId}")
	public void removeProduct(@PathParam("productId") Long productId) {
		productDao.removeProduct(productId);
	}
	
}
