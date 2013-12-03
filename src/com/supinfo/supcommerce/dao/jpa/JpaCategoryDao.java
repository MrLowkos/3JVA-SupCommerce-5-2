package com.supinfo.supcommerce.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import com.supinfo.supcommerce.dao.CategoryDao;
import com.supinfo.supcommerce.entity.Category;

/**
 * Category Dao implementation dedied to Persistence through JPA.
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
public class JpaCategoryDao implements CategoryDao {
	
	private static final String			ALL_CATEGORIES_QUERY	= "allCategories";
	
	private final EntityManagerFactory	emf;
	
	/**
	 * @param emf
	 */
	public JpaCategoryDao(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Category addCategory(Category category) {
		final EntityManager em = this.emf.createEntityManager();
		final EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(category);
			transaction.commit();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			em.close();
		}
		return category;
	}
	
	@Override
	public Category findCategoryById(Long id) {
		final EntityManager em = this.emf.createEntityManager();
		try {
			return em.find(Category.class, id);
		} finally {
			em.close();
		}
	}
	
	@Override
	public List<Category> getAllCategories() {
		final EntityManager em = this.emf.createEntityManager();
		try {
			TypedQuery<Category> query = em.createNamedQuery(ALL_CATEGORIES_QUERY, Category.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	@Override
	public Category findCategoryAndProductsById(Long id) {
		final EntityManager em = emf.createEntityManager();
		try {
			final Category category = em.find(Category.class, id);
			
			// Via le système de Lazy Loading, l'objet Category n'a pas vraiment la liste des Product qui lui sont
			// attachés.
			// On doit donc forcer le chargement de celle liste grâce à la méthode suivante :
			Hibernate.initialize(category.getProducts());
			
			return category;
		} finally {
			em.close();
		}
	}
	
	@Override
	public Category updateCategory(Category category) {
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			category = em.merge(category);
			t.commit();
		} finally {
			if (t.isActive()) {
				t.rollback();
			}
			em.close();
		}
		return category;
	}
	
}
