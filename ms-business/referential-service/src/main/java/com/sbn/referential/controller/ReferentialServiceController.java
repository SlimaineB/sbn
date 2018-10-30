package com.sbn.referential.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class ReferentialServiceController {

	private static Logger log = LoggerFactory.getLogger(ReferentialServiceController.class);

	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait) {

		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return  "OK Referential waitTimeSync "+timeToWait;
	}

	
	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait) {

		return Mono.just("OK Referential waitTimeAsync "+timeToWait)
        .delayElement(Duration.ofMillis(timeToWait));

	}
	
	@RequestMapping(value = "/getClientAccount/{clientName}", method = RequestMethod.GET)
	public Mono<String> getClientAccount(@PathVariable String clientName) {
		return Mono.just(clientName+"_001122")
		        .delayElement(Duration.ofMillis(200));
	}
}
