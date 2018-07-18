package com.sbn.firstbusiness.client;

import java.util.Arrays;
import java.util.List;

import feign.FeignException;

public class SecondServiceClientFallback implements SecondServiceClient {

	private final Throwable rootCause;

	public SecondServiceClientFallback(Throwable rootCause) {
		super();
		this.rootCause = rootCause;
	}

	@Override
	public List<String> getProductByCategoryNoDb(String category) {

		if (rootCause instanceof FeignException && ((FeignException) rootCause).status() == 404) {
			// Treat the HTTP 404 status
		}

		return Arrays.asList("Emply From Fallback : "+rootCause);
	}

}
