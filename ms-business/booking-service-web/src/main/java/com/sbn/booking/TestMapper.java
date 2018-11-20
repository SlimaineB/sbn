package com.sbn.booking;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMapper {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Car vago = mapper.readValue("{\"colour\":\"blue\",\"name\":\"peugeot\"}", Car.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
