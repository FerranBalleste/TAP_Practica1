package dataframe.visitors;

import java.util.List;

import dataframe.IDataFrame;
import dataframe.composite.Directory;
import dataframe.composite.IComponent;
import table.Table;
import table.element.TableElement;

/**
 * Class that calculates the max value of a column in a composite dataframe using the visitor pattern
 */
public class CompositeMaximumVisitor implements IVisitor {

	TableElement max;
	String label;
	
	/**
	 * Constructor
	 * @param label column name
	 */
	public CompositeMaximumVisitor(String label){
		this.label = label;
	}
	
	/**
	 * @return maximum value of the column
	 */
	public TableElement getMax() {
		return max;
	}

	@Override
	public void visit(Directory directory) {
		List <IComponent> children = directory.getChildren();
		for(IComponent child : children) {
			if(child instanceof Directory)
				visit((Directory) child);
			else visit((IDataFrame) child);
		}
	}

	@Override
	public void visit(IDataFrame dataframe) {
		Table tab = dataframe.getTable();
		if(tab == null) return;
		int col = tab.columnIndex(label);
		if(col == -1) return;
		if (max == null)
			max = tab.iat(0, col);
		
		max = tab.getRowList().stream().map(e -> e.getElement(col)).reduce(max, TableElement::max);
	}
}
