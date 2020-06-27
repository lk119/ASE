package Model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BaggageTest.class, NameTest.class, PassengerSetTest.class,
		PassengersInQueueTest.class, PassengerTest.class })
public class testSuite {

}
