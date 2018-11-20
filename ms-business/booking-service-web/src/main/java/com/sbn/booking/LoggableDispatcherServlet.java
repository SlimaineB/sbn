package com.sbn.booking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;

public class LoggableDispatcherServlet extends DispatcherServlet {
	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(request instanceof ContentCachingRequestWrapper)) {
			System.out.println("Toto");
			request = new ContentCachingRequestWrapper(request);
		}
		super.doDispatch(request, response);
	}
}