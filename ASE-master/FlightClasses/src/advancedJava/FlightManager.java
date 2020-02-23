package advancedJava;

/*
 * This is just to test the functionality of the classes
 */
public class FlightManager {
	private FlightList allFlights;

	public FlightManager() {
		allFlights = new FlightList();
	}

	public void run() {
		Flight f1 = new Flight("Botswana", "BA", "BA2091", 200);
		Flight f2 = new Flight("Hungary", "Qatar", "QA2091", 230);
		Flight f3 = new Flight("Hungary", "Qatar", "QA2091", 230);

		allFlights.addFlight(f1);
		allFlights.addFlight(f2);
		allFlights.addFlight(f3);

		String Car = f1.getCarrier();

		System.out.println("The carrrier is " + Car);
		allFlights.getAllFlights();
		System.out.println(allFlights.getAllFlights());
		System.out.println("The number of flights currently present in the list is :" + allFlights.numberOfFlights());
	}
}
