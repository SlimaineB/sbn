package com.sbn.erp.client.proxy;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "product-service", fallbackFactory=ProductServiceClientFallbackFactory.class)
public interface ProductServiceClient {

	@RequestMapping(value = "/listProductsByCategory/{category}", method = RequestMethod.GET)
	List<String> listProductsByCategory(@PathVariable("category") String category);
}
