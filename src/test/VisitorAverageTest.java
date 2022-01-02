package test;

import dataframe.TXT;
import dataframe.TXTFactory;
import dataframe.composite.Directory;
import dataframe.visitors.CompositeAverageVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VisitorAverageTest {

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
	void testAverageDataframe() {
		init();
		TXT txtDataframe = (TXT) txtFact.createDataFrame("testfiles/test1.txt");
		CompositeAverageVisitor avVisitor = new CompositeAverageVisitor("Column1");
		avVisitor.visit(txtDataframe);
		Assertions.assertEquals(15.0, avVisitor.getAverage().getValue());
	}
	
	@Test
	void testAverageMultipleComposite() {
		init();
		init2();
		init3();
		CompositeAverageVisitor avVisitor = new CompositeAverageVisitor("Column1");
		avVisitor.visit(dir1);
		Assertions.assertEquals(9.5, avVisitor.getAverage().getValue());
	}

	@Test
	void testDifferentLabels(){
		init();
		init2();
		init3();
		dir1.addChild(txtFact.createDataFrame("testfiles/test3.txt"));	//different labels
		/* If getTable() is null (different labels), the dataframe is ignored */
		CompositeAverageVisitor avVisitor = new CompositeAverageVisitor("Column1");
		avVisitor.visit(dir1);
		Assertions.assertEquals(9.5, avVisitor.getAverage().getValue());
	}




}
