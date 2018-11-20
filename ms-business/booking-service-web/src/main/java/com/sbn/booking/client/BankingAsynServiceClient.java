package com.sbn.booking.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BankingAsynServiceClient {

	
	@Autowired
	BankingServiceClient bankingServiceClient;
	
	
	@Async
	public String waitTimeAsync( Integer timeToWait) {
		return bankingServiceClient.waitTimeAsync(timeToWait);
	}
}
