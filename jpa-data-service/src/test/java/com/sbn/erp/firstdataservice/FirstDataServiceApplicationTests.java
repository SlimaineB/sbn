package com.sbn.erp.firstdataservice;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sbn.jpadata.domain.entity.Product;
import com.sbn.jpadata.domain.entity.ProductCategory;
import com.sbn.jpadata.domain.repository.ProductCategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstDataServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Before
	//@Ignore
	//@Test
	//@Rollback(false)
	public void testCreateProductCategory() throws Exception {

		productCategoryRepository.deleteAll();
		
		ProductCategory cat1 = new ProductCategory();
		cat1.setName("Fruit");
		
		cat1.addProduct(new Product("Banane", BigDecimal.valueOf(2.78), new Date()));
		cat1.addProduct(new Product("Pomme", BigDecimal.valueOf(1.03), new Date()));

		cat1 = productCategoryRepository.save(cat1);
		
		
		ProductCategory cat2 = new ProductCategory();
		cat2.setName("Legume");
		
		cat2.addProduct(new Product("Carotte", BigDecimal.valueOf(2.78), new Date()));
		cat2.addProduct(new Product("Radis", BigDecimal.valueOf(1.03), new Date()));

		cat2 = productCategoryRepository.save(cat2);
		assertNotNull(cat1.getId());

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetProductByCategory() throws Exception {
		mockMvc.perform(get("/getProductByCategory/Legume")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$[0]").value("Carotte"));

	}

	@Test
	public void testGetProductByCategoryNoParameter() throws Exception {
		mockMvc.perform(get("/getProductByCategory/")).andExpect(status().is(404));

	}



}
