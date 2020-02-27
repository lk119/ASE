/**
 * @author KudaMugara
 *
 */
public class Flight {
	
	private String destination;
	private String carrier;
	private String flightCode;
	private int passangerCarryingCapacity;
	private Passenger passenger;
	private Baggage baggage;

	public Flight(String destination, String carrier, String flightCode, int passangerCarryingCapacity,   
			       Passenger passenger, Baggage baggage) {
		this.destination = destination;
		this.carrier = carrier;
		this.flightCode = flightCode;
		this.passangerCarryingCapacity = passangerCarryingCapacity;
		this.passenger= passenger;
		this.baggage= baggage;
		
		
	}

	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getPassangerCarryingCapacity() {
		return passangerCarryingCapacity;
	}

	public void setPassangerCarryingCapacity(int passangerCarryingCapacity) {
		this.passangerCarryingCapacity = passangerCarryingCapacity;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	 
	public Passenger getPassenger() {
		return passenger;
	}
		
	public void setBaggage(Baggage baggage) {
			this.baggage = baggage;
		}
		 
	public Baggage getBaggage() {
			return baggage;	
		
	}
	@Override
	public String toString() {
		return "Total number of passengers checked in for flight " + carrier + flightCode + " flying to " +
	            destination + " is ";
				
	}
	
	
	//to calculate flight passenger capacity in flight list class 
	//for final report
	public String flightCheckInStatus() {
		String fc;
		return fc = passenger.getCheckInStatus() ;
		    }
	
	
	
	//alternate method to calculate flight passenger capacity in flight list class 
	//for final report (check which one works and keep that)
	public String flightupdatedCheckInStatus() {
		String fuc;
		return fuc = baggage.updatedCheckInStatus() ;
	}
		
	
	//to calculate flight weight capacity in flight list class 
	//for final report
	public double flightBaggageWeight() {
		double fw;
		return fw = baggage.getWeight();
	}
	
	//to calculate flight weight capacity in flight list class 
	//for final report
	public double flightBaggageVolume() {
	double fv;
	return fv = baggage.baggageVolume();
	
	}
		
	//to calculate flight excess baggage fees in flight list class 
	//for final report
	public double flightExcessBaggageFee() {
	double ff;
	return ff = baggage.excessBaggageFee();
		
	}
	
	
	
	}
	

