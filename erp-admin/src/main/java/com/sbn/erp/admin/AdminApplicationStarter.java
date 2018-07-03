package com.sbn.erp.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class AdminApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplicationStarter.class, args);
	}
}
