package com.java.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Car extends Vehicle implements Serializable {

	private int numberOfDoors;
	private String carColor;

	public Car() {
		super();
	}

	//constructor
	public Car(String vehicleId, String vehicleBrand, int numberOfDoors, String carColor) {
		super(vehicleId, vehicleBrand);
		this.numberOfDoors = numberOfDoors;
		this.carColor = carColor;
		this.type = "Car";
	}

	//getter for doors
	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	//setter for doors
	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	//getter for color
	public String getCarColor() {
		return carColor;
	}

	//setter for color
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

}
