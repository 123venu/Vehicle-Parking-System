package com.java.service;

import com.java.core.Vehicle;
import com.java.util.EntryDateTime;

import java.util.ArrayList;

public class WestminsterCarParkManager implements CarParkManager {

	// size of car park slots
	private static int numberOfSlots = 20;
	Vehicle[] slots;

	// stack array list
	private static ArrayList<Vehicle> list = new ArrayList<Vehicle>(numberOfSlots);

	// no of occupied slots in the park
	private int occupiedSlots = 0;

	// constructor
	public WestminsterCarParkManager() {
		// vehicle array for no of car park slots
		slots = new Vehicle[numberOfSlots];
	}

	// method to add a vehicle
	@Override
	public int addVehicle(Vehicle v) {
		int slot = getFreeSlotForParking(v.getType());
		if (slot == -2) {// for a van
			System.out.println("\nSorry, not enough space");
			// get free slots
			return numberOfSlots - occupiedSlots;
		} else if (slot == -1) { // when there are no free slots
			System.out.println("\nPark is Full");
			return 0;
		} else { // to add the vehicle and set the entry time
			v.setVehicleEntryTime(new EntryDateTime());
			slots[slot] = v;
			list.add(v);

			// to display what type was added
			System.out.println("\n" + v.getType() + " is Successfully Added");
			// get free slots
			return numberOfSlots - occupiedSlots;
		}

	}

	// method to remove a vehicle
	@Override
	public Vehicle deleteVehicle(String vehicleId) {
		for (int x = 0; x < slots.length; x++) {
			if (slots[x] != null) {
				if (slots[x].getVehicleId().equals(vehicleId)) {
					System.out.println("\nVehicle Id " + slots[x].getVehicleId() + " removed!");
					System.out.println("Vehicle Type " + slots[x].getType() + " removed!");
					// for van
					if (slots[x].getType().equals("Van")) {
						occupiedSlots = occupiedSlots - 2;
					} else { // for car and bike
						occupiedSlots--;
					}

					// remove from list in the stack
					for (int y = 0; y < list.size(); y++) {
						if (list.get(y).getVehicleId().equals(vehicleId)) {
							list.remove(y);
							break;
						}
					}
					// deleting the vehicle
					Vehicle v = slots[x];
					v = slots[x];
					slots[x] = null;
					return v;
				}
			}
		}
		// when the id plate can't be found
		System.out.println("\nVehicle not found!\n");
		return null;
	}

	// method to allocate slots for the parking vehicles
	public int getFreeSlotForParking(String type) {
		for (int x = 0; x < slots.length; x++) {
			// check for empty space
			if (slots[x] == null) {
				if (x == 0) { // check if it is first iteration
					switch (type) {
					case "Car":
					case "Motorbike":
						occupiedSlots++;
						return x;
					case "Van":
						if (slots[x + 1] == null) {
							occupiedSlots += 2;
							return x;
						} else {
							return -2;
						}
					}
				} else { // if not first iteration
					// check whether previous slot is empty
					if (slots[x - 1] != null) {
						// check whether previous vehicle is a van
						if (!slots[x - 1].getType().equals("Van")) {
							switch (type) {
							case "Car":
							case "Motorbike":
								occupiedSlots++;
								return x;
							case "Van":
								if (x + 1 < numberOfSlots && slots[x + 1] == null) {
									occupiedSlots += 2;
									return x;
								} else {
									return -2; // not enough space
								}
							}
						} // if previous vehicle == van don't do anything
					} else { // if previous slot is empty
						switch (type) {
						case "Car":
						case "Motorbike":
							occupiedSlots++;
							return x;
						case "Van":
							if ((x + 1) < (numberOfSlots - 1)) {
								if (slots[x + 1] == null) {
									occupiedSlots += 2;
									return x;
								}
							} else {
								return -2; // not enough space
							}
						}
					}
				}
			}
		}
		return -1; // park is full/no free slots available
	}

	// method to get the current parked list
	@Override
	public void printCurrentParked() {
		if (!isEmpty()) {
			System.out.println("\nCurrently parked vehicles\n");

			for (int x = list.size() - 1; x >= 0; x--) {
				if (list.get(x) != null) {
					// to get the element in an index in the array list
					Vehicle v = list.get(x);
					// calling the display method in vehicle class
					v.display();
				}
			}
		} else { // no vehicles in the park
			System.out.println("\nNo Vehicles are parked!\n");
		}
	}

	// method to print the statistics
	@Override
	public void printStatistics() {
		if (!isEmpty()) {
			int car = 0;
			int van = 0;
			int motorbike = 0;
			int total = 0;

			// to calculate the percentage of each vehicle type
			for (int x = 0; x < slots.length; x++) {
				if (slots[x] != null) {
					if (slots[x].getType().equals("Car")) {
						car++;
					}
					if (slots[x].getType().equals("Van")) {
						van++;
					}
					if (slots[x].getType().equals("Motorbike")) {
						motorbike++;
					}
					total++;
				}
			}

			// calculating percentages
			double carP = ((double) car / (double) total) * 100.0;
			double vanP = ((double) van / (double) total) * 100.0;
			double motorbikeP = ((double) motorbike / (double) total) * 100.0;

			// to show the longest parked vehicle and the last vehicle parked

			// longest parked is always the first in the array list
			Vehicle longestParked = list.get(0);
			// last parked is always the last in the array list
			Vehicle lastParked = list.get(list.size() - 1);

			System.out.println("\nCurrent parking percentages");
			System.out.println("Cars : " + (int) carP + "%");
			System.out.println("Vans : " + (int) vanP + "%");
			System.out.println("Motorbikes : " + (int) motorbikeP + "%\n");

			System.out.println("Longest parked vehicle");
			longestParked.display();

			System.out.println("Last Parked vehicle");
			lastParked.display();
		} else { // no vehicles in the park
			System.out.println("\nNo Vehicles are parked!\n");
		}
	}

	// method to get the charge for all parked vehicles
	@Override
	public void chargeForAllParked() {
		for (int x = 0; x < slots.length; x++) {
			if (slots[x] != null) {
				System.out.println(slots[x].getVehicleId() + " " + slots[x].getType());
				chargeForPark(slots[x]);
			}
		}
	}

	// method to get the charge for parking
	@Override
	public void chargeForPark(Vehicle vehicle) {
		EntryDateTime currTime = new EntryDateTime();
		long hours = currTime.getEpochTimeHours() - vehicle.getVehicleEntryTime().getEpochTimeHours();
		System.out.print("No of hours parked " + hours);

		int parkingCharge = 0;

		// if less than 3 hours
		if (hours == 0) {
			parkingCharge = 3;
		} else if (hours <= 3) {
			parkingCharge = 3 * (int) hours;
		} else { // if less than 24 hours
			parkingCharge = 9 + ((int) hours - 3);
		}

		System.out.println("\nCharge is €" + parkingCharge +"\n");

	}

	// validation if park is empty
	public boolean isEmpty() {
		int emptyCount = 0;

		for (int x = 0; x < slots.length; x++) {
			if (slots[x] == null) {
				emptyCount++; // if slot is empty bump
			}
		}
		return emptyCount == slots.length;
	}

	// validation if park is full
	public boolean isFull() {
		int fullCount = 0;

		for (int x = 0; x < slots.length; x++) {
			if (slots[x] == null) {
				// does not work if use !=null for some reason
			} else {
				if (slots[x].getType().equals("Van")) {
					fullCount += 2; // if its a van bump 2
				} else {
					fullCount++; // car, bike bump 1
				}
			}
		}
		return fullCount == slots.length;
	}

	// validation for same ID plate is in the park
	public boolean containsID(String idPlate) {
		for (int x = 0; x < slots.length; x++) {
			if (slots[x] != null) {
				if (slots[x].getVehicleId().equals(idPlate)) {
					return true;
				}
			}
		}
		return false;
	}

}
