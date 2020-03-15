

package Controller;
/**
 * Advanced Software Engineering - Coursework Stage 2 Main class to initiate the
 * application
 * MVC design pattern is used to compile the code into Model, View and Controller
 * packages
 * 
 * @author Lynsey Kirk, Kuda Mugara, Rachana Patel, Cameron Scott, Robert Stone
 */


public class TravelPigeonMain {
	
	/**
	 * The application's entry point
	 * 
	 * @param args an array of command-line arguments for the application
	 */

	public static void main(String[] args) {
		TravelPigeonManager Manager = new TravelPigeonManager();
		Manager.run();
	}

}
