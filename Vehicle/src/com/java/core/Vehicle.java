package com.java.core;

import com.java.util.EntryDateTime;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Vehicle implements Serializable {

	String vehicleId;
	String vehicleBrand;
	EntryDateTime vehicleEntryTime;
	String type;

	//default constructor
	public Vehicle() {
		super();
	}

	//constructor
	public Vehicle(String vehicleId, String vehicleBrand) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleBrand = vehicleBrand;
	}

	//display method
	public void display() {
		System.out.println("Vehicle Id : " + vehicleId);
		System.out.println("Vehicle type : " + type);
		System.out.println("Vehicle Brand : " + vehicleBrand);
		System.out.println("Vehicle entry time : " + vehicleEntryTime.getDateTime() + "\n");
	}

	//getter for type
	public String getType() {
		return type;
	}

	//setter for type
	public void setType(String type) {
		this.type = type;
	}

	//getter for date and time
	public EntryDateTime getVehicleEntryTime() {
		return vehicleEntryTime;
	}

	//setter for date and time
	public void setVehicleEntryTime(EntryDateTime vehicleEntryTime) {
		this.vehicleEntryTime = vehicleEntryTime;
	}

	//getter for vehicle id
	public String getVehicleId() {
		return vehicleId;
	}

	//setter for vehicle id
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	//getter for vehicle brand
	public String getVehicleBrand() {
		return vehicleBrand;
	}

	//setter for vehicle brand
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

}
