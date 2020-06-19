package Model;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

public class BaggageTest {

	// initialise variables
	Name expectedName;
	Passenger expectedPassenger;
	Baggage expectedBag, expectedBag2; 
	
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
				expectedBag2 = new Baggage(100,100,100,100, expectedPassenger);
			} 
	
	@Test
	public void testBaggage() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPassenger() {
		
		logger.info("Testing correct passenger is retrieved");
		
		Passenger actualPassenger = expectedBag.getPassenger();
		
		assertSame(expectedPassenger, actualPassenger);
	}

	@Test
	public void testBaggageVolume() {
		
		logger.info("Testing correct volume is calculated");
		
		Double actualVolume = expectedBag.baggageVolume();
		
		Double expectedVolume = 125.0;
		
		assertEquals(expectedVolume, actualVolume); 
	}

	@Test
	public void testExcessBaggageWeight() {
		
		
		// when fees should be 0
		logger.info("Testing correct excess baggage is calculated");
		
		Double actualExcess = expectedBag.excessBaggageWeight();
		Double expectedExcess = 0.0; 
		
		assertEquals(expectedExcess, actualExcess); 
		
		// when fees are expected "Economy" class allows 25Kgs of weight, 100 input so expected fees = 75
		
		Double actualExcess2 = expectedBag2.excessBaggageWeight();
		Double expectedExcess2 = 75.0; 
		
		assertEquals(expectedExcess2, actualExcess2);
	}

	@Test
	public void testGetMaxBaggageWeightFlight() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcessBaggageVolume() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxBaggageVolumeFlight() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcessWeightFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcessVolumeFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcessBaggageFee() {
		fail("Not yet implemented");
	}

	@Test
	public void testBaggageDetails() {
		fail("Not yet implemented");
	}

}
