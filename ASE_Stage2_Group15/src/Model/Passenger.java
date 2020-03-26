package Model;

import java.util.Random;

/**
 * Advanced Software Engineering Assignment Stage 1 GUI to allow the user to
 * search for a passenger last name and booking reference number to enable
 * subsequent check-in procedure
 * 
 * @author Cameron Scott, Kuda Mugara, Lynsey Kirk, Rachana Patel, Robert Stone
 */

public class Passenger {
	
	private String bookingReferenceNum;
 	private String flightCode;
 	private Name passengerName;
 	private boolean checkInStatus;
 	private String pClass;
 	final int FLIGHTCAPACITY = 150;
	
	
	public Passenger(String bookingReferenceNum, String flightCode, Name passengerName, boolean checkInStatus, String pClass) throws InvalidBookingReference {
		
		if(!bookingReferenceNum.subSequence(0, 5).equals(flightCode)) {
			throw new InvalidBookingReference(
					"Booking not correct");
		}
		this.bookingReferenceNum = bookingReferenceNum;
		this.flightCode = flightCode;
		this.passengerName = passengerName;
		this.checkInStatus = checkInStatus;
		this.pClass = pClass;
		}





	public String getBookingReferenceNum() {
		return bookingReferenceNum;
	}


	public void setBookingReferenceNum(String bookingRefernceNum) {
		this.bookingReferenceNum = bookingRefernceNum;
	}


	public String getflightCode() {
		return flightCode;
	}


	public void setflightCode(String flightCode) {
		this.flightCode = flightCode;
	}


	public Name getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(Name passengerName) {
		this.passengerName = passengerName;
	}

	public boolean getCheckInStatus() {
		if (checkInStatus == true) {
		}
			return checkInStatus;
	}


	public void setCheckInStatus(boolean checkInStatus) {
		this.checkInStatus = checkInStatus;
	}

	public String getpClass() {
		
		return pClass;
	}


	public void setpClass(String pClass) {
		
		this.pClass = pClass;
	}


	public String getLastName() {
		return passengerName.getLastName();
	}


	public String getFullName() {
		
		return passengerName.getFullName();
	}

  public int getflightCapacity() {
	return FLIGHTCAPACITY;
	


  }


//create a simple Bag
public Baggage createAbag(Passenger p) {
  Random randomNumber= new Random();
  double dimension= randomNumber.nextInt(10);
  double dimensionWeight= randomNumber.nextInt(50);

	Baggage b = new Baggage(dimensionWeight, dimension, dimension, dimension, p);
	return b;
	}


@Override
public String toString() {
	return  "\n"+"passengerName=" + getLastName() + ", pClass=" + pClass;
}

}