package test;

import dataframe.TXT;
import dataframe.TXTFactory;
import dataframe.composite.Directory;
import dataframe.visitors.CompositeMinimumVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class VisitorMinimumTest {

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
	void testMinimumDataframeInteger() {
		init();
		TXT txtDataframe = (TXT) txtFact.createDataFrame("testfiles/test1.txt");
		CompositeMinimumVisitor minVisitor = new CompositeMinimumVisitor("Column1");
		minVisitor.visit(txtDataframe);
		Assertions.assertEquals(1, minVisitor.getMin().getValue());
	}
	
	@Test
	void testMinimumSimpleCompositeInteger() {
		init();
		init2();
		CompositeMinimumVisitor minVisitor = new CompositeMinimumVisitor("Column1");
		minVisitor.visit(dir1);
		Assertions.assertEquals(-5, minVisitor.getMin().getValue());
	}
	
	@Test
	void testMinimumMultipleCompositeInteger() {
		init();
		init2();
		init3();
		CompositeMinimumVisitor minVisitor = new CompositeMinimumVisitor("Column1");
		minVisitor.visit(dir1);
		Assertions.assertEquals(-5, minVisitor.getMin().getValue());
	}

	@Test
	void testDifferentLabels() {
		init();
		init2();
		init3();
		CompositeMinimumVisitor minVisitor = new CompositeMinimumVisitor("Column1");
		dir1.addChild(txtFact.createDataFrame("testfiles/test3.txt"));	//different labels
		/* If getTable() is null (different labels), the dataframe is ignored */
		minVisitor.visit(dir1);
		Assertions.assertEquals(-5, minVisitor.getMin().getValue());
	}

}
