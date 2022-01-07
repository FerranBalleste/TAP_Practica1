package table;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import table.element.TableElement;

/**
 * This class implements a common dataframe table that will be used by all the dataframes in this project.
 */
public class Table {
	
	protected List<String> labels = new ArrayList<>();
	protected List<Row> rowList = new ArrayList<>();

	//Constructors
	
	/**
	 * Empty constructor
	 */
	public Table() {}
	
	/**
	 * Constructor without labels
	 * @param inputRows row data
	 */
	public Table(List<Row> inputRows) {
		rowList = inputRows;
	}
	
	/**
	 * Full constructor
	 * @param labels column names
	 * @param rowList row data
	 */
	public Table(List<String> labels, List<Row> rowList) {
		this.labels = labels;
		this.rowList = rowList;
	}
	
	/**
	 * Method that combines this Table with another Table with the same labels (and same label order).
	 * 
	 * @param table2 the table that will be annexed
	 * @return the resulting combination of the two tables
	 */
	public Table addRows(Table table2) {
		if(labels == null)
			return table2;
		if(!labels.equals(table2.getLabels()))
			return null;
		rowList.addAll(table2.getRowList());
		return this;
	}
	
	/**
	 * @return current labels
	 */
	public List<String> getLabels(){
		return labels;
	}
	
	/**
	 * @return current table data
	 */
	public List<Row> getRowList(){
		return rowList;
	}

	/**
	 * Gets certain column as a list of objects
	 * @param label column name
	 * @return selected list of TableElements
	 */
	public List<TableElement> getColumnAsList(String label){
		int index = columnIndex(label);
		if(index == -1) return null;
		List<TableElement> column = new ArrayList<>();
		rowList.forEach(row -> column.add(row.getElement(index)));
		return column;
	}
	
	/**
	 * Returns the value of a single item by row and columName
	 * @param id row (from 0 to size()-1)
	 * @param label columnName
	 * @return TableElement on that position, if not found returns null
	 */
	public TableElement at(int id, String label) {
		if(id<0 || id>=rowList.size())
			return null;
		int col = columnIndex(label);
		if(col == -1) 
			return null;
		
		return rowList.get(id).getElement(col);
	}
	
	/**
	 * Returns the value of a single item by row and column position
	 * @param row row index (from 0 to size()-1)
	 * @param col column index (from 0 to columns()-1)
	 * @return TableElement on that position, if not found returns null
	 */
	public TableElement iat(int row, int col) {
		if(row<0 || row>=rowList.size() || col<0 || col>=labels.size())
			return null;
		return rowList.get(row).getElement(col);
	}
	
	/**
	 * Number of columns (number of labels)
	 * @return number of columns
	 */
	public int columns() {
		return labels.size();
	}
	
	/**
	 * Number of elements (number of rows without counting the labels)
	 * @return number of elements
	 */
	public int size() {
		return rowList.size();
	}
	
	/**
	 * Searches column index by column name
	 * @param label column name
	 * @return corresponding column index, if not found returns -1
	 */
	public int columnIndex(String label) {
		for(int i=0; i<labels.size(); i++) 
			if(label.equalsIgnoreCase(labels.get(i)))
				return i;
		return -1;
	}
	
	/**
	 * Sorts the table by one column and a specific criteria.
	 * TableElementComparator inside the package table.element can be used as comp parameter.
	 * @param label column name
	 * @param comp Comparator that implements a TableElement comparator
	 * @return New Sorted table
	 */
	public Table sort(String label, Comparator<TableElement> comp) {
		int col = columnIndex(label);
		if(col == -1)return null;
		
		RowComparator comp2 = new RowComparator(comp, col);
		List<Row> rowList2 = rowList.stream().sorted(comp2).collect(Collectors.toList());
		return new Table(labels, rowList2);
	}

	/**
	 * Filters the table elements by specific criteria.
	 * Predicates can be found inside table.element.TableElement class
	 * @param label column name
	 * @param p predicate that will be used to filter the table
	 * @return New filtered table
	 */
	public Table query(String label, Predicate<TableElement> p) {
		int col = columnIndex(label);
		if(col == -1)return null;
		
		RowPredicate pred = new RowPredicate(p, col);
		List<Row> rowList2 = rowList.stream().filter(pred).collect(Collectors.toList());
		return new Table(labels, rowList2);
	}

	/**
	 * Perform reduce operation on a column
	 * @param label Column Name
	 * @param func TableElement function
	 * @return Result of reduce operation
	 */
	public TableElement mapReduce(String label, BinaryOperator<TableElement> func){
		List<TableElement> list = getColumnAsList(label);
		return list.stream().reduce(list.get(0).getZero(), func);
	}
	
	@Override
	public String toString() {
		String str = labels.toString() + "\n";
		for(Row r: rowList) {
			str = str.concat(r.toString() + "\n");
		}
		return str;
	}
	
}
