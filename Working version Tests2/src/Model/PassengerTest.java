package Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;


/**
 * Advanced Software Engineering - Coursework Class used for testing.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */
public class PassengerTest {

	// initialise variables
	Name expectedName;
	Passenger expectedPassenger;
	Baggage expectedBag; 
	
    // Creates a Logger 
    Logger logger 
        = Logger.getLogger( 
            PassengerTest.class.getName()); 
	
	 @Before
	    public void init() throws InvalidBookingReference{
		 
	        logger.info("Startup - Creates Generic Objects for testing");
	        
	        //setup objects needed for testing
				expectedName = new Name("Test", "Example", "McTesterson"); 
				expectedPassenger = new Passenger("QA410500", "QA410", expectedName, false, "Economy", null);
				expectedBag = new Baggage(5, 5, 5, 5, expectedPassenger);
				
			} 
	    
	 
	
	 
//JUnit Testing Notes - Getters and setters not tested, unless the property is changed or checked within the getter/setter,
// Roy Osherove in his famous book 'The Art Of Unit Testing' says:
//	 		Properties (getters/setters in Java) are good examples of code that usually doesn’t contain any logic, 
//			and doesn’t require testing. But watch out: once you add any check inside the property, 
//			you’ll want to make sure that logic is being tested.

	 
		/**
		 * Asserts the output is correct
		 * 
		 * @throws InvalidBookingReference
		 * @result BookingRef remains the same after processing.
		 * @result FlightCode remains the same after processing.
		 * @result CheckInStatus remains the same after processing.
		 * @result Class remains the same after processing.
		 */
	@Test
	public void testPassenger() throws InvalidBookingReference {
        logger.info("Testing Passenger Object Creation Input");
		

		String actualBookingRef = expectedPassenger.getBookingReferenceNum();
		String actualFlightCode = expectedPassenger.getflightCode();
		boolean actualCheckInStatus = expectedPassenger.getCheckInStatus();
		String actualClass = expectedPassenger.getpClass();

		String expectedBookingref = "QA410500";
		String expectedFlightCode = "QA410";
		boolean expectedCheckInStatus = false;
		String expectedClass = "Economy";

		assertEquals(expectedBookingref, actualBookingRef);
		assertEquals(expectedFlightCode, actualFlightCode);
		assertEquals(expectedCheckInStatus, actualCheckInStatus);
		assertEquals(expectedClass, actualClass);
	}

	/**
	 * Asserts the getLastName output is correct. Method gives correct output from
	 * the full name provided, ie just the last name.
	 * 
	 * @result Last name matches the last name of the full name provided.
	 * 
	 */
	@Test
	public void testGetLastName() {
		
        logger.info("Testing get for passenger last name is retrieved correctly");
		
		String expected1 = "McTesterson";
		String actual1 = expectedPassenger.getLastName();
		assertEquals(expected1, actual1);
	}

	/**
	 * Asserts if the getFullName output is correct
	 * 
	 * @result method gives correct output for both names with middle, and no
	 *         middle, name.
	 * 
	 */
	@Test
	public void testGetFullName() {
		
		logger.info("Testing get for passenger full name is retrieved correctly");
		
		String expected1 = "Test Example McTesterson";
		String actual1 = expectedPassenger.getFullName();
		assertEquals(expected1, actual1);
	}


	/**
	 * Asserts that the baggage object is created correctly
	 *  
	 *  @result  Height, Depth, width and weight of the bag are created within the correct range and are retrievable. 
	 */
	
	@Test
	public void testCreateAbag() {
		
		logger.info("Testing Baggage Dimensions/Weight are generated correct and within bounds");
		
		Baggage test = expectedPassenger.createAbag(expectedPassenger);
		Double actualDepth = test.getBreadth();
		Double actualWidth = test.getWidth();
		Double actualHeight = test.getHeight();
		Double actualWeight = test.getWeight();
		Passenger bagPassenger = test.getPassenger();
		
		assertSame(expectedPassenger, bagPassenger);
		assertTrue(actualDepth > 0 && actualDepth < 8);
		assertTrue(actualWidth > 0 && actualWidth < 14);
		assertTrue(actualHeight > 0 && actualHeight < 36);
		assertTrue(actualWeight > 0 && actualWeight < 63);
		
		
		
	}

	/**
	 * Asserts the toString Method produces the correct string from the passenger object called upon
	 * 
	 * @result A String of the Name and Flight code of the passenger called upon
	 */
	@Test
	public void testToString() {
		
		logger.info("Testing the ToString method output is correct");
		
		String actualToString = expectedPassenger.toString();
		String expectedToString = "McTesterson" + "\t" + "QA410" + "\t";
		
		assertEquals(expectedToString, actualToString); 
		
		
	}

}
