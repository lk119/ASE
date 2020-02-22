
public class NoMatchingLastNameException extends Exception {
	 public NoMatchingLastNameException(String LastName) {
		 // Call constructor of parent Exception 
	        super("Last Name Not Found : " +
	        (LastName)); 
	}
}
