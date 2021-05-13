import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Vehicle {
	private String vin;
	public ArraySequence<String> accidents = new ArraySequence<String>();
	private String brand;

	Scanner in = new Scanner(System.in);

	public Vehicle(String vin, String brand) {
		this.vin = vin;
		this.brand = brand;
	}

	public String getVin() {
		return this.vin;
	}
	
	
	public void addAccident(String date) {
		if (!accidents.isEmpty()) {
			Position<String> temp = accidents.first();
			boolean placed = false;
			for (int i = 0; i <= accidents.indexOf(accidents.last()); i++) {
				if (date.compareTo(temp.element()) >= 0) {
					accidents.addBefore(temp, date);
					placed = true;
					break;
				} 
				temp = accidents.next(temp);
			}
			if(!placed) {
				accidents.addLast(date);
			}
		} 
		else {
			accidents.addLast(date);
		}
		System.out.println("Added new accident for VIN " + vin + ": Date: " + date);

	}

	public void addAccident() {
		System.out.println("Do you want to add an accident to " + vin + " : Y/N");
		String answer = in.next();
		if (answer.equalsIgnoreCase("Y")) {
			System.out.println("Enter new accident date in format YYYY/MM/DD");
			String toAdd = in.next();
			if (!accidents.isEmpty()) {
				Position<String> temp = accidents.first();
				boolean placed = false;
				for (int i = 0; i <= accidents.indexOf(accidents.last()); i++) {
					if (toAdd.compareTo(temp.element()) >= 0) {
						accidents.addBefore(temp, toAdd);
						placed = true;
						break;
					} 
					temp = accidents.next(temp);
				}
				if(!placed) {
					accidents.addLast(toAdd);
				}
			} 
			else {
				accidents.addLast(toAdd);
			}
			System.out.println("Added new accident for VIN " + vin + ": Date: " + toAdd);


		} else if (answer.equalsIgnoreCase("N")) {
			System.out.println("Ok, not adding new accidents.");
		} else {
			System.out.println("Invalid response, not adding new accidents.");
		}
	}

	public ArraySequence<String> getAccidents() {
		System.out.println(printAccidents());
		return accidents;
	}

	public String printAccidents() {
		String toReturn = "List of accidents for VIN " + this.vin + ": ";
		if (!accidents.isEmpty()) {
			for (int i = 0; i <= accidents.indexOf(accidents.last()); i++) {
				toReturn += "[" + accidents.get(i) + "] ";
			}
		} else {
			toReturn += " no accidents... for now";
		}
		return toReturn;
	}

	public String toString() {
		return ("[VIN: " + this.vin + "], [brand: " + this.brand + "] " + printAccidents());
	}

	public boolean equals(Object otherObject) {
		if (otherObject == null)
			return false;
		else if (getClass() != otherObject.getClass())
			return false;
		else {
			Vehicle otherV = (Vehicle) otherObject;
			return (vin.equals(otherV.vin) && brand.equals(otherV.brand));
		}

	}

}
