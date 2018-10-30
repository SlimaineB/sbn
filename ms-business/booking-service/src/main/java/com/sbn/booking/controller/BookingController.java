package com.sbn.booking.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.sbn.booking.client.BankingClient;
import com.sbn.booking.error.AmpsRequestNotFoundException;
import com.sbn.booking.error.ErrorResponse;
import com.sbn.booking.service.BookingService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class BookingController {

	private static Logger log = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	BookingService bookingService;

	@Autowired
	BankingClient bankingServiceClient;

	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	@Autowired
	private WebClient webClient;

	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping("/welcome")
	public String retrieveWelcomeMessage() {
		// Complex Method
		return welcomeMessage;
	}

	@RequestMapping(value = "/waitTimeSync/{timeToWait}", method = RequestMethod.GET)
	public String waitTimeSync(@PathVariable Integer timeToWait) {
		return bookingService.waitTimeSync(timeToWait);
	}

	@RequestMapping(value = "/waitTimeAsync/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync(@PathVariable Integer timeToWait) {
		return bookingService.waitTimeAsync(timeToWait);
	}

	@RequestMapping(value = "/waitTimeAsync2/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync2(@PathVariable Integer timeToWait) {
		return webClient.get().uri("http://banking-service/waitTimeAsync/" + timeToWait).retrieve()
				.bodyToMono(String.class);
	}

	@RequestMapping(value = "/waitTimeAsync3/{timeToWait}", method = RequestMethod.GET)
	public Mono<String> waitTimeAsync3(@PathVariable Integer timeToWait) {
		return webClient.get().uri("http://localhost:8092/waitTimeAsync/" + timeToWait).retrieve()
				.bodyToMono(String.class);
	}

	@RequestMapping(value = "/book/{clientName}/{amount}", method = RequestMethod.GET)
	public Mono<String> book(@PathVariable String clientName, @PathVariable Integer amount) throws Exception {
		return bookingService.book(clientName, amount);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse("Hey there is an Exception"));
	}
	
	@ExceptionHandler(AmpsRequestNotFoundException.class)
	public ResponseEntity handleAmpsRequestNotFoundException(AmpsRequestNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse("Hey there is AmpsRequestNotFoundException"));
	}

}
