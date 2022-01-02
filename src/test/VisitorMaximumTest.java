package test;

import dataframe.TXT;
import dataframe.TXTFactory;
import dataframe.composite.Directory;
import dataframe.visitors.CompositeMaximumVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VisitorMaximumTest {

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
	void testMaxDataframe() {
		init();
		TXT txtDataframe = (TXT) txtFact.createDataFrame("testfiles/test1.txt");
		CompositeMaximumVisitor maxVisitor = new CompositeMaximumVisitor("Column1");
		maxVisitor.visit(txtDataframe);
		Assertions.assertEquals(50, maxVisitor.getMax().getValue());
	}
	
	@Test
	void testMaxSimpleComposite() {
		init();
		init2();
		CompositeMaximumVisitor maxVisitor = new CompositeMaximumVisitor("Column1");
		maxVisitor.visit(dir1);
		Assertions.assertEquals(50, maxVisitor.getMax().getValue());
	}
	
	@Test
	void testMaxMultipleComposite() {
		init();
		init2();
		init3();
		CompositeMaximumVisitor maxVisitor = new CompositeMaximumVisitor("Column1");
		maxVisitor.visit(dir1);
		Assertions.assertEquals(50, maxVisitor.getMax().getValue());
	}

	@Test
	void testDifferentLabels() {
		init();
		init2();
		init3();
		CompositeMaximumVisitor maxVisitor = new CompositeMaximumVisitor("Column1");
		dir1.addChild(txtFact.createDataFrame("testfiles/test3.txt"));	//different labels
		/* If getTable() is null (different labels), the dataframe is ignored */
		maxVisitor.visit(dir1);
		Assertions.assertEquals(50, maxVisitor.getMax().getValue());
	}


}
