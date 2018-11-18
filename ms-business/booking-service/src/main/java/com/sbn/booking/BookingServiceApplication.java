package com.sbn.booking;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

import feign.codec.Decoder;

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
    public MyErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }

	@Bean
	public Decoder notFoundAwareDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
		return new NotFoundAwareDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters)));
	}

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

}
