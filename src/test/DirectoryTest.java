package test;

import dataframe.IDataFrame;
import dataframe.TXTFactory;
import dataframe.composite.Directory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import table.Table;

class DirectoryTest {
	Directory dir1;
	TXTFactory txtFact;
	
	@BeforeEach
	public void init() {
		txtFact= new TXTFactory();
		dir1 = new Directory("root");
		dir1.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir1.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir1.addChild(txtFact.createDataFrame("testfiles/test2.txt"));
	}
	
	public void init2() {
		Directory dir2 = new Directory("childDirectory");
		dir2.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir2.addChild(txtFact.createDataFrame("testfiles/test2.txt"));
		dir1.addChild(dir2);
	}
	
	@Test
	void testAddChild() {
		Assertions.assertEquals(3, dir1.getChildren().size());
	}

	@Test
	void testRemoveChild() {
		IDataFrame child = txtFact.createDataFrame("testfiles/test1.txt");
		dir1.addChild(child);
		Assertions.assertEquals(4, dir1.getChildren().size());
		dir1.removeChild(child);
		Assertions.assertEquals(3, dir1.getChildren().size());
	}

	@Test
	void testAt() {
		Assertions.assertEquals(1, dir1.at(0, "Column1").getValue());		//first child
		Assertions.assertEquals(5, dir1.at(1, "Column1").getValue());		//first child

		Assertions.assertEquals(1, dir1.at(4, "Column1").getValue());		//second child
		Assertions.assertEquals(50, dir1.at(7, "Column1").getValue());		//second child

		Assertions.assertEquals(1, dir1.at(8, "Column1").getValue());		//third child
		Assertions.assertEquals(-5, dir1.at(11, "Column1").getValue());		//third child
	}
	
	@Test
	void testAtNull() {
		Assertions.assertNull(dir1.at(600, "Column1"));		//null
		Assertions.assertNull(dir1.at(0, "Banana"));		//null
	}

	@Test
	void testIat() {
		Assertions.assertEquals(1, dir1.iat(0, 0).getValue());		//first child
		Assertions.assertEquals(5, dir1.iat(1, 0).getValue());		//first child

		Assertions.assertEquals(1, dir1.iat(4, 0).getValue());		//second child
		Assertions.assertEquals(50, dir1.iat(7, 0).getValue());		//second child

		Assertions.assertEquals(1, dir1.iat(8, 0).getValue());		//third child
		Assertions.assertEquals(-5, dir1.iat(11, 0).getValue());	//third child
	}
	
	@Test
	void testIatNull() {
		Assertions.assertNull(dir1.iat(600, 0));		//null
		Assertions.assertNull(dir1.iat(0, 600));		//null
	}

	@Test
	void testSize() {
		Assertions.assertEquals(12, dir1.size());
	}
	
	@Test
	void testSize2() {
		init2();
		Assertions.assertEquals(20, dir1.size());
	}

	@Test
	void testColumns() {
		Assertions.assertEquals(4, dir1.columns());
	}
	
	@Test
	void testColumns2() {
		init2();
		Assertions.assertEquals(4, dir1.columns());
	}

	@Test
	void testGetTable() {
		Table t = dir1.getTable();
		//System.out.println(t);
		Assertions.assertEquals(12, t.size());
		Assertions.assertEquals(4, t.columns());
		Assertions.assertEquals(1, t.iat(0, 0).getValue());
		Assertions.assertEquals(50, t.iat(3, 0).getValue());
		Assertions.assertEquals(-5, t.iat(11, 0).getValue());
	}
	
	@Test
	void testGetTableVariousDirectories() {
		init2();
		Table t = dir1.getTable();
		//System.out.println(t);
		Assertions.assertEquals(20, t.size());
		Assertions.assertEquals(4, t.columns());
		Assertions.assertEquals(1, t.iat(0, 0).getValue());
		Assertions.assertEquals(50, t.iat(3, 0).getValue());
		Assertions.assertEquals(-5, t.iat(11, 0).getValue());
	}
	
	@Test
	void testGetTableNull() {
		dir1.addChild(txtFact.createDataFrame("testfiles/test3.txt"));
		Table t = dir1.getTable();	//If children with different labels exists, getTable() returns null.
		Assertions.assertNull(t);
	}

	//If getTable() works, sort and query also work (see TableTest.java)
	//If getTable() is null (different labels), sort() and query() return null

}
