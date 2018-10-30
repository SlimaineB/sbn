package com.sbn.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@Configuration
@EnableFeignClients(basePackages = { "com.sbn.booking.client" })
public class BookingApplication {

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


	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	
  //  @Bean
    //@Profile("insecure")
   /* public SecurityWebFilterChain securityWebFilterChainPermitAll(ServerHttpSecurity http) {
        return http.authorizeExchange().
        		anyExchange().permitAll()
                   .and().csrf().disable()
                   .build();
    }*/
    
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
