/**
 * @author km184
 *
 */
import java.util.Comparator;

public class FlightCarrierComparator implements Comparator<Flight> {

	@Override
	public int compare(Flight o1, Flight o2) {

		return o1.getCarrier().compareToIgnoreCase(o2.getCarrier());
	}

}

