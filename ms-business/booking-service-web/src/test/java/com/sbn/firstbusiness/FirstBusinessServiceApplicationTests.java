package com.sbn.firstbusiness;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.sbn.booking.client.BankingServiceClient;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "feign.hystrix.enabled=true" })
public class FirstBusinessServiceApplicationTests {

	@Autowired
	BankingServiceClient dataServiceClient;

	@ClassRule
	public static WireMockClassRule wiremock = new WireMockClassRule(wireMockConfig().dynamicPort());

	@Test
	public void contextLoads() {
	}

/*	@Test
	public void shouldCallWeatherService() throws Exception {

		// Stubbing WireMock
		stubFor(get(urlMatching("/getProductByName/Pomme"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withBody("{\"id\":null,\"name\":\"Pomme\",\"price\":10.5}")));
	
		// We're asserting if WireMock responded properly
		assertThat(dataServiceClient.getProductByName("Pomme").getName()).isEqualTo("Pomme");
	}*/

	@TestConfiguration
	public static class LocalRibbonClientConfiguration {
		@Bean
		public ServerList<Server> ribbonServerList() {
			return new StaticServerList<>(new Server("localhost", wiremock.port()));
		}
	}
}
