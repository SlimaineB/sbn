package com.sbn.booking;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
@EnableFeignClients(basePackages = { "com.sbn.booking.client" })
@EnableAsync
public class BookingServiceApplication {

	/*
	 * @Bean
	 * 
	 * @LoadBalanced public WebClient.Builder loadBalancedWebClientBuilder() {
	 * return WebClient.builder(); }
	 * 
	 */

	@Bean
	WebClient webClient(LoadBalancerClient loadBalancerClient) {
		return WebClient.builder()
				// .filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient))
				.build();
	}

	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

	
    @Bean
    //@Profile("insecure")
    public SecurityWebFilterChain securityWebFilterChainPermitAll(ServerHttpSecurity http) {
        return http.authorizeExchange().
        		anyExchange().permitAll()
                   .and().csrf().disable()
                   .build();
    }
    
   /* @Bean
    //@Profile("secure")
    public SecurityWebFilterChain securityWebFilterChainSecured(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers( "/assets/**").permitAll()
                .pathMatchers( "/login").permitAll()
                .pathMatchers( "/actuator/**").permitAll().
        		anyExchange().authenticated()//
                   .and().csrf().disable()//
                   .build();
    }*/
}
