package com.supinfo.supcommerce.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.entity.Product;

/**
 * Product Dao implementation dedied to Persistence through JPA.
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public class JpaProductDao implements ProductDao {
	
	private static final String			ALL_PRODUCTS_QUERY			= "allProducts";
	private static final String			REMOVE_PRODUCT_BY_ID_QUERY	= "deleteProductById";
	private static final String			CHEAPER_PRODUCTS_QUERY		= "cheaperProducts";
	
	private static final String			PRODUCT_MAX_PRICE			= "maxPrice";
	
	private final EntityManagerFactory	emf;
	
	/**
	 * @param emf
	 */
	public JpaProductDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Product addProduct(Product product) {
		final EntityManager em = this.emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(product);
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			
			em.close();
		}
		return product;
	}
	
	@Override
	public Product findProductById(Long id) {
		final EntityManager em = this.emf.createEntityManager();
		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<Product> getAllProducts() {
		final EntityManager em = this.emf.createEntityManager();
		try {
			TypedQuery<Product> query = em.createNamedQuery(ALL_PRODUCTS_QUERY, Product.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@Override
	public void removeProduct(Long id) {
		final EntityManager em = this.emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Query query = em.createNamedQuery(REMOVE_PRODUCT_BY_ID_QUERY);
			query.setParameter(1, id);
			query.executeUpdate();
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
	}
	
	@Override
	public List<Product> getCheaperProductsThan(float maxPrice) {
		final EntityManager em = this.emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		List<Product> cheaperProducts = new ArrayList<Product>();
		
		try {
			transaction.begin();
			TypedQuery<Product> query = em.createNamedQuery(CHEAPER_PRODUCTS_QUERY, Product.class);
			query.setParameter(PRODUCT_MAX_PRICE, maxPrice);
			// Inference Type !
			cheaperProducts = query.getResultList();
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
		return cheaperProducts;
	}
	
}
