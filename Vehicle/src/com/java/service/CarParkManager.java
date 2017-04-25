package com.java.service;

import com.java.core.Vehicle;

public interface CarParkManager {
	
	//method to add vehicles
	int addVehicle(Vehicle v);
	
	//method to delete vehicles
	Vehicle deleteVehicle(String vehicleId);
	
	//method to print current vehicles
	void printCurrentParked();
	
	//method to print statistics
	void printStatistics();
	
	//method to get charge for all parked vehicles
	void chargeForAllParked();
	
	//method to get charge for parking
	void chargeForPark(Vehicle vehicle);
	
}
