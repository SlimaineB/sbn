package com.sbn.firstbusiness.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.firstbusiness.client.DataServiceClient;
import com.sbn.firstbusiness.client.SecondServiceClient;

@RestController
public class FirstBusinessServiceController {

	private static Logger log = LoggerFactory.getLogger(FirstBusinessServiceController.class);

	@Autowired
	DataServiceClient dataServiceClient;
	
	@Autowired
	SecondServiceClient secondServiceClient;

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

}
