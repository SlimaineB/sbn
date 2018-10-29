package com.sbn.banking.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ReactService {

	
	@Async
	public  CompletableFuture<Boolean> veryLongMethod()  {

	    try {
	        Thread.sleep(5000L);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    return CompletableFuture.completedFuture(true);
	}
	
}
