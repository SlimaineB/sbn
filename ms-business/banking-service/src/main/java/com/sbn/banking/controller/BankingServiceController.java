package com.sbn.banking.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class BankingServiceController {

	private static Logger log = LoggerFactory.getLogger(BankingServiceController.class);

	@Autowired
	ReactService reactiveService;

	
	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait) {

		try {
			Thread.sleep(timeToWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return  "OK waiting "+timeToWait;
	}

	@RequestMapping(value = "/throwException/{timeToWait}", method = RequestMethod.GET)
	public ResponseEntity<Car> throwException(@PathVariable Integer timeToWait) throws Exception {
		return ResponseEntity.status(404).body(new Car("blue","peugeot"));
	}
	
	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait) {

		return Mono.just("OK waiting "+timeToWait)
        .delayElement(Duration.ofMillis(timeToWait));

	}
	
	
//	
//	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
//	public Mono<List<String>> getProductByCategory(@PathVariable String category) {
//
//		log.info("Calling getProductByCategory [" + category + "]");
//
//		/*
//		 * try { Thread.sleep(100); } catch (InterruptedException e) { // TODO
//		 * Auto-generated catch block e.printStackTrace(); }
//		 */
//
//		return Flux.fromIterable(Arrays.asList("Tomate", "Courgette")).collectList()
//				.switchIfEmpty(Mono.error(new Exception("No Product found for category : " + category)));
//
//	}
//
//	@RequestMapping(value = "/test")
//	public @ResponseBody CompletableFuture<String> test() throws InterruptedException {
//
//		long start = System.currentTimeMillis();
//
//		CompletableFuture<Boolean> boolean1 = reactiveService.veryLongMethod();
//		CompletableFuture<Boolean> boolean2 = reactiveService.veryLongMethod();
//		CompletableFuture<Boolean> boolean3 = reactiveService.veryLongMethod();
//
//		CompletableFuture.allOf(boolean1, boolean2, boolean3).join();
//
//		long end = System.currentTimeMillis();
//
//		System.out.println("Took " + (end - start) + " ms");
//
//		return CompletableFuture.completedFuture("abc");
//	}

}
