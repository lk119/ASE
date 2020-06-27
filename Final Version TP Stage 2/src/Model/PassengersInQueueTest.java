package Model;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

/**
 * Advanced Software Engineering - Coursework Class used for testing.
 *
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */

public class PassengersInQueueTest {

	// initialise variables
	private Name expectedName, expectedName2, expectedName3, expectedName4, expectedName5;
	private Passenger expectedPassenger, expectedPassenger2, expectedPassenger3, expectedPassenger4, expectedPassenger5;

	private PassengerSet testPassengerSet;
	private PassengersInQueue testPassengersInQueue;

	// Creates a Logger
	Logger logger = Logger.getLogger(PassengerSetTest.class.getName());

	@Before
	public void init() throws InvalidBookingReference {

		logger.info("Startup - Creates Generic Objects for testing");

		// setup objects needed for testing
		expectedName = new Name("Test", "Example", "McTesterson");
		expectedName2 = new Name("Testy", "Exampley", "McTestersony");
		expectedName3 = new Name("Gale", "Sabina", "Alvarado");
		expectedName4 = new Name("Kesha", "Mindy", "Casey");
		expectedName5 = new Name("Soo", "Necole", "Austin");
		expectedPassenger = new Passenger("QA410500", "QA410", expectedName, false, "Economy", null);
		expectedPassenger2 = new Passenger("BA80801", "BA808", expectedName2, false, "Business", null);
		expectedPassenger3 = new Passenger("BA80802", "BA808", expectedName3, false, "Economy", null);
		expectedPassenger4 = new Passenger("EA203139", "EA203", expectedName4, false, "Economy", null);
		expectedPassenger5 = new Passenger("EA203140", "EA203", expectedName5, false, "Business", null);

	}

	/**
	 * Tests that only economy passengers are taken from a passenger list 'testList', moved to a new list 'q1' and have been removed from 
	 * the initial list 
	 * 
	 * @throws InterruptedException
	 * @result A list of economy passengers 'q1' that are not present in testList 
	 */
	
	@Test
	public void testEconomyQueueputi() throws InterruptedException {

		logger.info(
				"Tests the correct passengers are taken from one list, placed in another, then removed from the initial list");

		LinkedList<Passenger> testList = new LinkedList<Passenger>();

		testList.add(expectedPassenger);
		testList.add(expectedPassenger2);
		testList.add(expectedPassenger3);
		testList.add(expectedPassenger4);
		testList.add(expectedPassenger5);

		LinkedList<Passenger> q1 = new LinkedList<Passenger>();

		testList.forEach((passenger) -> {

			// if condition to add economy class passengers in q1 linked list
			if (passenger.getpClass().equalsIgnoreCase("economy") && testList != null) {

				q1.add(passenger);

			}
		});

		LinkedList<Passenger> expectedList = new LinkedList<Passenger>();

		// add passengers that should be added by the for conditional loop
		expectedList.add(expectedPassenger);
		expectedList.add(expectedPassenger3);
		expectedList.add(expectedPassenger4);

		// this will only be true if the correct 3 passengers have been added
		assertEquals(expectedList, q1);

		// asserting that the final part of this method removes duplicates as expected.

		q1.forEach((passenger) -> {

			// remove the passenger from the set to avoid duplication
			if (testList.contains(passenger)) {
				testList.remove(passenger);

			}
		});

		LinkedList<Passenger> expectedList2 = new LinkedList<Passenger>();

		// add passengers that should be left by the for conditional loop
		expectedList2.add(expectedPassenger2);
		expectedList2.add(expectedPassenger5);

		// this will only be true if the correct 3 passengers have been removed
		assertEquals(expectedList2, testList);

	}

	/**
	 * Tests that only first/business passengers are taken from a passenger list 'testList', moved to a new list 'q2' and have been removed from 
	 * the initial list 
	 * 
	 * @throws InterruptedException
	 * @result A list of business passengers 'q2' that are not present in testList 
	 */
	
	@Test
	public void testFirstbusinessQueueputi() {
		logger.info(
				"Testing the correct passengers are taken from list 'testList', placed in list 'q1',"
				+ " then removed from the initial list 'testList'");

		LinkedList<Passenger> testList = new LinkedList<Passenger>();

		testList.add(expectedPassenger);
		testList.add(expectedPassenger2);
		testList.add(expectedPassenger3);
		testList.add(expectedPassenger4);
		testList.add(expectedPassenger5);

		LinkedList<Passenger> q1 = new LinkedList<Passenger>();

		testList.forEach((passenger) -> {

			// if condition to add economy class passengers in q1 linked list
			if (passenger.getpClass().equalsIgnoreCase("first")
					|| passenger.getpClass().equalsIgnoreCase("business") && testList != null) {

				q1.add(passenger);

			}
		});

		LinkedList<Passenger> expectedList = new LinkedList<Passenger>();

		// add passengers that should be added by the for conditional loop
		expectedList.add(expectedPassenger2);
		expectedList.add(expectedPassenger5);

		// this will only be true if the correct 3 passengers have been added
		assertEquals(expectedList, q1);

		// asserting that the final part of this method removes duplicates as expected.

		q1.forEach((passenger) -> {

			// remove the passenger from the set to avoid duplication
			if (testList.contains(passenger)) {
				testList.remove(passenger);

			}
		});

		LinkedList<Passenger> expectedList2 = new LinkedList<Passenger>();

		// add passengers that should be left by the for conditional loop
		expectedList2.add(expectedPassenger);
		expectedList2.add(expectedPassenger3);
		expectedList2.add(expectedPassenger4);

		// this will only be true if the correct 3 passengers have been removed
		assertEquals(expectedList2, testList);

	}

	/**
	 * Tests that the for loop takes passengers from economy list 'q1' are moved to new list 'checkedIn' after  :.
	 *  1) Checked in status set to true
	 *  2) Baggage dimensions and details are stored in the correct flight list (BA/QA/EA)
	 *  3) Passenger is added to list 'checkedIn' and 'forSecurity'
	 *  4) Passenger Removed from the initialList 'q1'
	 * 
	 * 
	 * @throws InterruptedException
	 * @result Updated passenger lists 'checkedIn' & 'forSecurity' and double lists for the respective flight details
	 */
	
	@Test
	public void testEconomyPicker() {
		
		logger.info(
				"Testin only economy passengers are taken from the q1 list, "
				+ "placed in checkedIn and forSecurity, then removed from the initial list");
		
		LinkedList<Passenger> q1 = new LinkedList<Passenger>();
		q1.add(expectedPassenger);
		q1.add(expectedPassenger3);
		q1.add(expectedPassenger4);

		LinkedList<Baggage> Bags = new LinkedList<Baggage>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// Qatar flight
		LinkedList<Double> QAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// Qatar flight
		LinkedList<Double> QAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for Qatar
		// flight
		LinkedList<Double> QAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// BA flight
		LinkedList<Double> BAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// BA flight
		LinkedList<Double> BAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for BA
		// flight
		LinkedList<Double> BAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// Emirates flight
		LinkedList<Double> EAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// Emirates flight
		LinkedList<Double> EAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for
		// Emirated flight
		LinkedList<Double> EAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list shared data structure to stored passengers who have checked in
		LinkedList<Passenger> Checkedin = new LinkedList<Passenger>();
		LinkedList<Passenger> forSecurity = new LinkedList<Passenger>();

		for (int i = 0; i <= 2; i++) {

			// Passenger object p is the first element in the q1 linked list
			// remove() method will remove the object from the linked list
			Passenger p = q1.removeFirst();

			// for passenger travelling by economy class baggage b object is created
			Baggage b = p.createAbag(p);

			// add the checked-in bag in the Bags Linked List
			Bags.add(b);

			// calculate the flight weight capacity reached
			double weightCapacity = ((b.getWeight() / 3000) * 100);

			// calculate the flight volume capacity reached
			double volumeCapacity = ((b.baggageVolume() / 80000) * 100);

			// calculate the excess baggage fees collected
			double baggageFees = b.excessBaggageFee();

			// add the values from the above calculations into respective linked lists
			if (p.getflightCode().equalsIgnoreCase("QA410")) {

				QAflightweightCapacity.add(weightCapacity);
				QAflightvolumeCapacity.add(volumeCapacity);
				QAflightexcessbaggaefees.add(baggageFees);

			} else if (p.getflightCode().equalsIgnoreCase("BA808")) {

				BAflightweightCapacity.add(weightCapacity);
				BAflightvolumeCapacity.add(volumeCapacity);
				BAflightexcessbaggaefees.add(baggageFees);
			}

			else if (p.getflightCode().equalsIgnoreCase("EA203")) {

				EAflightweightCapacity.add(weightCapacity);
				EAflightvolumeCapacity.add(volumeCapacity);
				EAflightexcessbaggaefees.add(baggageFees);
			}

			// the check-in status is set as true
			p.setCheckInStatus(true);

			// the passenger is added in the linked list Checkedin
			Checkedin.add(p);
			forSecurity.add(p);

			// remove the passenger from the set to avoid duplication
			if (q1.contains(p)) {
				q1.remove(p);
			}
		}

		// asserts that 1 passengers details has been added for the QA flight
		int expectedBASize = 1;
		int expectedBASize2 = 1;
		int expectedBASize3 = 1;

		int actualBASize = BAflightweightCapacity.size();
		int actualBASize2 = BAflightvolumeCapacity.size();
		int actualBASize3 = BAflightexcessbaggaefees.size();

		assertEquals(expectedBASize, actualBASize);
		assertEquals(expectedBASize2, actualBASize2);
		assertEquals(expectedBASize3, actualBASize3);

		// asserts that 1 passengers details has been added for the QA flight
		int expectedQASize = 1;
		int expectedQASize2 = 1;
		int expectedQASize3 = 1;

		int actualQASize = QAflightweightCapacity.size();
		int actualQASize2 = QAflightvolumeCapacity.size();
		int actualQASize3 = QAflightexcessbaggaefees.size();

		assertEquals(expectedQASize, actualQASize);
		assertEquals(expectedQASize2, actualQASize2);
		assertEquals(expectedQASize3, actualQASize3);

		// asserts that 1 passengers details has been added for the EA flight
		int expectedEASize = 1;
		int expectedEASize2 = 1;
		int expectedEASize3 = 1;

		int actualEASize = EAflightweightCapacity.size();
		int actualEASize2 = EAflightvolumeCapacity.size();
		int actualEASize3 = EAflightexcessbaggaefees.size();

		assertEquals(expectedEASize, actualEASize);
		assertEquals(expectedEASize2, actualEASize2);
		assertEquals(expectedEASize3, actualEASize3);

		LinkedList<Passenger> expectedList = new LinkedList<Passenger>();

		// add passengers that should be left by the for conditional loop
		expectedList.add(expectedPassenger);
		expectedList.add(expectedPassenger3);
		expectedList.add(expectedPassenger4);

		// checks that the passengers have been added to the checked in list after
		// processing
		assertEquals(expectedList, Checkedin);

		// checks that the passengers have been added to the checked in list after
		// processing
		assertEquals(expectedList, forSecurity);

		LinkedList<Passenger> expectedList2 = new LinkedList<Passenger>();

		// add passengers that processes passengers have been removed from the list,
		// only True if no passengers remain in q1
		assertEquals(expectedList2, q1);

	}
//	}

	/**
	 * Tests that the for loop takes passengers from economy list 'q2' are moved to new list 'checkedIn' after  :.
	 *  1) Checked in status set to true
	 *  2) Baggage dimensions and details are stored in the correct flight list (BA/QA/EA)
	 *  3) Passenger is added to list 'checkedIn' and 'forSecurity'
	 *  4) Passenger Removed from the initialList 'q1'
	 * 
	 * 
	 * @throws InterruptedException
	 * @result Updated passenger lists 'checkedIn' & 'forSecurity' and double lists for the respective flight details
	 */
	
	@Test
	public void testBusinessFirstPicker() {
		
		logger.info(
				"Testing that only business/first passengers are taken from the q1 list,"
				+ " placed in checkedIn and forSecurity, then removed from the initial list");

		
		LinkedList<Passenger> q2 = new LinkedList<Passenger>();
		q2.add(expectedPassenger2);
		q2.add(expectedPassenger5);

		LinkedList<Baggage> Bags = new LinkedList<Baggage>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// Qatar flight
		LinkedList<Double> QAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// Qatar flight
		LinkedList<Double> QAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for Qatar
		// flight
		LinkedList<Double> QAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// BA flight
		LinkedList<Double> BAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// BA flight
		LinkedList<Double> BAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for BA
		// flight
		LinkedList<Double> BAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list data structure to store flight weight capacity to carry bags for
		// Emirates flight
		LinkedList<Double> EAflightweightCapacity = new LinkedList<Double>();

		// Linked list data structure to store flight volume capacity to carry bags for
		// Emirates flight
		LinkedList<Double> EAflightvolumeCapacity = new LinkedList<Double>();

		// Linked list data structure to store excess baggage fees collected for
		// Emirated flight
		LinkedList<Double> EAflightexcessbaggaefees = new LinkedList<Double>();

		// Linked list shared data structure to stored passengers who have checked in
		LinkedList<Passenger> Checkedin = new LinkedList<Passenger>();
		LinkedList<Passenger> forSecurity = new LinkedList<Passenger>();

		for (int i = 0; i <= 1; i++) {

			// Passenger object p is the first element in the q1 linked list
			// remove() method will remove the object from the linked list
			Passenger p = q2.removeFirst();

			// for passenger travelling by economy class baggage b object is created
			Baggage b = p.createAbag(p);

			// add the checked-in bag in the Bags Linked List
			Bags.add(b);

			// calculate the flight weight capacity reached
			double weightCapacity = ((b.getWeight() / 3000) * 100);

			// calculate the flight volume capacity reached
			double volumeCapacity = ((b.baggageVolume() / 80000) * 100);

			// calculate the excess baggage fees collected
			double baggageFees = b.excessBaggageFee();

			// add the values from the above calculations into respective linked lists
			if (p.getflightCode().equalsIgnoreCase("QA410")) {

				QAflightweightCapacity.add(weightCapacity);
				QAflightvolumeCapacity.add(volumeCapacity);
				QAflightexcessbaggaefees.add(baggageFees);

			} else if (p.getflightCode().equalsIgnoreCase("BA808")) {

				BAflightweightCapacity.add(weightCapacity);
				BAflightvolumeCapacity.add(volumeCapacity);
				BAflightexcessbaggaefees.add(baggageFees);
			}

			else if (p.getflightCode().equalsIgnoreCase("EA203")) {

				EAflightweightCapacity.add(weightCapacity);
				EAflightvolumeCapacity.add(volumeCapacity);
				EAflightexcessbaggaefees.add(baggageFees);
			}

			// the check-in status is set as true
			p.setCheckInStatus(true);

			// the passenger is added in the linked list Checkedin
			Checkedin.add(p);
			forSecurity.add(p);

			// remove the passenger from the set to avoid duplication
			if (q2.contains(p)) {
				q2.remove(p);
			}
		}

		// asserts that 1 passengers details has been added for the QA flight
		int expectedBASize = 1;
		int expectedBASize2 = 1;
		int expectedBASize3 = 1;

		int actualBASize = BAflightweightCapacity.size();
		int actualBASize2 = BAflightvolumeCapacity.size();
		int actualBASize3 = BAflightexcessbaggaefees.size();

		assertEquals(expectedBASize, actualBASize);
		assertEquals(expectedBASize2, actualBASize2);
		assertEquals(expectedBASize3, actualBASize3);

		// asserts that 0 passengers details has been added for the QA flight as there
		// are no
		// business/first class passengers for this flight
		int expectedQASize = 0;
		int expectedQASize2 = 0;
		int expectedQASize3 = 0;

		int actualQASize = QAflightweightCapacity.size();
		int actualQASize2 = QAflightvolumeCapacity.size();
		int actualQASize3 = QAflightexcessbaggaefees.size();

		assertEquals(expectedQASize, actualQASize);
		assertEquals(expectedQASize2, actualQASize2);
		assertEquals(expectedQASize3, actualQASize3);

		// asserts that 1 passengers details has been added for the EA flight
		int expectedEASize = 1;
		int expectedEASize2 = 1;
		int expectedEASize3 = 1;

		int actualEASize = EAflightweightCapacity.size();
		int actualEASize2 = EAflightvolumeCapacity.size();
		int actualEASize3 = EAflightexcessbaggaefees.size();

		assertEquals(expectedEASize, actualEASize);
		assertEquals(expectedEASize2, actualEASize2);
		assertEquals(expectedEASize3, actualEASize3);

		LinkedList<Passenger> expectedList = new LinkedList<Passenger>();

		// add passengers that should be left by the for conditional loop
		expectedList.add(expectedPassenger2);
		expectedList.add(expectedPassenger5);

		// checks that the passengers have been added to the checked in list after
		// processing
		assertEquals(expectedList, Checkedin);

		// checks that the passengers have been added to the checked in list after
		// processing
		assertEquals(expectedList, forSecurity);

		LinkedList<Passenger> expectedList2 = new LinkedList<Passenger>();

		// add passengers that processes passengers have been removed from the list,
		// only True if no passengers remain in q1
		assertEquals(expectedList2, q2);

	}

	
	/**
	 * Testing the for loop within securityPicker that it takes passengers from 'forSecurity' list that have been checked in 
	 * and adds them to another list 'clearedSecurity', then removes this passenger from the initial list to prevent duplication. 
	 * 
	 * @result the 'clearedSecurity' list has been populated with passengers removed from the 'forSecurity' list
	 * 
	 */
	
	
	@Test
	public void testSecurityPicker() {
		
		logger.info(
				"Testing that only checkedIn passengers are taken from the forSecuirty list, "
				+ "placed in clearedSecurity, then removed from the initial list");


		LinkedList<Passenger> forSecurity = new LinkedList<Passenger>();

		LinkedList<Passenger> checkIn = new LinkedList<Passenger>();

		checkIn.add(expectedPassenger);
		checkIn.add(expectedPassenger2);
		checkIn.add(expectedPassenger3);
		checkIn.add(expectedPassenger4);
		checkIn.add(expectedPassenger5);

		checkIn.forEach((passenger) -> {
			passenger.setCheckInStatus(true);
			forSecurity.add(passenger);

		});

		LinkedList<Passenger> clearedSecurity = new LinkedList<Passenger>();

		// for loop to go through the linked list
		for (int i = 0; i <= 4; i++) {

			// Passenger object p is the first element in the linked list
			// remove() method will remove the object from the linked list
			Passenger p = forSecurity.removeFirst();

			// check if the passengers have checked in
			if (p.getCheckInStatus() == true) {

				// the passenger is added in the linked list security
				clearedSecurity.add(p);

				// remove the passenger from the set to avoid duplication
				if (forSecurity.contains(p)) {
					forSecurity.remove(p);
				}

			}
		}

		LinkedList<Passenger> expectedCleared = new LinkedList<Passenger>();

		expectedCleared.add(expectedPassenger);
		expectedCleared.add(expectedPassenger2);
		expectedCleared.add(expectedPassenger3);
		expectedCleared.add(expectedPassenger4);
		expectedCleared.add(expectedPassenger5);

		assertEquals(expectedCleared, clearedSecurity);

		LinkedList<Passenger> expectedNotCleared = new LinkedList<Passenger>();

		assertEquals(expectedNotCleared, forSecurity);

	}

	/**
	 * Testing the for loop within boardingPicker that it takes passengers from 'clearedSecurity' list that have been checked in 
	 * and adds them to another list 'boarding', then removes this passenger from the initial list to prevent duplication.
	 * 
	 *  A second elseif loop tests the passengers associated flight code and adds the passengers to the corresponding list (eg 
	 *  BA808 code would divert the passenger to the BA list)   
	 * 
	 * @result the 'boarding' and flight lists have been populated with passengers removed from the 'clearedSecurity' list
	 * 
	 */
	
	@Test
	public void testBoardingPicker() {
		
		logger.info(
				"Testing that passengers and their luggage have cleared security, "
				+ "are checked for their code and added to a list based on this ");


		LinkedList<Passenger> clearedSecurity = new LinkedList<Passenger>();

		LinkedList<Passenger> checkIn = new LinkedList<Passenger>();

		checkIn.add(expectedPassenger);
		checkIn.add(expectedPassenger2);
		checkIn.add(expectedPassenger3);
		checkIn.add(expectedPassenger4);
		checkIn.add(expectedPassenger5);

		checkIn.forEach((passenger) -> {
			passenger.setCheckInStatus(true);
			clearedSecurity.add(passenger);

		});

		LinkedList<Passenger> boarding = new LinkedList<Passenger>();
		LinkedList<Passenger> BA = new LinkedList<Passenger>();
		LinkedList<Passenger> emirates = new LinkedList<Passenger>();
		LinkedList<Passenger> qatar = new LinkedList<Passenger>();

		// for loop to go through the linked list
		for (int i = 0; i < checkIn.size(); i++) {

			// returns the first passenger object from the clearedSecurity linked list
			Passenger p = clearedSecurity.peekFirst();

			// the passenger object is checked for different conditions (flight code) to
			// populate
			// flight specific linked lists

			if (p.getCheckInStatus() == true && p.getflightCode().equalsIgnoreCase("QA410")) {

				// remove the first passenger if both the conditions in the if () are met
				clearedSecurity.removeFirst();

				// the passenger is added in the boarding linked list which is used
				// to check if all passengers from the input list have eventually boarded
				boarding.add(p);

				// flight data structure to mimic passengers on board
				qatar.add(p);

				// remove the passenger from the set to avoid duplication
				if (clearedSecurity.contains(p)) {
					clearedSecurity.remove(p);
				}

			}

			else if (p.getCheckInStatus() == true && p.getflightCode().equalsIgnoreCase("BA808")) {

				// remove the first passenger if both the conditions in the if () are met
				clearedSecurity.removeFirst();

				// the passenger is added in the boarding linked list which is used
				// to check if all passengers from the input list have eventually boarded
				boarding.add(p);

				// flight data structure to mimic passengers on board
				BA.add(p);

				// remove the passenger from the set to avoid duplication
				if (clearedSecurity.contains(p)) {
					clearedSecurity.remove(p);
				}

			} else if (p.getCheckInStatus() == true && p.getflightCode().equalsIgnoreCase("EA203")) {

				// remove the first passenger if both the conditions in the if () are met
				clearedSecurity.removeFirst();

				// the passenger is added in the boarding linked list which is used
				// to check if all passengers from the input list have eventually boarded

				boarding.add(p);

				// flight data structure to mimic passengers on board
				emirates.add(p);

				// remove the passenger from the set to avoid duplication
				if (clearedSecurity.contains(p)) {
					clearedSecurity.remove(p);
				}
			}
		}
		
		LinkedList<Passenger> expectedCleardSecurity = new LinkedList<Passenger>();
			assertEquals(expectedCleardSecurity, clearedSecurity);
		
		LinkedList<Passenger> expectedboarding = new LinkedList<Passenger>();
			expectedboarding.add(expectedPassenger);
			expectedboarding.add(expectedPassenger2);
			expectedboarding.add(expectedPassenger3);
			expectedboarding.add(expectedPassenger4);
			expectedboarding.add(expectedPassenger5);
		assertEquals(expectedCleardSecurity, clearedSecurity);
		
		LinkedList<Passenger> expectedBA = new LinkedList<Passenger>();
			expectedBA.add(expectedPassenger2);
			expectedBA.add(expectedPassenger3);
		
		assertEquals(expectedBA, BA);
		
		LinkedList<Passenger> expectedEmirates = new LinkedList<Passenger>();
			expectedEmirates.add(expectedPassenger4);
			expectedEmirates.add(expectedPassenger5);
	
		assertEquals(expectedEmirates, emirates);
		
		LinkedList<Passenger> expectedQatar = new LinkedList<Passenger>();
			expectedQatar.add(expectedPassenger);

		assertEquals(expectedQatar, qatar);
		
		
		
	}

	/**
	 * Tests the for loop logic of the three WeightCapacity methods for the three flights BA/QA/EA
	 * positive tests will result from correct summation of the 4 doubles. 
	 * 
	 * @result double of the sum of 4 doubles within the airlineWeights list
	 */
	
	@Test
	public void testAirlinelWeightCapacity() {
		
		logger.info(
				"Testing correct summation of list values for weightCapacity"); 
		
		double a = 20;
		double b = 25.90;
		double c = 30;
		double d = 25.05;
		
		LinkedList<Double> airlineWeights = new LinkedList<Double>();	
		airlineWeights.add(a);
		airlineWeights.add(b);
		airlineWeights.add(c);
		airlineWeights.add(d);
	
		double sum = 0;
		
		for (int i = 0; i < airlineWeights.size(); i++) {
			sum += airlineWeights.get(i);
		}
		
		double expectedSum = 100.95;
		
		assertEquals(expectedSum, sum, 0);
	}

	/**
	 * Tests the for loop logic of the three VolumeCapacity methods for the three flights BA/QA/EA
	 * positive tests will result from correct summation of the 4 doubles. 
	 * 
	 * @result double of the sum of 4 doubles within the airlineVolumes list
	 */
	
	@Test
	public void testAirlineVolumeCapacity() {
		
		logger.info(
				"Testing correct summation of list values for volumeCapacity");
		
		double a = 20;
		double b = 25.60;
		double c = 30.43;
		double d = 21.05;
		
		LinkedList<Double> airlineVolumes = new LinkedList<Double>();	
		airlineVolumes.add(a);
		airlineVolumes.add(b);
		airlineVolumes.add(c);
		airlineVolumes.add(d);
	
		double sum = 0;
		
		for (int i = 0; i < airlineVolumes.size(); i++) {
			sum += airlineVolumes.get(i);
		}
		
		double expectedSum = 97.08;
		
		assertEquals(expectedSum, sum, 0);
		
	}

	/**
	 * Tests the for loop logic of the three ExcessFeesCollected methods for the three flights BA/QA/EA
	 * positive tests will result from correct summation of the 4 doubles. 
	 * 
	 * @result double of the sum of 4 doubles within the airlineVolumes list
	 */
	
	@Test
	public void testAirlineExcessFeesCollected() {
		
		logger.info(
				"Testing correct summation of list values for FeesCollected");
		
		double a = 30;
		double b = 25.70;
		double c = 30;
		double d = 17.05;
		
		LinkedList<Double> airlineFees = new LinkedList<Double>();	
		airlineFees.add(a);
		airlineFees.add(b);
		airlineFees.add(c);
		airlineFees.add(d);
	
		double sum = 0;
		
		for (int i = 0; i < airlineFees.size(); i++) {
			sum += airlineFees.get(i);
		}
		
		double expectedSum = 102.75;
		
		assertEquals(expectedSum, sum, 0);
	}


}
