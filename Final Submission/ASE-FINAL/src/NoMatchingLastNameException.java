/**
 * Advanced Software Engineering - Coursework Exception Class to throw an
 * exception if a passenger enters a Last Name that does not match those
 * inputed in the input list upon check-in
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 *
 */
public class NoMatchingLastNameException extends Exception {
	/**
	 * Creates exception if a passenger enters a Last Name that does not match those
	 * inputed in the input list upon check-in
	 * 
	 * @param LastName the passenger's last name
	 */
	public NoMatchingLastNameException(String LastName) {
		// Call constructor of parent Exception
		super("No last name found matching " + (LastName));
	}
}