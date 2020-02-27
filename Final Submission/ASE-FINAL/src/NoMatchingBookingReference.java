/**
 * Advanced Software Engineering - Coursework Exception Class to throw an
 * exception if a passenger enters a Booking Reference that does not match those
 * inputed in the input list upon check-in
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */
public class NoMatchingBookingReference extends Exception {
	/**
	 * Creates exception if a passenger enters a Booking Reference that does not
	 * match those inputed in the input list upon check-in
	 * 
	 * @param bookingRefernceNum the passenger's booking reference
	 */
	public NoMatchingBookingReference(String bookingRefernceNum) {
		// Call constructor of parent Exception
		super("No booking number found matching : " + (bookingRefernceNum));
	}
}
