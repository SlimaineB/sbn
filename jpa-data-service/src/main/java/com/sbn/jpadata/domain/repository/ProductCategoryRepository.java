package com.sbn.jpadata.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbn.jpadata.domain.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
	
	public ProductCategory findByName(String name);

}
