import java.lang.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TPUTest {

	@Test
	public void testgetFullName() {
		 String expected1 = "Robert William Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getFullName();
		 assertEquals(expected1, actual1);
		 
		 String expected2 = "Robert Stone";
		 Name name2 = new Name("Robert","","Stone");
	     String actual2 = name2.getFullName();
		 assertEquals(expected2, actual2);
		 
	}

	@Test
	public void testgetLastName() {
		 String expected1 = "Stone";
		 Name name1 = new Name("Robert","William","Stone");
	     String actual1 = name1.getLastName();
		 assertEquals(expected1, actual1);
	
	}
}
