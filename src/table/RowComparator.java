package table;

import java.util.Comparator;

import table.element.TableElement;

/**
 * Class that implements Row Comparator
 * Used to sort rows inside the dataframe
 */
public class RowComparator implements Comparator<Row>{
	private final Comparator<TableElement> comp;
	private final int col;
	
	/**
	 * Constructor
	 * @param comp TableElement comparator
	 * @param col Column index
	 */
	public RowComparator(Comparator<TableElement> comp, int col) {
		this.comp = comp;
		this.col = col;
	}

	@Override
	public int compare(Row o1, Row o2) {
		return comp.compare(o1.getElement(col), o2.getElement(col));
	}

}
