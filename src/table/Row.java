package table;

import java.util.*;

import table.element.DoubleElement;
import table.element.IntegerElement;
import table.element.StringElement;
import table.element.TableElement;

/**
 * Row class used by table.Table.
 * Each row represents an element of the dataframe.
 * Each element needs to be from a class that extends table.element.TableElement
 */
public class Row {
	List<TableElement> rowData = new LinkedList<>();
	
	/**
	 * Empty constructor
	 */
	public Row(){}
	
	/**
	 * Constructor
	 * @param elemList List of elements that conforms the row
	 */
	public Row(List<TableElement> elemList) {
		rowData = elemList;
	}
	
	/**
	 * Automatic constructor by string array.
	 * Detects the TableElement type that will be added:
	 * -if the string contains ", it's a StringElement
	 * -if the string matches "-?\\d+", it will be a IntegerElement
	 * -if the string matches "-?\\d+.\\d+", it will be a DoubleElement
	 * -else it will be a string
	 * @param elements string array
	 */
	public Row(String[] elements) {
		for(String elem: elements) {
			if(elem.contains("\""))
				rowData.add(new StringElement(elem.replace("\"", "").trim()));
			else if(elem.matches("-?\\d+"))
				rowData.add(new IntegerElement(Integer.parseInt(elem)));
			else if(elem.matches("-?\\d+.\\d+"))
				rowData.add(new DoubleElement(Double.parseDouble(elem)));
			else
				rowData.add(new StringElement(elem.trim()));
		}
		//-?     --> negative sign, could have none or one
		//\\d+   --> one or more digits
	}
	
	/**
	 * Adds an element to the row
	 * @param e TableElement that will be added
	 */
	public void addElement(TableElement e) {
		rowData.add(e);
	}
	
	/**
	 * Gets an element from the row by its index
	 * @param index (between 0 and size()-1)
	 * @return TableElement or null
	 */
	public TableElement getElement(int index) {
		if(index >= 0 && index < rowData.size())
			return rowData.get(index);
		return null;
	}
	
	public String toString() {
		return rowData.toString();
	}
}
