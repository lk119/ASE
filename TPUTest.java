import java.lang.*;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TPUTest {


/**
 * Asserts if the getFullName output is correct
 * @result method gives correct output for both names with middle, and no middle, name.  
 * 
 */
	
	@Test
	public void testgetFullName() {
		 String expected1 = "Robert William Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getFullName();
		 assertEquals(expected1, actual1);
		 
		 String expected2 = "Robert  Stone";
		 Name name2 = new Name("Robert","","Stone");
	     String actual2 = name2.getFullName();
		 assertEquals(expected2, actual2);
		 
	}

	/**
	 * Asserts the getLastName output is correct. Method gives correct output from the full name provided, ie just the last name. 
	 * 
	 * @result   Last name matches the last name of the full name provided. 
	 * 
	 */	

	@Test
	public void testgetLastName() {
		 String expected1 = "Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getLastName();
		 assertEquals(expected1, actual1);
	
	}
	
	/**
	 * Asserts the  output is correct
	 * @result BookingRef remains the same after processing. 
	 * @result FlightCode remains the same after processing. 
	 * @result CheckInStatus remains the same after processing. 
	 * @result Class remains the same after processing. 
	 */	
	
	@Test
	public void testPassenger() {
		
		Passenger testPilot = new Passenger("31251269","QA410",new Name("Bale","Tabina","Olvarado"),true,"Economy");
		
		String actualBookingRef = testPilot.getBookingReferenceNum();
		String actualFlightCode = testPilot.getflightCode();
		boolean actualCheckInStatus = testPilot.getCheckInStatus();
		String actualClass = testPilot.getpClass(); 
		
		String expectedBookingref = "31251269";
		String expectedFlightCode = "QA410";
		boolean expectedCheckInStatus = true; 
		String expectedClass = "Economy";
		
		assertEquals(expectedBookingref,actualBookingRef);
		assertEquals(expectedFlightCode,actualFlightCode);
		assertEquals(expectedCheckInStatus,actualCheckInStatus);
		assertEquals(expectedClass,actualClass);
		
	}

	/**
	 * Asserts the baggage output methods are correct
	 * @result Weight remains the same after processing. 
	 * @result Length Code remains the same after processing. 
	 * @result Width remains the same after processing. 
	 * @result Breadth remains the same after processing. 
	 */	
	
	@Test
	public void testBaggage(){
		Baggage testBag = new Baggage(12.0,22.0,32.0,42.0,new Passenger("31251269","QA410",new Name("Bale","Tabina","Olvarado"),true,"Economy"),new Flight("France","British Airways","BA808",150, null, null));
		
		Double actualWeight = testBag.getWeight();
		Double actualLength = testBag.getHeight();
		Double actualWidth = testBag.getWidth();
		Double actualBreadth = testBag.getBreadth();
		
		Double expectedWeight = 12.0;
		Double expectedLength = 22.0;
		Double expectedWidth = 32.0;
		Double expectedBreadth = 42.0;
		
		assertEquals(expectedWeight, actualWeight); 
		assertEquals(expectedLength, actualLength); 
		assertEquals(expectedWidth, actualWidth); 
		assertEquals(expectedBreadth, actualBreadth); 

	}
	
//	@Test
//	void testExpectedException() {
//		HashSet<Passenger> PassengerSet;
//		PassengerSet = new HashSet<Passenger>();
//		PassengerSet.add(new Passenger("31251269","QA410",new Name("Bale","Tabina","Olvarado"),true,"Economy"));
//		PassengerSet.
//		
//		
//	  Assertions.assertThrows(InvalidBookingReference.class, () -> (PassengerSet.), "Invalid name thus should throw");
//	 
//	}
}
