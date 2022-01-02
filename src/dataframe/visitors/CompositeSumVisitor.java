package dataframe.visitors;

import java.util.List;

import dataframe.IDataFrame;
import dataframe.composite.Directory;
import dataframe.composite.IComponent;
import table.element.*;
import table.Table;

/**
 * Class that calculates the total sum of a column in a composite dataframe using the visitor pattern
 */
public class CompositeSumVisitor implements IVisitor {
	
	TableElement sum;
	String label;
	
	/**
	 * Constructor
	 * @param label column name
	 */
	public CompositeSumVisitor(String label){
		this.label = label;
	}
	
	/**
	 * @return sum of all values of the column
	 */
	public TableElement getSum() {
		return sum;
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
		if (sum == null)
			sum = tab.iat(0, col).getZero();
		
		sum = tab.getRowList().stream().map(e -> e.getElement(col)).reduce(sum, TableElement::add);
	}
}
