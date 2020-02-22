import java.util.TreeMap;

public class Manager {
	private BaggageList allBags;
	private PassengerSet allPass;
	private FlightList allFlights;

	public Manager() {
		allBags = new BaggageList();
		allPass = new PassengerSet();
		allFlights = new FlightList();
	}

	public void run() {
		// fill the list with some students
		allBags.adding();
		allPass.addingPassanger();
		allFlights.addingFlights();
		System.out.println(allBags.numberOfBags());
		System.out.println(allBags.maxBaggageWeight());
		System.out.println(allBags.maxBaggagevol());
		System.out.println(allPass.getAllPassengers());

		System.out.println(allPass.listByName());

		// flights list method checks
		System.out.println(allFlights.numberOfFlights());
		System.out.println(allFlights.listByCode().toString().replace("[", "").replace("]", ""));

		
		// now lets try to see how we can manipulate the the passanger list and flightList
		
	
		

	}
}
