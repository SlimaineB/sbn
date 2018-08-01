package com.sbn.reactdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.sbn.reactdata.client" })
@EnableCircuitBreaker
@EnableHystrix
@EnableAsync
public class ReactDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactDataServiceApplication.class, args);
	}
	
	

}
