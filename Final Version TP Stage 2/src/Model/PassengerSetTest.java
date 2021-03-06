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

public class PassengerSetTest {

	// initialise variables
	private Name expectedName, expectedName2;
	private Passenger expectedPassenger, expectedPassenger2;

	private PassengerSet testPassengerSet;

	// Creates a Logger
	Logger logger = Logger.getLogger(PassengerSetTest.class.getName());

	@Before
	public void init() throws InvalidBookingReference {

		logger.info("Startup - Creates Generic Objects for testing");

		// setup objects needed for testing
		expectedName = new Name("Test", "Example", "McTesterson");
		expectedName2 = new Name("Testy", "Exampley", "McTestersony");
		expectedPassenger = new Passenger("QA410500", "QA410", expectedName, false, "Economy", null);
		expectedPassenger2 = new Passenger("QA410500", "QA410", expectedName2, false, "Business", null);
		testPassengerSet = new PassengerSet();
		testPassengerSet.add(expectedPassenger);
	}

	/**
	 * Tests that the correct passenger has been added to the list called on.
	 * 
	 * @result Assert this by checking that the list has increased in size, contains
	 *         the addition and doesnt contain the other passenger object to ensure.
	 */
	@Test
	public void testAdd() {

		logger.info("Testing addition of passengers to arraylist is successful");

		// size at start
		int actualSize = testPassengerSet.totalcustomers();

		int expectedSize = 1;

		assertEquals(expectedSize, actualSize);

		// should be present in list as list initiated with passenger present
		assertTrue(testPassengerSet.cont(expectedPassenger));
		// should not be present in list as list initiated without this passenger
		// present
		assertFalse(testPassengerSet.cont(expectedPassenger2));

		// add a passenger
		testPassengerSet.add(expectedPassenger2);

		// size should now be 2
		int actualSize2 = testPassengerSet.totalcustomers();
		int expectedSize2 = 2;

		assertEquals(expectedSize2, actualSize2);

		// should now be present in list
		assertTrue(testPassengerSet.cont(expectedPassenger2));

	}

	/**
	 * Tests that the method is indeed recognising when a specific object is held
	 * within the arraylist 'testPassengerSet'
	 * 
	 * @result Asserts that it recognises the added passenger is present in the
	 *         list, but not one that has not been added to the list
	 */

	@Test
	public void testCont() {

		logger.info("Testing if passenger object is held within arraylist");

		// should be present in list as this passenger has been added
		assertTrue(testPassengerSet.cont(expectedPassenger));

		// should not be present in list as they haven't been added
		assertFalse(testPassengerSet.cont(expectedPassenger2));

	}

	/**
	 * Tests that the method does removes the correct object from the list called on
	 * 
	 * @result Passes if the specified passenger is present before but not after the
	 *         method is called
	 */

	@Test
	public void testRemoveOb() {

		logger.info("Testing removal of passengers from arrayList is successful");

		// passenger should be present before removal
		assertTrue(testPassengerSet.cont(expectedPassenger));

		testPassengerSet.removeOb(expectedPassenger);

		// passenger should now not be present
		assertFalse(testPassengerSet.cont(expectedPassenger));
	}

	/**
	 * Testing that the method pulls out an object from the given list
	 * 
	 * For the purposes of this test, we simply prove that it does pull out an
	 * object present from within the list.
	 * 
	 * @result returns a passenger from the list
	 * 
	 */
	@Test
	public void testRando() {

		logger.info("Testing random passenger selector is picking correct objects");

		// test is a little redundant as there is only one passenger, but asserts that
		// the passenger picked is in the correct list.
		Passenger actualPassenger = testPassengerSet.rando();

		assertSame((expectedPassenger), actualPassenger);

	}

	/**
	 * Tests that this method counts the correct list and is correct
	 * 
	 * @result int output of the size of each list, passes test if it gets the
	 *         correct size before and after adding an object to a list
	 */

	@Test
	public void testTotalCustomers() {

		logger.info("Testing population numbers of the PassengerSet");

		// size at start
		int actualSize = testPassengerSet.totalcustomers();

		int expectedSize = 1;

		assertEquals(expectedSize, actualSize);

		// add a passenger
		testPassengerSet.add(expectedPassenger2);

		// size should now be 2
		int actualSize2 = testPassengerSet.totalcustomers();
		int expectedSize2 = 2;

		assertEquals(expectedSize2, actualSize2);

	}

}
