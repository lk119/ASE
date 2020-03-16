
public class NameAndRefNumMismatch extends Exception {
	 public NameAndRefNumMismatch(String bookingRefernceNum, String bookingName) {
		 // Call constructor of parent Exception 
	        super("The name does not match the refnum : " +
	        (bookingRefernceNum) + (bookingName)); 
	}

	
}
