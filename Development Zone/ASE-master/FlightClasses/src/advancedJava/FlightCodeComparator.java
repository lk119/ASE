package advancedJava;

/**
 * @author km184
 *
 */
import java.util.Comparator;

public class FlightCodeComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {

		return o1.getFlightCode().compareToIgnoreCase(o2.getFlightCode());
	}

}
