/**
 * Advanced Software Engineering Assignment Stage 1 GUI to allow the user to
 * search for a passenger last name and booking reference number to enable
 * subsequent check-in procedure
 * 
 * @author Cameron Scott, Kuda Mugara, Lynsey Kirk, Rachana Patel, Robert Stone
 */

public class Passenger {
	
	private String bookingRefernceNum;
 	private Flight flight;
 	private Name passengerName;
 	private String checkInStatus;
 	private String pClass;
	
	
	public Passenger(String bookingRefernceNum, Flight flight, Name passengerName, String checkInStatus, String pClass) {
		this.bookingRefernceNum = bookingRefernceNum;
		this.flight = flight;
		this.passengerName = passengerName;
		this.checkInStatus = checkInStatus;
		this.pClass = pClass;
		}


	public String getBookingRefernceNum() {
		return bookingRefernceNum;
	}


	public void setBookingRefernceNum(String bookingRefernceNum) {
		this.bookingRefernceNum = bookingRefernceNum;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Name getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(Name passengerName) {
		this.passengerName = passengerName;
	}


	public String getCheckInStatus() {
		return checkInStatus;
	}


	public void setCheckInStatus(String checkInStatus) {
		this.checkInStatus = checkInStatus;
	}


	public String getpClass() {
		return pClass;
	}


	public void setpClass(String pClass) {
		this.pClass = pClass;
	}


	 	

}
