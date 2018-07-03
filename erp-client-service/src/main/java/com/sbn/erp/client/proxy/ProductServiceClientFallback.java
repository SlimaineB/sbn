package com.sbn.erp.client.proxy;

import java.util.Arrays;
import java.util.List;

import feign.FeignException;

public class ProductServiceClientFallback implements ProductServiceClient {

	private final Throwable rootCause;

	public ProductServiceClientFallback(Throwable rootCause) {
		super();
		this.rootCause = rootCause;
	}

	@Override
	public List<String> listProductsByCategory(String category) {

		if (rootCause instanceof FeignException && ((FeignException) rootCause).status() == 404) {
			// Treat the HTTP 404 status
		}

		return Arrays.asList("Emply from Fallback");
	}

}
