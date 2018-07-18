package com.sbn.secondbusiness.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.secondbusiness.client.DataServiceClient;

@RestController
public class SecondBusinessServiceController {

	private static Logger log = LoggerFactory.getLogger(SecondBusinessServiceController.class);

	@Autowired
	DataServiceClient dataServiceClient;

	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategory(@PathVariable String category) {
		
		log.info("Calling getProductByCategory ["+category+"]");

		return dataServiceClient.getProductByCategory(category);

	}

}
