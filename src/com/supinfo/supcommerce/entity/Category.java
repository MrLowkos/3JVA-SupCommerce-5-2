package com.supinfo.supcommerce.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity Category Product categories
 * 
 * @author Elka
 * @version 4.1
 * @since SupCommerce 4.1
 */
@Entity
@Table(name = "categories")
@NamedQuery(name = "allCategories", query = "SELECT c FROM Category c")
@XmlRootElement
public class Category implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;
	@Column(unique = true, nullable = false)
	private String				name;
	@OneToMany(mappedBy = "category")
	private List<Product>		products;
	
	/**
	 * @return id
	 */
	@XmlAttribute
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return name
	 */
	@XmlElement
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return products
	 */
	@XmlElement
	public List<Product> getProducts() {
		return products;
	}
	
	/**
	 * @param products
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
