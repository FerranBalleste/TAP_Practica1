package table;

import java.util.function.Predicate;

import table.element.TableElement;

/**
 * Class that implements Row Predicate
 * Used to filter rows inside a dataframe
 */
public class RowPredicate implements Predicate<Row>{
	private final Predicate<TableElement> pred;
	private final int col;
	
	/**
	 * Constructor
	 * @param predicate Predicate that will be used to filter
	 * @param col Column Index
	 */
	public RowPredicate(Predicate<TableElement> predicate, int col) {
		this.pred = predicate;
		this.col = col;
	}
	
	@Override
	public boolean test(Row t) {
		return pred.test(t.getElement(col));
	}

}
