package dataframe.visitors;

import java.util.List;

import dataframe.IDataFrame;
import dataframe.composite.Directory;
import dataframe.composite.IComponent;
import table.Table;
import table.element.TableElement;

/**
 * Class that calculates the minimum value of a column in a composite dataframe using the visitor pattern
 */
public class CompositeMinimumVisitor implements IVisitor {

	TableElement min;
	String label;
	
	/**
	 * Constructor
	 * @param label column name
	 */
	public CompositeMinimumVisitor(String label){
		this.label = label;
	}
	
	/**
	 * @return minimum value of the column
	 */
	public TableElement getMin() {
		return min;
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
		if (min == null)
			min = tab.iat(0, col);
		
		min = tab.getRowList().stream().map(e -> e.getElement(col)).reduce(min, TableElement::min);
	}
}
