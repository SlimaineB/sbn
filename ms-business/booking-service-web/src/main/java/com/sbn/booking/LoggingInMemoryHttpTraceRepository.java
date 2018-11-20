package com.sbn.booking;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;




@Repository
public class LoggingInMemoryHttpTraceRepository extends InMemoryHttpTraceRepository {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInMemoryHttpTraceRepository.class);
    public void add(HttpTrace trace) {
        super.add(trace);
        LOGGER.info("Trace:" + ToStringBuilder.reflectionToString(trace));
        LOGGER.info("Request:" + ToStringBuilder.reflectionToString(trace.getRequest()));
        LOGGER.info("Response:" + ToStringBuilder.reflectionToString(trace.getResponse()));
    }
    
    
}