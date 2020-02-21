
public class Manager {
	private BaggageList allBags;
	private PassengerSet allPass;

	public Manager() {
		allBags = new BaggageList();
		allPass = new PassengerSet();
	}

	public void run() {
		// fill the list with some students
		allBags.adding();
		allPass.addingPassanger();
		System.out.println(allBags.numberOfBags());
		System.out.println(allBags.maxBaggageWeight());
		System.out.println(allBags.maxBaggagevol());
		System.out.println(allPass.getAllPassengers());
		System.out.println(allPass.findLastName("Muagara"));
		System.out.println(allPass.listByName());

	}
}
