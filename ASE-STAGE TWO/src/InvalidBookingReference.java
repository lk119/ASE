/**
 * Advanced Software Engineering - Coursework Exception Class to throw an
 * exception if a passenger enters an invalid Booking Reference Number upon
 * check-in
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */
public class InvalidBookingReference extends Exception {
	/**
	 * Creates xception if a passenger enters an invalid Booking Reference Number
	 * upon check-in
	 * 
	 * @param bookingReferenceNum the passenger's booking reference
	 */
	public InvalidBookingReference(String bookingReferenceNum) {
		// Call constructor of parent Exception
		super("Invalid " + (bookingReferenceNum));
	}
}
