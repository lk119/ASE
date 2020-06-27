package Model;

import java.util.Random;

/**
 * Advanced Software Engineering Assignment Stage 2 to construct the Passenger
 * object, hold passenger details (check-in status, flight code, booking ref
 * etc.)
 * 
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class Passenger {

	private String bookingReferenceNum;
	private String flightCode;
	private Name passengerName;
	private boolean checkInStatus;
	private String pClass;
	private Baggage baggage;

	/**
	 * Constructor for the Passenger class
	 * 
	 * @param bookingReferenceNum Passenger's booking reference number
	 * @param flightCode          Passenger's flight code
	 * @param passengerName       Passenger's full name
	 * @param checkInStatus       Passenger's check-in status (true or false)
	 * @param pClass              Passenger's class (economy/first/business)
	 * @param b                   Passenger's baggage
	 * @throws InvalidBookingReference
	 */

	public Passenger(String bookingReferenceNum, String flightCode, Name passengerName, boolean checkInStatus,
			String pClass, Baggage b) throws InvalidBookingReference {

		if (!bookingReferenceNum.subSequence(0, 5).equals(flightCode)) {
			throw new InvalidBookingReference("Booking not correct");
		}
		this.bookingReferenceNum = bookingReferenceNum;
		this.flightCode = flightCode;
		this.passengerName = passengerName;
		this.checkInStatus = checkInStatus;
		this.pClass = pClass;
		this.baggage = b;
	}

	/**
	 * Returns the passenger's booking number
	 * 
	 * @return booking reference number
	 */

	public String getBookingReferenceNum() {
		return bookingReferenceNum;
	}

	/**
	 * Sets booking reference number
	 * 
	 * @param bookingRefernceNum
	 */
	public void setBookingReferenceNum(String bookingRefernceNum) {
		this.bookingReferenceNum = bookingRefernceNum;
	}

	/**
	 * Returns the passenger's flight code
	 * 
	 * @return flight code
	 */
	public String getflightCode() {
		return flightCode;
	}

	/**
	 * Sets flight code
	 * 
	 * @param flightCode
	 */
	public void setflightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	/**
	 * Returns the passenger's name
	 * 
	 * @return passenger name
	 */
	public Name getPassengerName() {
		return passengerName;
	}

	/**
	 * Sets passenger name
	 * 
	 * @param passengerName
	 */
	public void setPassengerName(Name passengerName) {
		this.passengerName = passengerName;
	}

	/**
	 * Returns passenger's check-in status
	 * 
	 * @return check-in status
	 */
	public boolean getCheckInStatus() {
		if (checkInStatus == true) {
		}
		return checkInStatus;
	}

	/**
	 * Sets check-in status
	 * 
	 * @param checkInStatus
	 */
	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}

	/**
	 * Returns the passenger's class
	 * 
	 * @return passenger class
	 */
	public String getpClass() {

		return pClass;
	}

	/**
	 * Sets passenger class
	 * 
	 * @param pClass
	 */
	public void setpClass(String pClass) {

		this.pClass = pClass;
	}

	/**
	 * Returns the passenger's full name
	 * 
	 * @return passenger full name
	 */
	public String getFullName() {

		return passengerName.getFullName();
	}

	/**
	 * Returns the passenger's baggage
	 * 
	 * @return passenger baggage
	 */
	public Baggage getBaggage() {
		return baggage;
	}

	/**
	 * Creates a baggage object associated with a Passenger
	 * 
	 * @return baggage
	 */
    //create a simple Bag
	public Baggage createAbag(Passenger p) {
		Random randomNumber = new Random();
		double dimensionHeight = randomNumber.nextInt(30) + 5;
		double dimensionWidth = randomNumber.nextInt(10) + 3;
		double dimensionDepth = randomNumber.nextInt(6) + 1;
		double dimensionWeight = randomNumber.nextInt(60) + 2;

		Baggage b = new Baggage(dimensionWeight, dimensionHeight, dimensionWidth, dimensionDepth, p);
		return b;
	}

}