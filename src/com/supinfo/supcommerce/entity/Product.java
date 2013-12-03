package com.supinfo.supcommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * Entity implementation class for Entity: Product
 * 
 * @author Elka
 * @version 4.2
 * @since SupCommerce 4.1
 */
@Entity
@Table(name = "products")
@NamedQueries({ @NamedQuery(name = "allProducts", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "deleteProductById", query = "DELETE FROM Product p WHERE p.id = ?1"),
		@NamedQuery(name = "cheaperProducts", query = "SELECT p FROM Product p WHERE p.price < :maxPrice") })
@XmlRootElement
@XmlType(propOrder = { "name", "content", "price" })
public class Product implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long				id;
	private String				name;
	private String				content;
	private Float				price;
	@ManyToOne
	@JoinColumn(name = "category_fk", nullable = true)
	private Category			category;
	
	/**
	 * @return id
	 */
	@XmlAttribute
	public Long getId() {
		return this.id;
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
		return this.name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return content
	 */
	@XmlElement(name = "Description")
	public String getContent() {
		return this.content;
	}
	
	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return price
	 */
	@XmlElement
	public Float getPrice() {
		return this.price;
	}
	
	/**
	 * @param price
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	
	/**
	 * @return category
	 */
	@XmlTransient
	public Category getCategory() {
		return category;
	}
	
	/**
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
