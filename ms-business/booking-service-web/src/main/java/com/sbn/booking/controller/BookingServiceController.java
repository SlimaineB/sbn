package com.sbn.booking.controller;

import java.time.Duration;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbn.booking.MyBadRequestException;
import com.sbn.booking.client.BankingAsynServiceClient;
import com.sbn.booking.client.BankingServiceClient;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class BookingServiceController {

	private static Logger log = LoggerFactory.getLogger(BookingServiceController.class);

	@Autowired
	BankingServiceClient bankingServiceClient;

	@Autowired
	BankingAsynServiceClient bankingAsyncClient;

	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	@Value("${welcome.message}")
	private String welcomeMessage;


	@GetMapping("/welcome")
	public String retrieveWelcomeMessage() {
		// Complex Method
		return welcomeMessage;
	}

	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait) {
		return bankingServiceClient.waitTimeSync(timeToWait);
	}

	@RequestMapping(value = "/throwException/{timeToWait}", method = RequestMethod.GET)
	public String throwException(@PathVariable Integer timeToWait) throws Exception {

		String value = null;
		try {

			value = bankingServiceClient.throwException(timeToWait).toString();
		} catch (MyBadRequestException myBad) {
			System.out.println(myBad);
		}

		return value;
	}

	/*
	 * @RequestMapping(value = "/waitTimeAsync/{timeToWait}", method =
	 * RequestMethod.GET) public Mono<String> waitTimeAsync(@PathVariable Integer
	 * timeToWait) { return
	 * WebClient.builder().baseUrl("http://banking-service").filter(lbFunction).
	 * build()
	 * .get().uri("/waitTimeAsync/"+timeToWait).retrieve().bodyToMono(String.class);
	 * }
	 */

	/*
	 * @RequestMapping(value = "/waitTimeAsync/{timeToWait}", method =
	 * RequestMethod.GET) public DeferredResult<String> waitTimeAsync(@PathVariable
	 * Integer timeToWait) {
	 * 
	 * DeferredResult<String> output = new DeferredResult<>();
	 * 
	 * ForkJoinPool.commonPool().submit(() -> { try {
	 * log.error("Processing in separate thread");
	 * output.setResult(bankingServiceClient.waitTimeSync(timeToWait));
	 * log.error("End Processing in separate thread"+output+" --  "+output.getResult
	 * ()); }catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }); log.error("servlet thread freed"); return output; }
	 */

	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait) {

		// return blockingGet(() -> bankingServiceClient.waitTimeAsync(timeToWait));

		return Mono.just("Toto").delayElement(Duration.ofMillis(timeToWait));

	}

	private <T> Mono<T> blockingGet(final Callable<T> callable) {
		return Mono.fromCallable(callable).subscribeOn(Schedulers.elastic());
	}

}
