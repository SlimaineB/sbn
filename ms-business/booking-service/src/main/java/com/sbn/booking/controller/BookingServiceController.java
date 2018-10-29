package com.sbn.booking.controller;

import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.reactive.function.client.WebClient;

import com.sbn.booking.client.BankingAsynServiceClient;
import com.sbn.booking.client.BankingServiceClient;

import reactor.core.publisher.Mono;

@RestController
public class BookingServiceController {

	private static Logger log = LoggerFactory.getLogger(BookingServiceController.class);

	@Autowired
	BankingServiceClient bankingServiceClient;
	
	@Autowired
	BankingAsynServiceClient bankingAsyncClient;

	
	/*@Autowired
	private WebClient.Builder webClientBuilder;*/
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private LoadBalancerExchangeFilterFunction lbFunction;
	
	
	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping("/welcome")
	public String retrieveWelcomeMessage() {
		// Complex Method
		return welcomeMessage;
	}
	
	
	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait) {
		return  bankingServiceClient.waitTimeSync(timeToWait);
	}

	
	/*@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait) {
		 return WebClient.builder().baseUrl("http://banking-service").filter(lbFunction).build()
				.get().uri("/waitTimeAsync/"+timeToWait).retrieve().bodyToMono(String.class);
	}*/
	
	@RequestMapping(value = "/waitTimeAsync2/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync2(@PathVariable Integer timeToWait) {
		 return webClient.get().uri("http://banking-service/waitTimeAsync/"+timeToWait).retrieve().bodyToMono(String.class);
	}
	
	/*@RequestMapping(value = "/waitTimeAsync3/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync3(@PathVariable Integer timeToWait) {
		 return webClientBuilder.build()
				.get().uri("http://localhost:8092/waitTimeAsync/"+timeToWait).retrieve().bodyToMono(String.class);
	}*/

	
	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public DeferredResult<String> waitTimeAsync(@PathVariable Integer timeToWait) {
		
		DeferredResult<String> output = new DeferredResult<>();
		
		ForkJoinPool.commonPool().submit(() -> {
			try {
	        log.error("Processing in separate thread");
	        output.setResult(bankingServiceClient.waitTimeSync(timeToWait));
	        log.error("End Processing in separate thread"+output+" --  "+output.getResult());
			}catch (Exception e) {
				e.printStackTrace();
			}
	        
	    });
		log.error("servlet thread freed");
		 return output;
	}
	

	
}
