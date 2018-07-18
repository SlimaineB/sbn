package com.sbn.firstbusiness.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "second-business-service", fallbackFactory=SecondServiceClientFallbackFactory.class)
public interface SecondServiceClient {

	@RequestMapping(value = "/getProductByCategoryNoDb/{category}", method = RequestMethod.GET)
	List<String> getProductByCategoryNoDb(@PathVariable("category") String category);
}


