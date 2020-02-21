
/**
 * Advanced Software Engineering Assignment Stage 1 GUI to allow the user to
 * search for a passenger last name and booking reference number to enable
 * subsequent check-in procedure
 * 
 * @author Cameron Scott, Kuda Mugara, Lynsey Kirk, Rachana Patel, Robert Stone
 */

public class Passenger {
	
	private String bookingRefernceNum;
 	private String flightCode;
 	private Name passengerName;
 	private String checkInStatus;
 	private String pClass;

	
	
	public Passenger(String bookingRefernceNum, String flightCode, Name passengerName, String checkInStatus, String pClass) {
		this.bookingRefernceNum = bookingRefernceNum;
		this.flightCode = flightCode;
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

    //check functionality of this method 
	public String getCheckInStatus() {
		if (checkInStatus == null) {
			return "Please proceed to Check-in";
		}
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


	public String getLastName() {
		return passengerName.getLastName();
	}


	public String getFullName() {
		return passengerName.getFullName();
	}


	


	 	

}