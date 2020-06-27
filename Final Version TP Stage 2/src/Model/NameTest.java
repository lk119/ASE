package Model;

import static org.junit.Assert.*;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

public class NameTest {

	// initialise variables
	Name expectedName;

	// Creates a Logger
	Logger logger = Logger.getLogger(PassengerTest.class.getName());

	@Before
	public void init() {

		logger.info("Startup - Creates Generic Objects for testing");

		// setup objects needed for testing
		expectedName = new Name("Test", "Example", "McTesterson");

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
		String actual1 = expectedName.getLastName();
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
		String actual1 = expectedName.getFullName();
		assertEquals(expected1, actual1);
	}

}
