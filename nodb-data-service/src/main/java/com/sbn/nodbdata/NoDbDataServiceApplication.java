package com.sbn.nodbdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = { "com.sbn.nodbdata.client" })
@EnableCircuitBreaker
@EnableHystrix
public class NoDbDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoDbDataServiceApplication.class, args);
	}
	
	

}
