package com.sbn.erp.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.erp.client.delegate.ClientServiceDelegate;

@RestController
public class ClientServiceController {
	
	@Autowired
	ClientServiceDelegate productServiceDelegate;
	

	@RequestMapping(value = "/listProductsByCategoryRestTemplate/{category}", method = RequestMethod.GET)
	public List<String> getProductsByCategory(@PathVariable String category) {
		return productServiceDelegate.listProductsByCategoryRestTemplate(category);
	}

	
	@RequestMapping(value = "/listProductsByCategoryFeign/{category}", method = RequestMethod.GET)
	public List<String> getSchoolNameDetails(@PathVariable String category) {
		return productServiceDelegate.listProductsByCategoryFeign(category);
	}
	
	
}
