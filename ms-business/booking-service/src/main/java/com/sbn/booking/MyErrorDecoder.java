package com.sbn.booking;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() >= 400 && response.status() <= 499) {
			try {
				byte[] bodyData = Util.toByteArray(response.body().asInputStream());
				String responseBody = new String(bodyData);
				ObjectMapper mapper = new ObjectMapper();
				Car vago = mapper.readValue(responseBody, Car.class);
				return new MyBadRequestException(responseBody);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return defaultErrorDecoder.decode(methodKey, response);
	}
}
