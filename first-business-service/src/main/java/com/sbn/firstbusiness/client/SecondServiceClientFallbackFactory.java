package com.sbn.firstbusiness.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SecondServiceClientFallbackFactory implements FallbackFactory<SecondServiceClient> {

	@Override
	public SecondServiceClient create(Throwable cause) {
		return new SecondServiceClientFallback(cause);
	}

}
