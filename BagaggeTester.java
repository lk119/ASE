package TP;

import java.util.Scanner;

import TP.Baggage;

/**
 * @author crs8
 *
 */
public class BagaggeTester {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// assign like a constant
		Baggage FirstData = Baggage.FIRST;
		// uses inbuilt toString
		System.out.println(FirstData);
		System.out.println("end");

		// assign like a constant
		Baggage BusinessData = Baggage.BUSINESS;
		// uses inbuilt toString
		System.out.println(BusinessData);
		System.out.println("end");

		// assign like a constant
		Baggage EconomyData = Baggage.ECONOMY;
		// uses inbuilt toString
		System.out.println(EconomyData);
		System.out.println("end");

		// assign like a constant
		Baggage FlightData = Baggage.FLIGHT;
		// uses inbuilt toString
		System.out.println(FlightData);
		System.out.println("end");

		for (Baggage b : Baggage.values()) {
			System.out.println(b);
		}
		// prints class followed by weight (kg) and volume (ltr) values
		
		System.out.println(FlightData + " max volume is " + FlightData.getvolume() + "l ");
		System.out.println(FlightData + " max weight is " + FlightData.getweight() + "kg ");

		System.out.println(EconomyData + " max volume is " + EconomyData.getvolume() + "l ");
		System.out.println(EconomyData + " max weight is " + EconomyData.getweight() + "kg ");

		System.out.println(BusinessData + " max volume is " + BusinessData.getvolume() + "l ");
		System.out.println(BusinessData + " max weight is " + BusinessData.getweight() + "kg ");

		System.out.println(FirstData + " max volume is " + FirstData.getvolume() + "l ");
		System.out.println(FirstData + " max weight is " + FirstData.getweight() + "kg ");
		
		// scanner to output values of passenger class entered
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Class : ");
		String input = scanner.nextLine();
		String input1 = input.trim().toUpperCase().replaceAll("\\s", "");
		Baggage newBaggage = Baggage.valueOf(input1);
		System.out.println(newBaggage + "\n" + " Weight: " + newBaggage.getweight() + "kg " + "\n" + " Volume: "
				+ newBaggage.getvolume() + "ltr ");

		for (Baggage b : Baggage.values()) {
			System.out.println("class: " + b + " Weight: " + b.getweight() + " Volume:  " + b.getvolume());
		}
		scanner.close();
	}
}
