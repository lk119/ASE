
public class NoMatchingLastNameException extends Exception {

	    public NoMatchingLastNameException(String LastName) 
	    { 
	        // Call constructor of parent Exception 
	        super("No last name found matching " + (LastName)); 
	        
	    } 
	
}