package Model;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

/**
 * Advanced Software Engineering - Coursework Class used for testing.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class BaggageTest {

	// initialise variables
	private Name expectedName;
	private Passenger expectedPassenger, expectedPassenger2;
	private Baggage expectedBag, expectedBag2, expectedBag3;

	// Creates a Logger
	Logger logger = Logger.getLogger(BaggageTest.class.getName());

	@Before
	public void init() throws InvalidBookingReference {

		logger.info("Startup - Creates Generic Objects for testing");

		// setup objects needed for testing
		expectedName = new Name("Test", "Example", "McTesterson");
		expectedPassenger = new Passenger("QA410500", "QA410", expectedName, false, "Economy", null);
		expectedPassenger2 = new Passenger("QA410500", "QA410", expectedName, false, "Business", null);
		expectedBag = new Baggage(5, 5, 5, 5, expectedPassenger);
		expectedBag2 = new Baggage(100, 100, 100, 100, expectedPassenger);
		expectedBag3 = new Baggage(100, 100, 100, 100, expectedPassenger2);
	}

	/**
	 * Test that the method gets the correct Passenger object from the associated
	 * baggage
	 * 
	 * @result Passenger Object retrieved from the associated baggage
	 */
	@Test
	public void testGetPassenger() {

		logger.info("Testing correct passenger is retrieved");

		Passenger actualPassenger = expectedBag.getPassenger();

		assertSame(expectedPassenger, actualPassenger);
	}

	/**
	 * Tests that the correct volume is calculated from a given baggage object
	 *
	 * @result double of the volume of the baggage called on
	 */

	@Test
	public void testBaggageVolume() {

		logger.info("Testing correct baggage volume is calculated");

		Double actualVolume = expectedBag.baggageVolume();

		Double expectedVolume = 125.0;

		assertEquals(expectedVolume, actualVolume);
	}

	/**
	 * Testing correct excess baggage is calculated
	 * 
	 * @result Double of the excess fee (if applicable)
	 */

	@Test
	public void testExcessBaggageWeight() {

		// when fees should be 0
		logger.info("Testing correct excess baggage is calculated");

		Double actualExcess = expectedBag.excessBaggageWeight();
		Double expectedExcess = 0.0;

		assertEquals(expectedExcess, actualExcess);

		// when fees are expected "Economy" class allows 25Kgs of weight, 100 input so
		// expected fees = 75

		Double actualExcess2 = expectedBag2.excessBaggageWeight();
		Double expectedExcess2 = 75.0;

		assertEquals(expectedExcess2, actualExcess2);
	}

	/**
	 * Testing correct weight is retrieved for a passenger of that class, ie Economy
	 * = 25
	 * 
	 * @result Double of the correct max weight before fees
	 */

	@Test
	public void testGetMaxBaggageWeightFlight() {

		logger.info("Testing correct excess weight fee is calculated correctly");

		double actualBaggageWeight = expectedBag.getMaxBaggageWeightFlight();
		double expectedBaggageweight = 25.0;

		assertEquals(expectedBaggageweight, actualBaggageWeight, 0);

		double actualBaggegWeight2 = expectedBag3.getMaxBaggageWeightFlight();
		double expectedBaggageWeight2 = 35.0;

		assertEquals(expectedBaggageWeight2, actualBaggegWeight2, 0);

	}

	/**
	 * Testing correct excess volume is calculated when called on a baggage object
	 * 
	 * @result double = Excess volume used for fee calculation
	 */

	@Test
	public void testExcessBaggageVolume() {

		logger.info("Testing correct excess volume is calculated");

		double actualBagVolume = expectedBag.excessBaggageVolume();
		double expectedBagVolume = 50.0;

		assertEquals(expectedBagVolume, actualBagVolume, 0);

		double actualBagVolume2 = expectedBag3.excessBaggageVolume();
		double expectedBagVolume2 = 999900.0;

		assertEquals(expectedBagVolume2, actualBagVolume2, 0);
	}

	/**
	 * Testing correct excess volume for flight is retrieved
	 * 
	 * @result double of the expected volume for the given class of passenger
	 */

	@Test
	public void testGetMaxBaggageVolumeFlight() {

		logger.info("Testing correct excess volume for flight is retrieved");

		double actualVolume = expectedBag.getMaxBaggageVolumeFlight();
		double expectedVolume = 75.0;

		assertEquals(expectedVolume, actualVolume, 0);

		double actualVolume2 = expectedBag3.getMaxBaggageVolumeFlight();
		double expectedVolume2 = 100.0;

		assertEquals(expectedVolume2, actualVolume2, 0);

	}

	/**
	 * Testing excess weight fee is calculated correctly
	 * 
	 * @result 
	 */

	@Test
	public void testExcessWeightFee() {

		logger.info("Testing excess weight fee is calculated correctly");

		double actualFees = expectedBag2.excessWeightFee();
		double expectedFees = 750.0;

		assertEquals(expectedFees, actualFees, 0);

		double actualFees2 = expectedBag.excessWeightFee();
		double expectedFees2 = 0.0;

		assertEquals(expectedFees2, actualFees2, 0);

	}

	@Test
	public void testExcessVolumeFee() {

		logger.info("Testing correct excess volume fee is calculated");

		double actualFee = expectedBag.excessVolumeFee();
		double expectedFee = 20.0;

		assertEquals(expectedFee, actualFee, 0);

		double actualFee2 = expectedBag3.excessVolumeFee();
		double expectedFee2 = 20.0;

		assertEquals(expectedFee2, actualFee2, 0);

	}

	@Test
	public void testExcessBaggageFee() {

		logger.info("Testing correct baggage fee is calculated");

		double actualFee = expectedBag.excessBaggageFee();
		double expectedFee = 20.0;

		assertEquals(expectedFee, actualFee, 0);

		double actualFee2 = expectedBag2.excessBaggageFee();
		double expectedFee2 = 770.0;

		assertEquals(expectedFee2, actualFee2, 0);

		double actualFee3 = expectedBag3.excessBaggageFee();
		double expectedFee3 = 670.0;

		assertEquals(expectedFee3, actualFee3, 0);

	}

	@Test
	public void testBaggageDetails() {

		logger.info("Testing correct details are retrieved");

		String actualDetails = expectedBag.baggageDetails();
		String expectedDetails = "5.0125.020.0";

		assertEquals(expectedDetails, actualDetails);

		String actualDetails2 = expectedBag3.baggageDetails();
		String expectedDetails2 = "100.01000000.0670.0";

		assertEquals(expectedDetails2, actualDetails2);

	}

}
