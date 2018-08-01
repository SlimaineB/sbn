package com.sbn.reactdata.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactDataServiceController {

	private static Logger log = LoggerFactory.getLogger(ReactDataServiceController.class);

	@Autowired
	ReactService reactiveService;
	
	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public Mono<List<String>> getProductByCategory(@PathVariable String category) {

		log.info("Calling getProductByCategory [" + category + "]");
		
	/*	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		return Flux.fromIterable(Arrays.asList("Tomate", "Courgette")).collectList().
                switchIfEmpty(Mono.error(new Exception("No Product found for category : "+category)));

	}
	
	  @RequestMapping(value = "/test")
	  public @ResponseBody CompletableFuture<String> test() throws InterruptedException {
		  
		  long start = System.currentTimeMillis();
		  
	        CompletableFuture<Boolean> boolean1= reactiveService.veryLongMethod();
	        CompletableFuture<Boolean> boolean2= reactiveService.veryLongMethod();
	        CompletableFuture<Boolean> boolean3= reactiveService.veryLongMethod();

	        CompletableFuture.allOf(boolean1,boolean2,boolean3).join();
	 
	        
	        long end = System.currentTimeMillis();
	        
	        System.out.println("Took "+(end - start)+ " ms");
	        
	        return CompletableFuture.completedFuture("abc");
	  } 



	
}
