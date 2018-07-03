package com.sbn.erp.client.proxy;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class ProductServiceClientFallbackFactory implements FallbackFactory<ProductServiceClient> {

	@Override
	public ProductServiceClient create(Throwable cause) {
		return new ProductServiceClientFallback(cause);
	}

}
