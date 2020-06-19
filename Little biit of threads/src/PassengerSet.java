import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class PassengerSet {

	private HashSet<Passenger> PassengerSet;
	private Scanner scanner;

	/**
	 * Creates the hash set for the passengers
	 */
	public PassengerSet() {
		PassengerSet = new HashSet<Passenger>();
		
	}

	/**
	 * Method to allow addition of passenger objects to the set of passengers
	 * 
	 * @param p the passenger object
	 */
	public void add(Passenger p) {
		PassengerSet.add(p);
	}
	
	public  int totalcustomers() {
		
		return  PassengerSet.size();


	}
	
	
	
	//return random passenger
public Passenger rando()	{
	Random random= new Random();
	Iterator<Passenger> iter = PassengerSet.iterator();

	try {
	int index = random.nextInt(PassengerSet.size());
	for (int i = 0; i < index; i++) {
	    iter.next();
	}
	
	}
	
	catch(IllegalArgumentException iae) {
		System.out.println("All Customers have been processed");
	}
	return iter.next();
}
	
	
	
	
	
	

	/**
	 * This returns the set of the passengers
	 * 
	 * @return the set of passengers
	 */
	public HashSet<Passenger> getPassengerSet() {
		return PassengerSet;
	}

	/**
	 * Process line, extracts information and creates Passenger object
	 * 
	 * @exception NumberFormatException          if unable to format string to
	 *                                           number.
	 * @exception ArrayIndexOutOfBoundsException if the array index does not exist.
	 * @param line the line processed from input file
	 * @return 
	 */

	private void processLine(String line) {
		try {
			String parts[] = line.split(",");
			Name passengerName = new Name(parts[2], parts[3], parts[4]);
			String bookingReferenceNum = parts[0];
			String flightCode = parts[1];
			String pClass = parts[6];
			String checkInStatusString = parts[5];

			boolean checkInStatus = Boolean.parseBoolean(checkInStatusString);

			Passenger p = new Passenger(bookingReferenceNum, flightCode, passengerName, checkInStatus, pClass);
			this.add(p);
			
			
			// System.out.println(getAllPassengers());
			// System.out.println(findBookingTest2());
		}

		catch (NumberFormatException ohno) {
			String error = "Number conversion error in '" + line + "'  - " + ohno.getMessage();
			System.out.println(error);
		} catch (ArrayIndexOutOfBoundsException ohshoot) {
			String error = "Not enough items in  : '" + line + "' index position : " + ohshoot.getMessage();
			System.out.println(error);			
		} catch (InvalidBookingReference ibr) {
			String error = "Invalid Booking Reference Number : '" + line + "' index position : " + ibr.getMessage();
			System.out.println(error);
		}
		;

	}

	/**
	 * Reads specified files, extracts passenger information, creating passenger
	 * objects Add the information to passenger set
	 * 
	 * @param filename is the name of the input file
	 * @exception FileNotFoundException if the file does not exists
	 */

	public void readFile(String filename) {
		
		try {
			File f = new File(filename);
			scanner = new Scanner(f);
			while (scanner.hasNextLine()) {
				String inputLine = scanner.nextLine();
				if (inputLine.length() != 0) {
				 processLine(inputLine);
				}

			}

		} catch (FileNotFoundException fnf) {
			System.out.println(filename + " not found ");
			System.exit(0);
		}
		
	}

	/**
	 * Will find passenger based on booking reference number
	 * 
	 * @param bookingRefernceNum from the input list
	 */

	public void findBookingRefNum(String bookingRefernceNum)
			throws NoMatchingBookingReference, InvalidBookingReference {
		for (Passenger p : PassengerSet) {
			String s = p.getBookingReferenceNum();
			 if (p.getBookingReferenceNum() == (bookingRefernceNum)) {
			}

			else
				throw new NoMatchingBookingReference(bookingRefernceNum);

			return;
		}
	}

//	public String findBookingTest2() {
//		String report = "bananas are delicious";
//		return report;
//		
//	}
//
//
//	public String findBookingTest() {
//		String report = "";
//		for (Passenger p : PassengerSet) {
//			report += String.format("%-30s", p.getFullName() + " " + p.getCheckInStatus());
//			report += "\n";
//		}
//		return report;
//	}

	public Passenger findBooking2(String bookingRef, String LastName) {
		HashMap<String, Passenger> findBooking = new HashMap<String, Passenger>();
		String key = "";
		for (Passenger p : PassengerSet) {
			key = p.getBookingReferenceNum();
			Passenger value = p;
			findBooking.put(key, value);
		}
		return findBooking.get(bookingRef);
	}

	public Passenger findBooking(String bookingReferenceNum, String LastName)
			throws NoMatchingBookingReference, InvalidBookingReference, NoMatchingLastNameException {
		for (Passenger p : PassengerSet) {
			String s = p.getBookingReferenceNum();
			if (p.getBookingReferenceNum() == (bookingReferenceNum) && (p.getLastName() == (LastName))) {
				return p;
			} else if (p.getBookingReferenceNum() == (bookingReferenceNum) && (p.getLastName() != (LastName))) {
				throw new NoMatchingLastNameException(LastName);
			}

			else
				throw new NoMatchingBookingReference(bookingReferenceNum);

		}
		return null;
	}

	/**
	 * Will find passenger based on last name
	 * 
	 * @param last name from the input list
	 */

	public void findLastName(String LastName) throws NoMatchingLastNameException {
		for (Passenger p : PassengerSet) {
			if (p.getLastName() == (LastName)) {
			} else
				throw new NoMatchingLastNameException(LastName);
		}
		return;
	}

	/**
	 * Will find number of passengers checked in
	 * 
	 * @param check in status from the input list
	 * @return size of the set
	 */

	public int totalNumberOfPassengersCheckedIn() {
		for (Passenger p : PassengerSet) {
			if (p.getCheckInStatus() != false) {
			}
			return PassengerSet.size();
		}
		return 0;
	}

	/**
	 * Will generate ordered list of passengers by name
	 * 
	 * @param full name from the input list
	 * @return ordered list by name
	 */
	// produce a sorted unique ordered list of passengerName
	public TreeSet<String> listByName() {
		TreeSet<String> pName = new TreeSet<String>();
		for (Passenger p : PassengerSet) {
			pName.add(p.getFullName());
		}
		for (String name : pName) {
			System.out.println(name);
		}
		return pName;
	}

	/**
	 * Will generate ordered list of passengers by booking reference number
	 * 
	 * @param booking reference number from the input list
	 * @return ordered list by booking reference number
	 */
	// produce a sorted unique ordered list of booking reference number
	public TreeSet<String> listByBookingRefernceNum() {
		TreeSet<String> pRefNum = new TreeSet<String>();
		for (Passenger p : PassengerSet) {
			pRefNum.add(p.getBookingReferenceNum());
		}
		for (String RefNum : pRefNum) {
			System.out.println(RefNum);
		}
		return pRefNum;
	}

	/**
	 * TreeMap method to get passenger checkin status and flight code
	 * 
	 * @param key as last name and value as check-in status and flight code
	 * @return both keys and values
	 */
	// Treemap method to checkin status and flightcode using associated Last name
	// as the Key
	public String getPassengerStatusfromLastName() {
		TreeMap<String, String> PassengerStatus = new TreeMap<String, String>();
		for (Passenger p : PassengerSet) {
			PassengerStatus.put(p.getLastName(), p.getCheckInStatus() + " in flight " + p.getflightCode());
		}
		for (Map.Entry<String, String> entry : PassengerStatus.entrySet()) {
			return (entry.getKey() + entry.getValue());
		}
		return null;
	}

	/**
	 * TreeMap method to get passenger checkin status and flight code
	 * 
	 * @param key as booking reference number and value as check-in status and
	 *            flight code
	 * @return both keys and values
	 */
	// Treemap method to checkin status and flightcode using associated Booking
	// reference number
	// as the Key
	public String getPassengerStatusfromBookingRefNum() {
		TreeMap<String, String> PassengerStatus = new TreeMap<String, String>();
		for (Passenger p : PassengerSet) {
			PassengerStatus.put(p.getBookingReferenceNum(), p.getCheckInStatus() + " in flight " + p.getflightCode());
		}
		for (Map.Entry<String, String> entry : PassengerStatus.entrySet()) {
			return (entry.getKey() + entry.getValue());
		}
		return null;
	}

	/**
	 * Returns a list of passengers by iterating through the PassengerSet
	 * 
	 * @param PassengerSet
	 * @return report of Passengers
	 * 
	 */
	// this method may not be needed
	public String getAllPassengers() {
	
		String report = "";
		for (Passenger p : PassengerSet) {
			report += String.format("%-30s", p.getFullName() + " " + p.getCheckInStatus());
			report += "\n";
		}
		
		return report;
		
	}
	
	public HashMap createAMap() {
		HashMap<String,String> flyers=new  HashMap<String,String>();

		for (Passenger p:PassengerSet ) {
			flyers.put(p.getBookingReferenceNum(), p.getLastName());
		}
		return flyers;
	}
	
	public HashMap createAMap2() {
		HashMap<String,Passenger> flyers=new  HashMap<String,Passenger>();

		for (Passenger p:PassengerSet ) {
			flyers.put( p.getBookingReferenceNum(),p);
		}
		return flyers;
	}
	
	
	//Trial passengers that will be used  to populate our poolset
	
	
	public PassengerSet  populate() {
		PassengerSet p= new PassengerSet();
		p.readFile("passengers");
		return p;
	
}
	
	
public void removeOb(Passenger p) {
	PassengerSet.remove(p);
}
	
//Does it contain a person

public Boolean cont(Passenger pes) {
PassengerSet.contains(pes);
return true;
}
	
	
	}
	

