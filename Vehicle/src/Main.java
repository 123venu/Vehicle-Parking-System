import com.java.core.Car;
import com.java.core.Motorbike;
import com.java.core.Van;
import com.java.core.Vehicle;
import com.java.service.WestminsterCarParkManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Main implements Serializable {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] a) throws IOException {

		WestminsterCarParkManager parkingSys = new WestminsterCarParkManager();

		// load array list from file
		ArrayList<Vehicle> list1 = deserializeList();
		int freeSlots = 20;

		String selection = "Y";

		// get input procedure
		while (!selection.equals("X")) {
			System.out.println("~ Welcome to Westminster Car Park ~" + "\nPress desired key to continue "
					+ "\n(A)dd Vehicle" + "\n(D)elete vehicle" + "\n(P)rint current list of vehicles"
					+ "\n(S)tatistics of vehicles" + "\n(L)ist parkings on a selected day" 
					+ "\n(C)harge for all parked" + "\n(X) to Exit");

			System.out.print("Enter a procedure : ");
			selection = sc.next().toUpperCase();
			Vehicle v = null;

			try {
				// main selection to get a procedure
				switch (selection) {
				// add vehicles
				case "A":
					// check if park is full
					if (!parkingSys.isFull()) {
						System.out.print("Enter vehicle ID : ");
						String s = sc.next();
						String idPlate = validateInput(s);

						// check whether ID is already there
						if (parkingSys.containsID(idPlate)) {
							System.out.print("\nVehicle ID already exists\n");
							break;
						}

						System.out.print("Enter vehicle brand : ");
						String brand = validateInput(sc.next());

						selection = "1";
						boolean isAdded = false;

						while (!isAdded) {
							System.out.print("Enter vehicle type (1.Car, 2.Van, 3.Motorbike): ");
							selection = sc.next();

							try {
								// sub section to select the type of vehicle
								switch (selection) {
								case "1":
									System.out.print("Add Door Count: ");
									int numberOfDoors = sc.nextInt();
									System.out.print("Add Color: ");
									String color = sc.next();
									v = new Car(idPlate, brand, numberOfDoors, color);
									isAdded = true;
									break;
								case "2":
									System.out.print("Add Cargo Volume: ");
									double cargoVolume = sc.nextDouble();
									v = new Van(idPlate, brand, cargoVolume);
									isAdded = true;
									break;
								case "3":
									System.out.print("Add Engine Size: ");
									int engineSize = sc.nextInt();
									v = new Motorbike(idPlate, brand, engineSize);
									isAdded = true;
									break;
								default:
									System.out.println("\nInvalid Vehicle Type! Re-enter Type\n");
									break;
								}
							} catch (Exception e) {
								System.out.println("Wrong Input! Re-enter Data");
								sc.nextLine();
							}
						}

						int temp = freeSlots;

						// to get free slots
						freeSlots = parkingSys.addVehicle(v);

						// add vehicle to list if successfully added to park
						if (temp != freeSlots) {
							// add to array for storing
							list1.add(v);
						}

						if (freeSlots >= 0) {
							// display number of free slots;
							System.out.println("Available slots " + freeSlots + "\n");
						}
					} else {
						System.out.println("\nPark is full!\n"); //full
					}
					break;
				// remove vehicles
				case "D":
					if (!parkingSys.isEmpty()) {
						System.out.print("Enter Plate number : ");
						String idPlate1 = sc.next();
						v = parkingSys.deleteVehicle(idPlate1);
						if (v != null) {
							parkingSys.chargeForPark(v);
						}
					} else {
						System.out.println("\nPark is empty!\n"); //empty
					}
					break;
				// print current parked vehicles
				case "P":
					parkingSys.printCurrentParked();
					break;
				// print the vehicle statistics
				case "S":
					parkingSys.printStatistics();
					break;
				// list of vehicles on a selected date
				case "L":
					System.out.print("Enter date and year (yyyy-mm-dd) : ");
					String date = sc.next();
					printVehicleList(list1, date);
					break;
				// charge for all the vehicles parked
				case "C":
					if (!parkingSys.isEmpty()) {
						parkingSys.chargeForAllParked();
					} else {
						System.out.println("\nPark is empty!\n");
					}
					break;
				// exit
				case "X":
					// save array list into a file
					serializeList(list1);
					System.out.print("Good bye!");
					break;
				default:
					System.out.println("\nInvalid Procedure! Try Again \n");
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Input!");
				sc.nextLine();
			}
		}
		sc.close();
	}

	// method list of vehicles on a specific date
	private static void printVehicleList(ArrayList<Vehicle> list1, String filterDate) {
		if (!list1.isEmpty()) {
			boolean isPrinted = false;
			for (int x = 0; x < list1.size(); x++) {
				if (list1.get(x) != null) {
					if (list1.get(x).getVehicleEntryTime().getDate().equals(filterDate)) {
						isPrinted = true;
						list1.get(x).display();
					}
				}
			}

			if (!isPrinted) {
				System.out.println("\nNo parkings on " + filterDate + "\n");
			}

		} else {
			System.out.println("\nNo parkings on " + filterDate + "\n");
		}
	}

	// validation for string input
	public static String validateInput(String value) {
		boolean contain = true;
		boolean isValid = true;

		while (contain) { // to continue the loop

			char[] ch = value.toCharArray(); // put characters to a string array
			for (char c : ch) { // enhanced loop
				isValid = (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || (c == '-')
						|| ((c >= '0') && (c <= '9')));
				// if input contains other characters
				if (!isValid) {
					System.out.print("Input contains other characters. Please re-enter : ");
					value = sc.next();
					break;
				} else {
					contain = false;
				}
			}
		}
		return value;
	}

	// write object to file
	public static void serializeList(ArrayList<Vehicle> list) throws IOException {
		// create a file to write
		FileOutputStream file = new FileOutputStream("data.txt");
		// create a object output path to file
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(list); // write object to file
		oos.flush();
		oos.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Vehicle> deserializeList() throws IOException {
		try {
			// create a file to get input
			FileInputStream file = new FileInputStream("data.txt");
			// create object input path
			ObjectInputStream ois = new ObjectInputStream(file);

			// array list to get input
			ArrayList<Vehicle> list = new ArrayList<Vehicle>();

			// try to read from file to list, might throw exception if empty
			list = (ArrayList<Vehicle>) ois.readObject();
			ois.close();
			return list; // return the loaded list
		} catch (Exception e) {
			// file is empty if exception is thrown
		}
		return new ArrayList<Vehicle>(); // return empty list if file is empty
	}

}