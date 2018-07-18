package com.sbn.jooqdata.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.jooqdata.domain.repository.ProductCategoryRepository;

@RestController
public class FirstDataServiceController {
	
	private static Logger log = LoggerFactory.getLogger(FirstDataServiceController.class);
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategory(@PathVariable String category) {

		
		log.info("Calling getProductByCategory ["+category+"]");
		
		//return Arrays.asList("AB","CD");
		
		return productCategoryRepository.findByName(category).stream().map(p -> p.getName()).collect(Collectors.toList());
		
	}

}
