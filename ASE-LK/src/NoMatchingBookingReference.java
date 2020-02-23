
public class NoMatchingBookingReference extends Exception {
	 public NoMatchingBookingReference(String bookingRefernceNum) {
		 // Call constructor of parent Exception 
	        super("No booking number found matching : " +
	        (bookingRefernceNum)); 
	}

	
}



   
