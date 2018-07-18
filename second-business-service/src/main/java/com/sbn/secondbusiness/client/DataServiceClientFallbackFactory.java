package com.sbn.secondbusiness.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class DataServiceClientFallbackFactory implements FallbackFactory<DataServiceClient> {

	@Override
	public DataServiceClient create(Throwable cause) {
		return new DataServiceClientFallback(cause);
	}

}
