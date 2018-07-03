package com.sbn.erp.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.erp.product.domain.Product;

@RestController
public class ProductServiceController {

	private static List<Product> productDb;

	static {
		Long idCounter = 1L;
		productDb = new ArrayList<Product>();

		productDb.add(new Product(idCounter++, "Baname", "Fruit"));
		productDb.add(new Product(idCounter++, "Courgette", "Legume"));
		productDb.add(new Product(idCounter++, "Patate", "Legume"));
		productDb.add(new Product(idCounter++, "Pomme", "Fruit"));

	}

	@RequestMapping(value = "/listProductsByCategory/{category}", method = RequestMethod.GET)
	public List<String> getStudentDetailsForSchool(@PathVariable String category) {
		
		System.out.println("Listing products of category : " + category);

		return productDb.stream().filter(product -> product.getCategory().equals(category)).map(product -> product.getName()).collect(Collectors.toList());
	
	}


}
