import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class DriverA3 {
	
	public static String genBrand() {
		String[] carBrands = {"Acura", "Ford", "Jeep", "Fiat", "Kia", "Toyota", "Mercedes-Benz",
				"Mazda","GMC","Honda", "Lexus", "Jaguar"};
		
		Random ran = new Random();
		int n = ran.nextInt(12);
		return carBrands[n];
	}
	
	public static String genDate() {
		Random ran = new Random();
		String y = "";
		String m = "";
		String d = "";
		
		int n = ran.nextInt(71);
		n += 1950;
		y += n;
		
		n = ran.nextInt(12);
		n += 1;
		if (n < 10) {
			m += "0" + n;
		}
		else
			m += n;
		
		n = ran.nextInt(30);
		n += 1;
		if (n < 10) {
			d += "0" + n;
		}
		else
			d += n;
		
		return (y + "/" + m + "/" + d);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Working with Data6.txt with 200 items total");
		
		CVR data = new CVR(12, 501, 1000);
		System.out.println();
		System.out.println();
		
		
		System.out.println();
		
		Scanner inputStream = null;
		PrintWriter outputStream = null ;
		try {
			inputStream = new Scanner(new FileInputStream("Data8.txt"));
			//outputStream = new PrintWriter(new FileOutputStream("Data2.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("Error opening file");
			System.exit(0);
		}
		
		/*
		for(int i = 0; i < 50; i++) {
			outputStream.println(data.generate());
		}
		outputStream.close();
		*/
		
		
		while(inputStream.hasNextLine()) {
			String toAdd = inputStream.nextLine();
			data.add(toAdd, genBrand());
			System.out.println();
		}
		
		inputStream.close();
		
		/*
		data.add(data.generate(), genBrand());
		data.add(data.generate(), genBrand());
		data.add(data.generate(), genBrand());
		data.add(data.generate(), genBrand());
		data.add(data.generate(), genBrand());
		*/
		
		data.add("OUO8SXL5453", genBrand()); 
		System.out.println();
		data.add("OUO8SXL5453", genBrand()); 
		System.out.println();
		
		data.getValues("OUO8SXL5453");
		System.out.println();
		data.nextKey("OUO8SXL5453");
		System.out.println();
		data.prevKey("OUO8SXL5453");
		System.out.println();
		
		for (int i = 0; i < 7; i++) {
			data.addAccident("OUO8SXL5453", genDate());
			System.out.println();
		}
		System.out.println();
		
		data.getValues("OUO8SXL5453");
		System.out.println();
		data.remove("OUO8SXL5457");
		System.out.println();
		data.remove("OUO8SXL5453");
		System.out.println();
		data.getValues("OUO8SXL5453");
		System.out.println();
		System.out.println();
		
		
		
		data.allKeys();
		System.out.println();
		
		
		System.out.println("User input part: Input VIN to work with");
		String vin = in.next();
		System.out.println();
		
		data.getValues(vin);
		System.out.println();
		
		data.nextKey(vin);
		System.out.println();
		
		data.prevKey(vin);
		System.out.println();
		for (int i = 0; i < 3; i++) {
			data.addAccident(vin, genDate());
			System.out.println();
		}
		
		data.addAccident(vin);
		System.out.println();
		

		data.getValues(vin);
		System.out.println();
		data.remove(vin);
		System.out.println();
		data.getValues(vin);
		
		System.out.println("End of demonstration. Thank you for using this CVR");
		
		
		/*
		
		System.out.print("Enter VIN to add: ");
		vin = in.next();
		data.add(vin, genBrand());
		System.out.println();
		
		System.out.print("Enter VIN to find the predecessor: ");
		vin = in.next();
		data.prevKey(vin);
		System.out.println();
		
		System.out.print("Enter VIN to find the successor: ");
		vin = in.next();
		data.nextKey(vin);
		System.out.println();
		
		System.out.print("Enter VIN to add an accident: ");
		vin = in.next();
		data.addAccident(vin);
		System.out.println();
		
		System.out.print("Enter VIN for list of accidents: ");
		vin = in.next();
		data.prevAccids(vin);
		System.out.println();
		
		System.out.print("Enter VIN to remove; ");
		vin = in.next();
		data.remove(vin);
		System.out.println();
		
		System.out.print("Enter VIN to get values for: ");
		vin = in.next();
		data.getValues(vin);
		System.out.println();
			
		*/
		
	}
	
	

}
