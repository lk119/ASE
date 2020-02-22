import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


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
	 * Method to allow addition of passenger objects to the set of
	 * passengers
	 * 
	 * @param p the passenger object
	 */
	public void add(Passenger p) {
		PassengerSet.add(p);
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
	 */
	
	private void processLine(String line) {
		try {
			String parts[] = line.split(",");
			    Name passengerName = new Name(parts[1], parts[2], parts[3]);
				String bookingReferenceNum = parts[0];
				String flightCode = parts[4];
				String pClass = parts[5];
				String checkInStatus = parts[6];
						
			Passenger p = new Passenger(bookingReferenceNum, flightCode, passengerName, checkInStatus, pClass);
			this.add(p);
		}
		
			catch (NumberFormatException ohno) {
			String error = "Number conversion error in '" + line + "'  - " + ohno.getMessage();
			System.out.println(error);
		}   catch (ArrayIndexOutOfBoundsException ohshoot) {
			String error = "Not enough items in  : '" + line + "' index position : " + ohshoot.getMessage();
			System.out.println(error);

		}
		
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
			File f = new File("Passengers");
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
	
	public void findBookingRefNum(String bookingRefernceNum) throws NoMatchingBookingReference, InvalidBookingReference {
		for (Passenger p : PassengerSet) {
			String s= p.getBookingReferenceNum();
			if (s.length() < 0 || s.length() > 8) {
	        	throw new InvalidBookingReference ();
	        }
			else if (p.getBookingReferenceNum() == (bookingRefernceNum)) {
			}
	
		   else throw new NoMatchingBookingReference (bookingRefernceNum);
		
		return;
	}
	}
	
	
	/**
	 * Will find passenger based on last name
	 * 
	 * @param last name from the input list
	 */
	
	public void findLastName(String LastName) throws NoMatchingLastNameException {
		for (Passenger p : PassengerSet) {
			if (p.getLastName() == (LastName)) {
			}
			else throw new NoMatchingLastNameException (LastName);
			}
		return;
		}

	

	/**
	 * Will find number of passengers checked in
	 * 
	 * @param check in status from the input list
	 * @return size of the set 
	 */

	
	public String totalNumberOfPassengersCheckedIn() {
		for (Passenger p : PassengerSet) {
			if (p.getCheckInStatus()!= null) {
		}
		return  "Number of passangers checkedin:"+ PassengerSet.size();
		}
		return "";
	}
		
   
	/**
	 * Will generate ordered list of passengers by name
	 * 
	 * @param full name from the input list
	 * @return ordered list by name 
	 */
    //produce a sorted unique ordered list of passengerName
	public TreeSet<String> listByName() {
	TreeSet<String>pName = new TreeSet<String> ();
	for (Passenger p : PassengerSet) {
    pName.add(p.getFullName());
    }
    for (String name :pName) {
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
    //produce a sorted unique ordered list of booking reference number	
   public TreeSet<String> listByBookingRefernceNum() {
   TreeSet<String>pRefNum = new TreeSet<String> ();	
   for (Passenger p : PassengerSet) {	
   pRefNum.add(p.getBookingReferenceNum());
   }	
   for (String RefNum: pRefNum)	{
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
 //Treemap method to checkin status and flightcode using associated Last name
   //as the Key
   public String getPassengerStatusfromLastName () {
   TreeMap <String, String> PassengerStatus = new TreeMap <String, String> ();
   for (Passenger p: PassengerSet) {
   PassengerStatus.put(p.getLastName(), p.getCheckInStatus() + " in flight " + p.getflightCode());
   }
   for (Map.Entry<String, String> entry : PassengerStatus.entrySet() ) {
		return (entry.getKey() + entry.getValue());
	}
    return null;
    }
   
   
   /**
	 * TreeMap method to get passenger checkin status and flight code
	 * 
	 * @param key as last name and value as check-in status and flight code
	 * @return both keys and values
	 */ 
  //Treemap method to checkin status and flightcode using associated Booking reference number
   //as the Key
  public String getPassengerStatusfromBookingRefNum () {
  TreeMap <String, String> PassengerStatus = new TreeMap <String, String> ();
  for (Passenger p: PassengerSet) {
  PassengerStatus.put(p.getBookingReferenceNum(), p.getCheckInStatus() + " in flight " + p.getflightCode());
  }
  for (Map.Entry<String, String> entry : PassengerStatus.entrySet() ) {
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
   //this method may not be needed
  	public String getAllPassengers() {
		String report = "";
		for (Passenger p : PassengerSet) {
			report += String.format("%-30s", p.getFullName() + p.getCheckInStatus());
			report += "\n";
		}
		return report;
	}
  	
  	
  	public String fulldetails() {
  		
  		PassengerSet.forEach(System.out::println);
		return "";
  	}

public void addingPassanger() {
Passenger p1= new Passenger("Bn24322","BA234",new Name("Kuda","Mugara"),"checkedIn","Business");	
Passenger p2= new Passenger("Bn24323","BA274",new Name("Amos","Kewandu"),"checkedIn","First");	
Passenger p3= new Passenger("B344456","BA244",new Name("Keneddy","Kewasdu"),"checkedIn","First");	
Passenger p4= new Passenger("B344460","BA244",new Name("Kenddy","ewasdu"),"checkedIn","First");	
Passenger p5= new Passenger("B344460","BA234",new Name("Kinga","Mugara"),"checkedIn","First");	
Passenger p6= new Passenger("B344460","BA234",new Name("Rob","Rachana"),"checkedIn","First");	


this.add(p1);
this.add(p2);
this.add(p3);
this.add(p4);
this.add(p5);
this.add(p6);

}

public HashMap createAMap() {
	HashMap<String,String> flyers=new  HashMap<String,String>();

	for (Passenger p:PassengerSet ) {
		flyers.put(p.getFullName(), p.getflightCode());
	}
	return flyers;
}


	
	
}