import java.util.TreeMap;

public class PassangersInFlightsList {
	private TreeMap<String, Flight> passFlights;
	private PassengerSet allPass;
	private FlightList allFlights;

	public PassangersInFlightsList() {
		passFlights = new TreeMap<String, Flight>();
	}

	public void addPassangerToFlight(Passenger p, Flight f) {
		String passangerFlight = p.getflightCode();
		String numberOfCheckedins= allPass.totalNumberOfPassengersCheckedIn();
		int    num=Integer.parseInt(numberOfCheckedins);
       for(int i=0; i< num;i++) {
		if (passangerFlight.equalsIgnoreCase(f.getFlightCode())) {
			passFlights.put(p.getFullName(), f);

		}
	}
	}

}