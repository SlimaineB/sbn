package com.sbn.jooqdata.domain.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

	public static final String PRODUCT = "PRODUCT";

	private Long id;

	private String name;

	private ProductCategory category;

	private BigDecimal price;

	private Date creationDate;

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String name, BigDecimal price, Date creationDate) {
		super();
		this.name = name;
		this.price = price;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
