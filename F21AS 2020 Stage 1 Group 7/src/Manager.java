
public class Manager {
	private BaggageList allBags;

	public Manager() {
		allBags = new BaggageList();
	}

	public void run() {
		// fill the list with some students
		allBags.adding();
		System.out.println(allBags.numberOfBags());
		System.out.println(allBags.maxBaggageWeight());
		System.out.println(allBags.maxBaggagevol());

	}
}
