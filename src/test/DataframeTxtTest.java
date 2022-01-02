package test;

import dataframe.TXT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import table.element.IntegerElement;
import table.element.TableElement;

class DataframeTxtTest {

	@Test
	void testTXT() {
		TXT table = new TXT("testfiles/test1.txt");
		/*
		 * Column1	Column2	Column3	Column4
			1	A	"Monday"	16.5
			5	Z	Tuesday	88.9
			4	E	"Saturday"	76.1
			-5	"5"	"Tomorrow Land"	9999.3
		 */
		//System.out.println(table);
		TableElement elem1 = table.iat(0, 0);
		Assertions.assertNotEquals(elem1, new IntegerElement(1));
		Assertions.assertEquals(elem1.getValue(), 1);				//Tests Content
	}

}
