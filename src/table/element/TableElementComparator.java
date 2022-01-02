package table.element;

import java.util.Comparator;

/**
 * Class that implements TableElement comparator
 * Used to compare two TableElement instances and sort them.
 */
public class TableElementComparator implements Comparator<TableElement>{
	boolean ascending;
	
	/**
	 * Constructor
	 * @param ascending Ascending or descending order (true or false)
	 */
	public TableElementComparator(boolean ascending) {
		this.ascending = ascending;
	}
	
	@Override
	public int compare(TableElement o1, TableElement o2) {
		int result = o1.compareTo(o2);
		return (ascending) ? result : -result;
	}
}
