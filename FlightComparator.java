package TP;
import java.util.Comparator;

/**
 * @author crs8
 *
 */
public class FlightComparator implements Comparator<Flight> {
	
	public int compare(Flight aero1, Flight aero2) {
		return aero1.getFlightCode().compareTo(aero2.getFlightCode());
	}

}
