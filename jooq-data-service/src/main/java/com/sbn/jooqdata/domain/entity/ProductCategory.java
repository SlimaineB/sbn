package com.sbn.jooqdata.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {

	public static final String PRODUCT_CATEGORY = "PRODUCT_CATEGORY";

	private Long id;

	private String name;

	List<Product> products = new ArrayList<Product>();

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
		product.setCategory(this);
	}

}
