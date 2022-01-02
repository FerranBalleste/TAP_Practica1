package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dataframe.CSV;

class DataframeCsvTest {

	@Test
	void testCSV() {
		CSV table = new CSV("testfiles/cities.csv");
		
		/*
		 * 
		 * [LatD, LatM, LatS, NS, LonD, LonM, LonS, EW, City, State]
		 * [41, 5, 59, N, 80, 39, 0, W, Youngstown, OH]
		 * ...
		 */

		//System.out.println(table);
		Assertions.assertEquals(41, table.iat(0, 0).getValue());
		Assertions.assertEquals(41, table.at(0, "LatD").getValue());

		Assertions.assertEquals("Youngstown", table.iat(0, 8).getValue());
		Assertions.assertEquals("Youngstown", table.at(0, "City").getValue());

		Assertions.assertEquals(80, table.iat(0, 4).getValue());
		Assertions.assertEquals(80, table.at(0, "LonD").getValue());

		Assertions.assertEquals(128, table.size());
		Assertions.assertEquals(10, table.columns());
		
	}

}
