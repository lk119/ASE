package Model;

/**
 * Advanced Software Engineering - Coursework Class to construct the Passenger
 * object.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */
public class Passenger   {

	private String bookingReferenceNum;
	private String flightCode;
	private Name passengerName;
	private boolean checkInStatus;
	private String pClass;
	private Flight passengerFlight;
	private Baggage passengerBaggage;
	
	/**
	 * Constructor for the Passenger class
	 *
	 * @param bookingReferenceNum booking reference number
	 * @param flightCode          flight code
	 * @param passengerName       name of the passenger
	 * @param checkInStatus       passenger checkIn status
	 * @param pClass              travel class of the passenger
	 * @param passengerFlight     flight of the passenger
	 * @param passengerBaggage    baggage of the passenger
	 * @param PassengersInQueue    passengers in queue shared object
	 *
	 * 
	 */
	public Passenger(String bookingReferenceNum, String flightCode, Name passengerName, boolean checkInStatus,
			String pClass, Flight passengerFlight, Baggage passengerBaggage) throws InvalidBookingReference {

		if (!bookingReferenceNum.subSequence(0, 5).equals(flightCode)) {
			throw new InvalidBookingReference("Booking not correct");
		}
		this.bookingReferenceNum = bookingReferenceNum;
		this.flightCode = flightCode;
		this.passengerName = passengerName;
		this.checkInStatus = checkInStatus;
		this.pClass = pClass;
		this.passengerFlight = passengerFlight;
		this.passengerBaggage = passengerBaggage;
		
	}

	// getters

	
	/**
	 * Returns the booking reference
	 * 
	 * @return booking reference
	 */
	public String getBookingReferenceNum() {
		return bookingReferenceNum;
	}

	/**
	 * Returns the flight Code
	 * 
	 * @return flight Code
	 */
	public String getflightCode() {
		return flightCode;
	}

	/**
	 * Returns the check in status
	 * 
	 * @return check in status
	 */
	public boolean getCheckInStatus() {
		if (checkInStatus == true) {
		}
		return checkInStatus;
	}

	/**
	 * Returns the passenger designated class
	 * 
	 * @return passenger class
	 */
	public String getpClass() {
		return pClass;
	}

	/**
	 * Returns the Baggage object
	 * 
	 * @return passengerBaggage
	 */
	public Baggage getBaggage() {
		return passengerBaggage;
	}

	/**
	 * Returns the Flight object
	 * 
	 * @return passengerFlight
	 */
	public Flight getFlight() {
		return passengerFlight;
	}

	/**
	 * Returns the passenger last name
	 * 
	 * @return passenger last name
	 */
	public String getLastName() {
		return passengerName.getLastName();
	}

	/**
	 * Returns passenger full name
	 * 
	 * @return passenger full name
	 */
	public String getFullName() {
		return passengerName.getFullName();
	}

	// setters

	/**
	 * Sets the passenger check-in status
	 *
	 * @param checkInStatus the check in status of the passenger
	 */
	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}

	

	

	

}
