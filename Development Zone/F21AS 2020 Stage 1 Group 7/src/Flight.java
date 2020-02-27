/**
 * @author KudaMugara
 *
 */
public class Flight {
	public Flight(String destination, String carrier, String flightCode, int passangerCarryingCapacity) {
		this.destination = destination;
		this.carrier = carrier;
		this.flightCode = flightCode;
		this.passangerCarryingCapacity = passangerCarryingCapacity;
	}

	private String destination;
	private String carrier;
	private String flightCode;
	private int passangerCarryingCapacity;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public int getPassangerCarryingCapacity() {
		return passangerCarryingCapacity;
	}

	public void setPassangerCarryingCapacity(int passangerCarryingCapacity) {
		this.passangerCarryingCapacity = passangerCarryingCapacity;
	}

	@Override
	public String toString() {
		return "Flight details:" + "\n" + "Destination:" + destination + "\n" + "Airline:" + carrier + "\n" + "Number:"
				+ flightCode + "\n" + "totalBoarding" + passangerCarryingCapacity + "\n";
	}
}
