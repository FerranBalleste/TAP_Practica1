package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import table.Row;

class TableRowTest {

	@Test
	void testRow() {
		String[] inputString = {"AAA", "5", "9.6", "\"BBB\"", "66"};
		Row result = new Row(inputString);
		

		Assertions.assertTrue(result.getElement(0).getValue() instanceof String);
		Assertions.assertTrue(result.getElement(1).getValue() instanceof Integer);
		Assertions.assertTrue(result.getElement(2).getValue() instanceof Double);
		Assertions.assertTrue(result.getElement(3).getValue() instanceof String);
		Assertions.assertTrue(result.getElement(4).getValue() instanceof Integer);
		
		Assertions.assertEquals("AAA", result.getElement(0).getValue());
		Assertions.assertEquals(5, result.getElement(1).getValue());
		Assertions.assertEquals(9.6, result.getElement(2).getValue());
		Assertions.assertEquals("BBB", result.getElement(3).getValue());
		Assertions.assertEquals(66, result.getElement(4).getValue());
	}
	
	@Test
	void testToString() {
		String[] inputString = {"AAA", "5", "9.6", "\"BBB\"", "66"};
		Row result = new Row(inputString);
		//System.out.println(result);
	    Assertions.assertFalse(result.toString().contains("@"));
	}

}
