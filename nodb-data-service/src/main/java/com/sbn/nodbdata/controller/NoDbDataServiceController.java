package com.sbn.nodbdata.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoDbDataServiceController {

	private static Logger log = LoggerFactory.getLogger(NoDbDataServiceController.class);

	@RequestMapping(value = "/getProductByCategory/{category}", method = RequestMethod.GET)
	public List<String> getProductByCategoryNoDb(@PathVariable String category) {

		log.info("Calling getProductByCategory [" + category + "]");
		
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Arrays.asList("Tomate", "Courgette");

	}

	
	@GetMapping("/tomate")
	public String getTomateMessage() throws InterruptedException {
		
		//Thread.sleep(100);
		// Complex Method
		return "Tomate";
	}
}
