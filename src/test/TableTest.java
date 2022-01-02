package test;

import dataframe.CSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import table.Table;
import table.element.IntegerElement;
import table.element.StringElement;
import table.element.TableElement;
import table.element.TableElementComparator;

class TableTest {
	private CSV table;
	
	@BeforeEach
	public void init() {
		table = new CSV("testfiles/test1.csv");
		//System.out.println(table);
	}

	@Test
	void testAt() {
		Assertions.assertEquals(1, table.at(0, "Column1 Int").getValue());
		Assertions.assertEquals(2, table.at(1, "Column1 Int").getValue());
		
		Assertions.assertEquals("AAAaaaa", table.at(0, "Column5 String").getValue());
		Assertions.assertEquals("mmmmm", table.at(11, "Column5 String").getValue());
		
		Assertions.assertEquals(5.5, table.at(0, "Column2 Double").getValue());
		Assertions.assertEquals(-9.5, table.at(1, "Column2 Double").getValue());
	}
	
	@Test
	void testAt2() {
		Assertions.assertNull(table.at(-5, "Column1 Int"));
		Assertions.assertNull(table.at(500, "Column1 Int"));
		Assertions.assertNull(table.at(2, "AAAAAAAAAAAAAAAAAAAAA"));
	}

	@Test
	void testIat() {
		Assertions.assertEquals(1, table.iat(0, 0).getValue());
		Assertions.assertEquals(2, table.iat(1, 0).getValue());
		
		Assertions.assertEquals("AAAaaaa", table.iat(0, 4).getValue());
		Assertions.assertEquals("mmmmm", table.iat(11, 4).getValue());
		
		Assertions.assertEquals(5.5, table.iat(0, 1).getValue());
		Assertions.assertEquals(-9.5, table.iat(1, 1).getValue());
	}
	
	@Test
	void testIat2() {
		Assertions.assertNull(table.iat(-5, 2));
		Assertions.assertNull(table.iat(500, 2));
		Assertions.assertNull(table.iat(2, -6));
		Assertions.assertNull(table.iat(2, 999999));
	}

	@Test
	void testColumns() {
		Assertions.assertEquals(5, table.columns());
	}

	@Test
	void testSize() {
		Assertions.assertEquals(12, table.size());
	}

	@Test
	void testSortIntegerAscending() {
		TableElementComparator comp1 = new TableElementComparator(true);
		Table sortedTable = table.sort("Column1 Int", comp1);
		
		for(int i = 0; i<sortedTable.size()-1; i++)
			Assertions.assertTrue((Integer)sortedTable.at(i, "Column1 Int").getValue() <= (Integer)sortedTable.at(i+1, "Column1 Int").getValue());
		//System.out.println(sortedTable);
	}
	
	@Test
	void testSortIntegerDescending() {
		TableElementComparator comp1 = new TableElementComparator(false);
		Table sortedTable = table.sort("Column1 Int", comp1);
		
		for(int i = 0; i<sortedTable.size()-1; i++)
			Assertions.assertTrue((Integer)sortedTable.at(i, "Column1 Int").getValue() >= (Integer)sortedTable.at(i+1, "Column1 Int").getValue());
		//System.out.println(sortedTable);
	}
	
	@Test
	void testSortDoubleAscending() {
		TableElementComparator comp1 = new TableElementComparator(true);
		Table sortedTable = table.sort("Column2 Double", comp1);
		
		for(int i = 0; i<sortedTable.size()-1; i++)
			Assertions.assertTrue((Double)sortedTable.at(i, "Column2 Double").getValue() <= (Double)sortedTable.at(i+1, "Column2 Double").getValue());
		//System.out.println(sortedTable);
	}
	
	@Test
	void testSortDoubleDescending() {
		TableElementComparator comp1 = new TableElementComparator(false);
		Table sortedTable = table.sort("Column2 Double", comp1);

		for(int i = 0; i<sortedTable.size()-1; i++)
			Assertions.assertTrue((Double)sortedTable.at(i, "Column2 Double").getValue() >= (Double)sortedTable.at(i+1, "Column2 Double").getValue());
		//System.out.println(sortedTable);
	}
	
	@Test
	void testSortStringAscending() {
		TableElementComparator comp1 = new TableElementComparator(true);
		Table sortedTable = table.sort("Column5 String", comp1);
		for(int i = 0; i<sortedTable.size()-1; i++) {
			String s1 = (String) sortedTable.at(i, "Column5 String").getValue();
			String s2 = (String) sortedTable.at(i+1, "Column5 String").getValue();
			Assertions.assertTrue(s1.compareTo(s2) <= 0);
		}
		//System.out.println(sortedTable);
	}
	
	@Test
	void testSortStringDescending() {
		TableElementComparator comp1 = new TableElementComparator(false);
		Table sortedTable = table.sort("Column5 String", comp1);
		for(int i = 0; i<sortedTable.size()-1; i++) {
			String s1 = (String) sortedTable.at(i, "Column5 String").getValue();
			String s2 = (String) sortedTable.at(i+1, "Column5 String").getValue();
			Assertions.assertTrue(s1.compareTo(s2) >= 0);
		}
		//System.out.println(sortedTable);
	}

	@Test
	void testQueryIsBiggerInt() {
		Table filteredTable = table.query("Column1 Int", TableElement.isBigger(new IntegerElement(10)));
		for(int i=0; i<filteredTable.size(); i++) {
			Assertions.assertTrue((Integer)filteredTable.at(i, "Column1 Int").getValue() > 10);
		}
		//System.out.println(filteredTable);
	}
	
	@Test
	void testQueryIsBiggerOrEqualInt() {
		Table filteredTable = table.query("Column1 Int", TableElement.isBiggerOrEqual(new IntegerElement(5)));
		for(int i=0; i<filteredTable.size(); i++) {
			Assertions.assertTrue((Integer)filteredTable.at(i, "Column1 Int").getValue() >= 5);
		}
		//System.out.println(filteredTable);
	}
	
	@Test
	void testQueryIsBiggerString() {
		Table filteredTable = table.query("Column5 String", TableElement.isBigger(new StringElement("Z")));
		for(int i=0; i<filteredTable.size(); i++) {
			String s1 = (String) filteredTable.at(i, "Column5 String").getValue();
			Assertions.assertTrue(s1.compareTo("Z") > 0);
		}
		//System.out.println(filteredTable);
	}

}
