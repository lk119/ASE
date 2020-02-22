import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
		allPass.createAMap();

		System.out.println(allBags.numberOfBags());
		System.out.println(allBags.maxBaggageWeight());
		System.out.println(allBags.maxBaggagevol());

		// Passangers checks
		System.out.println(allPass.getAllPassengers());
		System.out.println(allPass.totalNumberOfPassengersCheckedIn());

		System.out.println(allPass.listByName());

		// flights list method checks
		System.out.println(allFlights.numberOfFlights());
		System.out.println(allFlights.listByCode().toString().replace("[", "").replace("]", ""));

		// now lets try to see how we can manipulate the the passenger list and
		// flightList

		System.out.println(allPass.fulldetails());
		System.out.println(	allPass.createAMap());
		System.out.println(allFlights.createAMap());
	System.out.println(allFlights.createAMap().keySet().toString().replace("]", "").replace("[", "").substring(0, 5));
		
	//passenger in thier flights
	HashMap<String,String> PassFlights= allPass.createAMap();	
	//flights and thier airline
	HashMap<String,String> FlightsAndLine= allFlights.createAMap();	

	String KING=allFlights.createAMap().keySet().toString().replace("]", "").replace("[", "").substring(0, 5);
	for(Map.Entry<String, String> fentry:FlightsAndLine.entrySet()) {
	
		
		for (Map.Entry<String, String> entry : PassFlights.entrySet() )
	 {
			if(fentry.getKey().equalsIgnoreCase(entry.getValue())) {
			  
				System.out.println(entry.getKey() + " is in flight "+ fentry.getKey());
				
			}
			
		/*
				 * System.out.println(entry.getKey() + "is in flight " + entry.getValue());
				 */
	}
		int count = Collections.frequency(PassFlights.values(), fentry.getKey());
        System.out.println(fentry.getKey() +"has:"+ count +" passengers already checkedIn");
   }
	}
}