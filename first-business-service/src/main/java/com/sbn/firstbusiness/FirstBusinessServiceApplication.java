package com.sbn.firstbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.sbn.firstbusiness.client" })
@EnableCircuitBreaker
@EnableHystrix
public class FirstBusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstBusinessServiceApplication.class, args);
	}
	
	

}
