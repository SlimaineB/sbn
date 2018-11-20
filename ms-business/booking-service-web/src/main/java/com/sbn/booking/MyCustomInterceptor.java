package com.sbn.booking;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyCustomInterceptor implements HandlerInterceptor {

	// unimplemented methods comes here. Define the following method so that it
	// will handle the request before it is passed to the controller.
	 private static final Logger LOGGER = LoggerFactory.getLogger(MyCustomInterceptor.class);

	    private static final int MAX_PAYLOAD_LENGTH = 1000;

	    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Lala");
		String requestBody= IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
  
}
