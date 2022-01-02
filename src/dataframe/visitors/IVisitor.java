package dataframe.visitors;

import dataframe.IDataFrame;
import dataframe.composite.Directory;


/**
 * Interface used to implement a visitor pattern
 */
public interface IVisitor{
	
	/**
	 * @param directory that will be visited
	 */
	void visit(Directory directory);
	/**
	 * @param dataframe that will be visited
	 */
	void visit(IDataFrame dataframe);

}
