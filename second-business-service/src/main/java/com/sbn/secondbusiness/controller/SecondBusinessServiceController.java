package com.sbn.secondbusiness.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RestController
public class SecondBusinessServiceController {

	private static Logger log = LoggerFactory.getLogger(SecondBusinessServiceController.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public Flux<String> getProductByCategory(@PathVariable String category) {
		
		log.info("Calling getProductByCategory ["+category+"]");

		System.out.println("End 0");
		
		Flux<String> reponse1 = webClientBuilder.build().get().uri("http://data-service/getProductByCategory/"+category)
				.retrieve().bodyToFlux(String.class);
		
		System.out.println("End 1");
		
		Flux<String> reponse2 = webClientBuilder.build().get().uri("http://data-service/getProductByCategory/"+category)
				.retrieve().bodyToFlux(String.class);
		
		System.out.println("End 2");
		
//		Flux<String> merged =  list1.mergeWith(list2);
//		
//		System.out.println("End 3");
//		
//		merged.subscribe(System.out::print);
//		
//		System.out.println("End 4");
		
		
		
		return Flux.zip(reponse1, reponse2).map(reponseAll -> Arrays.asList(reponseAll.getT1() , reponseAll.getT2())).map(obj -> obj.toString());
		//return Flux.zip(reponse1, reponse2).concat();

	}
	
	
	@RequestMapping(value = "/getProductByCategoryMono/{category}", method = RequestMethod.GET)
	public Flux<String> getProductByCategoryMono(@PathVariable String category) {
		
		log.info("Calling getProductByCategory ["+category+"]");

		System.out.println("End 0");
		
		webClientBuilder.build().get().uri("http://data-service/getProductByCategory/"+category)
		.retrieve().bodyToMono(Object.class).subscribe(System.out::println);

		
		return webClientBuilder.build().get().uri("http://data-service/getProductByCategory/"+category)
				.retrieve().bodyToFlux(String.class);

	}

}
