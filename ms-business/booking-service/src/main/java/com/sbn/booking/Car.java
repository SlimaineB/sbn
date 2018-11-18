package com.sbn.booking;

public class Car {

	
	private String colour;
	
	private String name;

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car(String colour, String name) {
		super();
		this.colour = colour;
		this.name = name;
	}

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
