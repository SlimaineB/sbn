package com.sbn.jooqdata;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sbn.erp.domain.tables.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqDataServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	Product product = Product.PRODUCT;
	
	@Autowired
	private DSLContext dsl;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Before
	// @Ignore
	// @Test
	// @Rollback(false)
	public void testCreateProductCategory() throws Exception {

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
	
	/*@Test
	public void testGetProductByCategory() throws Exception {
		dsl.insertInto(product)
		  .set(product.ID, 4L)
		  .set(product.NAME, "Herbert")
		  .set(product.PRICE, BigDecimal.valueOf(5.55))
		  .execute();

	}*/
/*
	@Test
	public void testGetProductByCategoryNoParameter() throws Exception {
		mockMvc.perform(get("/getProductByCategory/")).andExpect(status().is(404));

	}*/

}
