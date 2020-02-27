import java.lang.*;
import static org.junit.Assert.*;

public class TPTest {

	@test
	public void testgetFullName() {
		 String expected1 = "Robert William Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getFullName();
		 assertEquals(expected1, actual1);
		 
		 String expected1 = "Robert Stone";
		 Name name1 = new Name("Robert","","Stone");
	     String actual1 = name1.getFullName();
		 assertEquals(expected1, actual1);
	
	
		 
}

	@test
	public void testgetLastName() {
		 String expected1 = "Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getLastName();
		 assertEquals(expected1, actual1);
	
}

	@test
	public void testgetwidth() {
		 String expected1 = "Stone";
		 Baggage name1 = new Baggage(22,22,22);
	     String actual1 = name1.get;
		 assertEquals(expected1, actual1);
	
}
