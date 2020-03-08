
//import toolkits
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Advanced Software Engineering - Coursework Reads the input files of existing
 * of passenger bookings and flight details Runs the GUI for the electronic
 * check-in system
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class TravelPigeonManager {

	/**
	 * Reads the input files and creates the first instance of the GUI
	 */
	public void run() {

		// read passenger input file
		PassengerSet p = new PassengerSet();
		p.readFile("passengers");
		p.createAMap();
		System.out.println(p.getAllPassengers());
		BaggageList b = new BaggageList();

		// read flight input file
		FlightList f = new FlightList();
		f.readFile("flight");

		// create new instance of TPWelcome
		TPWelcome TPWelcome = new TPWelcome(p, f, b);

		// open the scanner
		Scanner sc = new Scanner(System.in);
		// close the scanner
		sc.close();

		HashMap<String, Passenger> passae = p.createAMap2();
		HashMap<String, String> planes = f.createAMap3();
		System.out.println(passae.entrySet());

		// auto updates of checkIn
		for (Map.Entry<String, String> fentry : planes.entrySet()) {
			int count = 0;

			for (Map.Entry<String, Passenger> entry : passae.entrySet()) {
				if (fentry.getKey().equalsIgnoreCase(entry.getValue().getflightCode())) {
					boolean sta = entry.getValue().getCheckInStatus();
					String res = String.valueOf(sta);
					if (res.equalsIgnoreCase("true")) {
						count++;
						// System.out.println(count);
					}
				}
			}
			System.out.println(fentry.getKey() + "has:" + count + " passengers already checkedIn");
		}
	}
}