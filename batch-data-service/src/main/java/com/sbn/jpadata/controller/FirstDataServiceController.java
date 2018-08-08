package com.sbn.jpadata.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.jpadata.domain.repository.ProductCategoryRepository;

@RestController
@RefreshScope
public class FirstDataServiceController {
	
	private static Logger log = LoggerFactory.getLogger(FirstDataServiceController.class);
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategory(@PathVariable String category) {

		
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		log.info("Calling getProductByCategory ["+category+"]");

		return productCategoryRepository.findByName(category).getProducts().stream().map(product -> product.getName()).collect(Collectors.toList());
		
	}

}
