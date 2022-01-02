package test;

import dataframe.TXT;
import dataframe.TXTFactory;
import dataframe.composite.Directory;
import dataframe.visitors.CompositeSumVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class VisitorSumTest {

	Directory dir1;
	TXTFactory txtFact;
	
	public void init() {
		txtFact= new TXTFactory();
	}
	
	public void init2() {
		dir1 = new Directory("root");
		dir1.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir1.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir1.addChild(txtFact.createDataFrame("testfiles/test2.txt"));
	}
	
	public void init3() {
		Directory dir2 = new Directory("childDirectory");
		dir2.addChild(txtFact.createDataFrame("testfiles/test1.txt"));
		dir2.addChild(txtFact.createDataFrame("testfiles/test2.txt"));
		dir1.addChild(dir2);
	}
	
	@Test
	void testSumDataframeInteger() {
		init();
		TXT txtDataframe = (TXT) txtFact.createDataFrame("testfiles/test1.txt");
		CompositeSumVisitor sumVisitor = new CompositeSumVisitor("Column1");
		sumVisitor.visit(txtDataframe);
		Assertions.assertEquals(60, sumVisitor.getSum().getValue());
	}
	
	@Test
	void testSumSimpleCompositeInteger() {
		init();
		init2();
		CompositeSumVisitor sumVisitor = new CompositeSumVisitor("Column1");
		sumVisitor.visit(dir1);
		Assertions.assertEquals(60+60+5, sumVisitor.getSum().getValue());
	}
	
	@Test
	void testSumMultipleCompositeInteger() {
		init();
		init2();
		init3();
		CompositeSumVisitor sumVisitor = new CompositeSumVisitor("Column1");
		sumVisitor.visit(dir1);
		Assertions.assertEquals(60+60+5+60+5, sumVisitor.getSum().getValue());
	}
	
	@Test
	void testSumDataframeDouble() {
		init();
		TXT txtDataframe = (TXT) txtFact.createDataFrame("testfiles/test1.txt");
		CompositeSumVisitor sumVisitor = new CompositeSumVisitor("Column4");
		sumVisitor.visit(txtDataframe);
		Assertions.assertEquals(10180.8, sumVisitor.getSum().getValue());
	}

	@Test
	void testDifferentLabels() {
		init();
		init2();
		init3();
		CompositeSumVisitor sumVisitor = new CompositeSumVisitor("Column1");
		dir1.addChild(txtFact.createDataFrame("testfiles/test3.txt"));	//different labels
		/* If getTable() is null (different labels), the dataframe is ignored */
		sumVisitor.visit(dir1);
		Assertions.assertEquals(60+60+5+60+5, sumVisitor.getSum().getValue());
	}
}
