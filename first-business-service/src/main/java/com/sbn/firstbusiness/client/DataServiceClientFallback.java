package com.sbn.firstbusiness.client;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbn.firstbusiness.dto.Product;

import feign.FeignException;

public class DataServiceClientFallback implements DataServiceClient {

	private static Logger log = LoggerFactory.getLogger(DataServiceClientFallback.class);
	
	private final Throwable rootCause;

	public DataServiceClientFallback(Throwable rootCause) {
		super();
		this.rootCause = rootCause;
	}

	@Override
	public List<String> getProductByCategory(String category) {

		if (rootCause instanceof FeignException && ((FeignException) rootCause).status() == 404) {
			// Treat the HTTP 404 status
		}

		log.error(rootCause.getMessage());
		
		return Arrays.asList("Emply From Fallback : "+rootCause);
	}

	@Override
	public Product getProductByName(String name) {
		
		if (rootCause instanceof FeignException && ((FeignException) rootCause).status() == 404) {
			// Treat the HTTP 404 status
		}

		log.error(rootCause.getStackTrace().toString());
		
		return null;
	}
}
