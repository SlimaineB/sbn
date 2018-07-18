package com.sbn.firstbusiness.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "data-service", fallbackFactory=DataServiceClientFallbackFactory.class)
public interface DataServiceClient {

	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	List<String> getProductByCategory(@PathVariable("category") String category);
}


