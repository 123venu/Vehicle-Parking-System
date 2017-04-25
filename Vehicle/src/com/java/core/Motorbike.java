package com.java.core;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Motorbike extends Vehicle implements Serializable {

	private int engineSize;

	//default constructor
	public Motorbike() {
		super();
	}

	//constructor
	public Motorbike(String vehicleId, String vehicleBrand, int engineSize) {
		super(vehicleId, vehicleBrand);
		this.engineSize = engineSize;
		this.type = "Motorbike";
	}

	//getter for engine size
	public int getEngineSize() {
		return engineSize;
	}

	//setter for engine size
	public void setEngineSize(int engineSize) {
		this.engineSize = engineSize;
	}

}
