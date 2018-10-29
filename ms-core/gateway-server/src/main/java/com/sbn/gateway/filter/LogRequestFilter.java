package com.sbn.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class LogRequestFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(LogRequestFilter.class);
	
	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return !ctx.containsKey(FilterConstants.FORWARD_TO_KEY) // a filter has already forwarded
				&& !ctx.containsKey(FilterConstants.SERVICE_ID_KEY); // a filter has already determined serviceId
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Requete:" + request.getScheme() + " Adresse:" + request.getRemoteAddr() + " Port:"
				+ request.getRemotePort());
		return null;
	}
}