/**
 * Advanced Software Engineering Assignment Stage 1 Class to define the varying
 * instances of Names for the passengers
 *
 * @author Cameron Scott, Kuda Mugara, Lynsey Kirk, Rachana Patel, Robert Stone
 */

public class Name {

	/**
	 * Private instance variables accessible within class
	 */

	// Assign parameters
	private String FirstName;
	private String MiddleName;
	private String LastName;

	// Create the constructor
	/**
	 * @param fName the passenger's first name
	 * @param mName the passenger's middle name
	 * @param lName the passenger's last name
	 */
	public Name(String fName, String lName) {
		// 2 name constructor
		FirstName = fName;
		MiddleName = "";
		LastName = lName;
	}

	public Name(String fName, String mName, String lName) {

		this.FirstName = fName;
		this.MiddleName = mName;
		this.LastName = lName;
	}

	/**
	 * Creates the passenger's full name from the individual first, middle and last
	 * names and takes into consideration if a passenger doesn't have a middle name
	 *
	 * @param fullName the passenger's full name
	 */
	public Name(String fullName) {
		int spacePos1 = fullName.indexOf(' ');
		FirstName = fullName.substring(0, spacePos1);
		int spacePos2 = fullName.lastIndexOf(' ');
		if (spacePos1 == spacePos2) {
			MiddleName = "";
		} else {
			MiddleName = fullName.substring(spacePos1 + 1, spacePos2);
		}
		LastName = fullName.substring(spacePos2 + 1);
	}

	/**
	 * Returns the passenger's full name
	 *
	 * @return the passenger's full name
	 */
	public String getFullName() {
		return FirstName + " " + MiddleName + " " + LastName;
	}

	/**
	 * Return the passenger's first name
	 *
	 * @return the passenger's first name
	 */
	public String getLastName() {
		return LastName;
	}

}