package com.sbn.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "referential-service")
public interface ReferentialClient {
	
	
	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait);

	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeAsync(@PathVariable Integer timeToWait);
	
	@RequestMapping(value = "/getClientAccount/{clientName}", method = RequestMethod.GET)
	public String getClientAccount(@PathVariable String clientName);
}


