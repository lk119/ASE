package Model;

/**
 * Advanced Software Engineering - Coursework Class to construct the Flight
 * object.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class Flight {

	private String destination;
	private String carrier;
	private String flightCode;
	private int passangerCarryingCapacity;
	private Passenger passenger;
	private Baggage baggage;

	/**
	 * The constructor for the Flight class
	 * 
	 * @param destination               the flight's destination
	 * @param carrier                   the flight's carrier
	 * @param flightCode                the flight's flight code
	 * @param passangerCarryingCapacity the flight's passenger capacity
	 * @param passenger                 the flight's passengers
	 * @param baggage                   the flight's baggage
	 * 
	 */
	public Flight(String destination, String carrier, String flightCode, int passangerCarryingCapacity,
			Passenger passenger, Baggage baggage) {
		this.destination = destination;
		this.carrier = carrier;
		this.flightCode = flightCode;
		this.passangerCarryingCapacity = passangerCarryingCapacity;
		this.passenger = passenger;
		this.baggage = baggage;
	}

	// getters

	/**
	 * Returns the destination
	 * 
	 * @return destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Returns the carrier
	 * 
	 * @return carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * Returns the flight code
	 * 
	 * @return flight code
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * Returns the passenger carrying capacity
	 * 
	 * @return passenger carrying capacity
	 */
	public int getPassangerCarryingCapacity() {
		return passangerCarryingCapacity;
	}

	/**
	 * Returns the passenger
	 * 
	 * @return passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * Returns the baggage
	 * 
	 * @return bagggage
	 */
	public Baggage getBaggage() {
		return baggage;

	}

	// setters

	/**
	 * Sets the destination
	 *
	 * @param destination the flight destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Sets the carrier
	 *
	 * @param carrier the flight carrier
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * Sets the flight code
	 *
	 * @param flight code
	 */
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	/**
	 * Sets the passenger carrying capacity
	 *
	 * @param passangerCarryingCapacity the passenger carrying capacity
	 */
	public void setPassangerCarryingCapacity(int passangerCarryingCapacity) {
		this.passangerCarryingCapacity = passangerCarryingCapacity;
	}

	/**
	 * Sets the passenger
	 *
	 * @param passenger the passenger
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	/**
	 * Sets the baggage
	 *
	 * @param baggage the baggage
	 */
	public void setBaggage(Baggage baggage) {
		this.baggage = baggage;
	}

	/**
	 * Returns a string value of the Total number of passengers checked in for a
	 * specific flight
	 *
	 * @return string of number of passengers and flight details
	 * 
	 * @Override
	 */
	public String toString() {
		return "Total number of passengers checked in for flight " + carrier + flightCode + " flying to " + destination
				+ " is ";

	}

	/**
	 *  
	 *
	 */
	// to calculate flight passenger capacity in flight list class
	public boolean flightCheckInStatus() {
		return passenger.getCheckInStatus();
	}

}
