package com.sbn.jpadata.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbn.jpadata.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
