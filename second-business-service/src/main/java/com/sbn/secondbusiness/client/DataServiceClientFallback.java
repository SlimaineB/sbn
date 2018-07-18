package com.sbn.secondbusiness.client;

import java.util.Arrays;
import java.util.List;

import feign.FeignException;

public class DataServiceClientFallback implements DataServiceClient {

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

		return Arrays.asList("Emply From Fallback : "+rootCause);
	}

}
