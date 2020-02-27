
public class InvalidBookingReference extends Exception {
	 
	public InvalidBookingReference(String bookingReferenceNum) 
    { 
        // Call constructor of parent Exception 
        super("Invalid " + (bookingReferenceNum)); 
        
    }

}
