package com.sbn.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "banking-service")
public interface BankingServiceClient {
	
	
	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait);

	
//	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
//	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait);
	
	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeAsync(@PathVariable Integer timeToWait);
}


