package com.sbn.referential;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = { "com.sbn.referential.nodbdata.client" })
public class ReferentialServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReferentialServiceApplication.class, args);
	}
	
	

}
