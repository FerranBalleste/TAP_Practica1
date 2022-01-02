package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dataframe.JSON;

class DataframeJsonTest {

	@Test
	void testJson() {
		JSON table = new JSON("testfiles/cities.json");
		
		/*
		 * 
		 * [LatD, LatM, LatS, NS, LonD, LonM, LonS, EW, City, State]
		 * [41, 5, 59, N, 80, 39, 0, W, Youngstown, OH]
		 * ...
		 */

		//System.out.println(table);
		//Table created with a json file does not maintain original column order, test were made with .at()
		Assertions.assertEquals(41, table.at(0, "LatD").getValue());
		Assertions.assertEquals("Youngstown", table.at(0, "City").getValue());
		Assertions.assertEquals(80, table.at(0, "LonD").getValue());
		Assertions.assertEquals(128, table.size());
		Assertions.assertEquals(10, table.columns());
		
	}

}
