package com.sbn.erp.client.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sbn.erp.client.proxy.ProductServiceClient;

@Service
public class ClientServiceDelegate {

	@Value("${services.product.endpoint}")
	protected String productServiceEndpoint;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ProductServiceClient productServiceProxy;

	/**
	 * listProductsByCategoryRestTemplate
	 * @param category
	 * @return
	 */
	public List<String> listProductsByCategoryRestTemplate(String category) {

		System.out.println("Calling listProductsByCategory With RestTemplate Client : " + category);
		
		return restTemplate.exchange(productServiceEndpoint + "/listProductsByCategory/{category}", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() {
				}, category).getBody();
	}

	/**
	 * listProductsByCategoryFeign
	 * @param category
	 * @return
	 */
	public List<String> listProductsByCategoryFeign(String category) {
		
		System.out.println("Calling listProductsByCategory With Feign Client : " + category);
		
		return productServiceProxy.listProductsByCategory(category);
	}

}
