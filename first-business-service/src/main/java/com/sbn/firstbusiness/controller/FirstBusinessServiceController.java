package com.sbn.firstbusiness.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.firstbusiness.client.DataServiceClient;
import com.sbn.firstbusiness.client.SecondServiceClient;
import com.sbn.firstbusiness.dto.Product;

@RestController
public class FirstBusinessServiceController {

	private static Logger log = LoggerFactory.getLogger(FirstBusinessServiceController.class);

	@Autowired
	DataServiceClient dataServiceClient;
	
	@Autowired
	SecondServiceClient secondServiceClient;
	
	
	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping("/welcome")
	public String retrieveWelcomeMessage() {
		// Complex Method
		return welcomeMessage;
	}
	
	
	@GetMapping("/tomate")
	public String getTomateMessage() throws InterruptedException {
		
		//Thread.sleep(100);
		// Complex Method
		return "Tomate";
	}

	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategory(@PathVariable String category) {
		
		log.info("Calling getProductByCategory ["+category+"]");

		return dataServiceClient.getProductByCategory(category);

	}
	
	@RequestMapping(value = "/getProductByCategoryNoDb/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategoryNoDb(@PathVariable String category) {
		
		log.info("Calling getProductByCategoryNoDb ["+category+"]");

		return secondServiceClient.getProductByCategoryNoDb(category);

	}
	
	
	@RequestMapping(value = "/getProductByName/{name}", method = RequestMethod.GET)
	public Product getProductByName(@PathVariable String name) {
		
		log.info("Calling getProductByName ["+name+"]");

		return new Product("Pomme",BigDecimal.valueOf(10.5));

	}

}
