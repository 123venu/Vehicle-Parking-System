package com.java.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Van extends Vehicle implements Serializable {

	private double cargoVolume;

	public Van() {
		super();
	}

	//constructor
	public Van(String vehicleId, String vehicleBrand, double cargoVolume) {
		super(vehicleId, vehicleBrand);
		this.cargoVolume = cargoVolume;
		this.type = "Van";
	}
	
	//getter for cargo volume
	public double getCargoVolume() {
		return cargoVolume;
	}

	//setter for cargo volume
	public void setCargoVolume(double cargoVolume) {
		this.cargoVolume = cargoVolume;
	}

}
