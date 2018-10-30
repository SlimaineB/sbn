package com.sbn.booking.service;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sbn.booking.client.BankingClient;
import com.sbn.booking.client.ReferentialClient;
import com.sbn.booking.error.AmpsRequestNotFoundException;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class BookingService {

	private static Logger log = LoggerFactory.getLogger(BookingService.class);
	
	@Autowired
	BankingClient bankingServiceClient;
	
	@Autowired
	ReferentialClient referentialClient;
	
	/**
	 * 
	 * @param timeToWait
	 * @return
	 */
	public Mono<String> waitTimeAsync(Integer timeToWait){
		return blockingGet(() -> bankingServiceClient.waitTimeAsync(timeToWait));
	}
	
	/**
	 * 
	 * @param timeToWait
	 * @return
	 */
	public String waitTimeSync(@PathVariable Integer timeToWait) {
		return bankingServiceClient.waitTimeSync(timeToWait);
	}

	
	
	public Mono<String> book(String clientName, Integer amount) throws Exception{
		
		log.info("Calling book ");
		
		//throw new AmpsRequestNotFoundException("My Exception");
		Mono<String> result = getClientAccount(clientName).flatMap(accountNumber -> debitAmount(accountNumber, amount));
		
		
		//Mono<String> result = Mono.fromCallable(() -> referentialClient.getClientAccount(clientName))/*.flatMap(e -> Mono.fromCallable(() -> bankingServiceClient.debitAmount(e , amount)))*/.subscribeOn(Schedulers.elastic());
		
		return result;

	}
	
	
	
	/**
	 * 
	 * @param timeToWait
	 * @return
	 */
	public Mono<String> getClientAccount(String clientName){
		log.info("Calling getClientAccount "+clientName);
		return blockingGet(() -> referentialClient.getClientAccount(clientName));
	}
	
	
	public Mono<String> debitAmount(String accountNumber, Integer amount) {
		log.info("Calling debitAmount "+accountNumber+" - "+amount);
		return blockingGet(() -> bankingServiceClient.debitAmount(accountNumber, amount));
	}
	
	/**
	 * Calling asynchronously (in a different thread) a synchronous method
	 * 
	 * @param callable
	 * @return
	 */
	private <T> Mono<T> blockingGet(final Callable<T> callable) {
		return Mono.fromCallable(callable).subscribeOn(Schedulers.elastic());
	}
}
