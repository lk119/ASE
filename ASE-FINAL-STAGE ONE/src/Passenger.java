/**
 * Advanced Software Engineering - Coursework Class to construct the Passenger
 * object.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */
public class Passenger {

	private String bookingReferenceNum;
	private String flightCode;
	private Name passengerName;
	private boolean checkInStatus;
	private String pClass;

	/**
	 * Constructor for the Passenger class
	 *
	 * @param bookingReferenceNum Weight of the baggage
	 * @param flightCode          Height of the baggage
	 * @param passengerName       Width of the baggage
	 * @param checkInStatus       Breadth of the baggage
	 * @param pClass              The passenger associated with the baggage
	 *
	 * @exception InvalidBookingReference if booking reference is not found
	 *
	 */
	public Passenger(String bookingReferenceNum, String flightCode, Name passengerName, boolean checkInStatus,
			String pClass) throws InvalidBookingReference {

		if (!bookingReferenceNum.subSequence(0, 5).equals(flightCode)) {
			throw new InvalidBookingReference("Booking not correct");
		}
		this.bookingReferenceNum = bookingReferenceNum;
		this.flightCode = flightCode;
		this.passengerName = passengerName;
		this.checkInStatus = checkInStatus;
		this.pClass = pClass;
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

	//setters
	
	/**
	 * Sets the passenger check-in status
	 *
	 * @param checkInStatus the check in status of the passenger
	 */
	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}
}
