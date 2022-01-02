package dataframe.composite;

import java.util.Comparator;
import java.util.function.Predicate;

import dataframe.visitors.IVisitor;
import table.Table;
import table.element.TableElement;


/**
 * Component interface used to implement a Composite pattern.
 * Interface used by dataframe.composite.Directory and dataframe.IDataFrame.
 * Directories can have dataframes or subdirectories inside.
 */
public interface IComponent {
	
	/**
	 * Returns the value of a single item by row and columName from a Composite
	 * @param id row (from 0 to the sum of all component sizes -1)
	 * @param label columnName
	 * @return TableElement on that position, if not found returns null
	 */
    TableElement at(int id, String label);
    
	/**
	 * Returns the value of a single item by row and column index from a Composite
	 * @param row row index (from 0 to the sum of all component sizes -1)
	 * @param col column index(from 0 to columns()-1)
	 * @return TableElement on that position, if not found returns null
	 */
    TableElement iat(int row, int col);
    
    /**
	 * Number of columns (number of labels)
	 * @return number of columns
     */
    int columns();
    
    /**
	 * Number of elements (number of rows without counting the labels)
	 * @return number of elements
     */
    int size();
    
    /**
     * Sorts the composite by one column and specific criteria,
     * returns a single ordered table.
     * TableElementComparator inside the package table.element can be used as comp parameter.
     * Label names of each component need to be equal and ordered.
     * @param label column name
     * @param comparator Comparator that implements a TableElement Comparator
     * @return Sorted table
     */
    Table sort(String label, Comparator<TableElement> comparator);
    
	/**
	 * Filters the table elements by specific criteria.
	 * Predicates can be found inside table.element.TableElement class
	 * @param label column name
	 * @param p predicate that will be used to filter the table
	 * @return New filtered table
	 */
    Table query(String label, Predicate<TableElement> p);
    
    /**
     * Creates a single Table with the same labels and data as this composite
     * The labels of each child need to be identical and have the same order, 
     * else it returns null
     * @return Table with the same labels and data as this composite
     */
    Table getTable();
    
	/**
	 * Method needed to implement a visitor pattern
	 * @param visitor IVisitor element
	 */
	void accept(IVisitor visitor);
	
}
