package dataframe.visitors;

import java.util.List;

import dataframe.IDataFrame;
import dataframe.composite.Directory;
import dataframe.composite.IComponent;
import table.Table;
import table.element.TableElement;


/**
 * Class that calculates the average value of a column in a composite dataframe using the visitor pattern
 */
public class CompositeAverageVisitor implements IVisitor {
	
	TableElement sum;
	String label;
	int n;
	
	/**
	 * Constructor
	 * @param label column name
	 */
	public CompositeAverageVisitor(String label){
		this.label = label;
		n = 0;
	}
	
	/**
	 * @return average value of the column
	 */
	public TableElement getAverage() {
		return sum.divide(n);
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
		n = n + tab.size();
	}

}
