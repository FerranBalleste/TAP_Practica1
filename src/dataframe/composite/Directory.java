package dataframe.composite;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import dataframe.visitors.IVisitor;
import table.Table;
import table.element.TableElement;

/**
 * Directory class needed to implement a composite pattern.
 * Might contain several dataframes or/and other directories inside.
 * Some methods might not function if labels are not equal (sort and query)
 */
public class Directory implements IComponent {

	private String name;
	private List <IComponent> children;

	/**
	 * Directory constructor
	 * @param name name of the directory
	 */
	public Directory(String name) {
		this.name = name;
		children = new LinkedList<>();
	}
	
	/*						*/
	/* 	Directory Methods 	*/
	/*						*/
	
	/**
	 * Adds a child to the directory.
	 * It can be a dataframe or a new subdirectory.
	 * @param child IComponent that will be added
	 */
	public void addChild(IComponent child) {
		children.add(child);
	}

	/**
	 * Removes a child from the directory
	 * @param child to be removed
	 */
	public void removeChild(IComponent child){
		children.remove(child);
	}

	/**
	 * @return name of the directory
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name new directory name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return IComponents that conform directory
	 */
	public List<IComponent> getChildren() {
		return children;
	}

	/**
	 * @param children new components of the directory
	 */
	public void setChildren(List<IComponent> children) {
		this.children = children;
	}
	
	/*										*/
	/* 	Directory and DataFrame Methods 	*/
	/*										*/

	/**
	 * Returns the value of a single item by row and columName from this directory.
	 * @param id row (from 0 to the sum of all component sizes -1)
	 * @param label columnName
	 * @return TableElement on that position, if not found returns null
	 */
	@Override
	public TableElement at(int id, String label) {
		int size;
		for (IComponent child:children) {
			size = child.size();
			if(id < size) {
				return child.at(id, label);
			}
			id = id - size;
		}
		return null;
	}
	
	/**
	 * Returns the value of a single item by row and column index from a Composite
	 * @param row row index (from 0 to the sum of all component sizes -1)
	 * @param col column index(from 0 to columns()-1)
	 * @return TableElement on that position, if not found returns null
	 */
	@Override
	public TableElement iat(int row, int col) {
		int size;
		for (IComponent child:children) {
			size = child.size();
			if(row < size) {
				return child.iat(row, col);
			}
			row = row - size;
		}
		return null;
	}

	
    /**
	 * Number of elements (number of rows without counting the labels).
	 * It's the sum of number of elements of each child in the directory.
     * @return number of elements
     */
	@Override
	public int size() {
		int result = 0;
		for (IComponent child:children)
			result = result + child.size();
		return result;
	}

    /**
	 * Number of columns (number of labels) of the first child
	 * @return number of columns
     */
	@Override
	public int columns() {
		int result;
		for (IComponent child:children) {
			result = child.columns();
			if(result != -1)
				return result;
		}
		return -1;
	}

    /**
     * Sorts the directory component elements by one column and specific criteria,
     * returns a single ordered table.
     * TableElementComparator inside the package table.element can be used as comp parameter.
     * Label names of each component need to be equal and ordered, else it returns null
     * @param label column name
     * @param comparator Comparator that implements a TableElement Comparator
     * @return Sorted table
     */
	@Override
	public Table sort(String label, Comparator<TableElement> comparator) {
		Table t = getTable();
		if(t == null) return null;
		return t.sort(label, comparator);
	}

	/**
	 * Filters the directory component elements by specific criteria.
	 * Predicates can be found inside table.element.TableElement class
	 * Label names of each component need to be equal and ordered, else it returns null
	 * @param label column name
	 * @param p predicate that will be used to filter the table
	 * @return New filtered table
	 */
	@Override
	public Table query(String label, Predicate<TableElement> p) {
		Table t = getTable();
		if(t == null) return null;
		return t.query(label, p);
	}
	
	/**
	 * Combines the different dataframes inside the directory (and inside subdirectories)
	 * into a single Table.
	 */
	@Override
	public Table getTable() {
		Table auxTable = children.get(0).getTable();
		for(int i=1; i<children.size(); i++)
			auxTable = auxTable.addRows(children.get(i).getTable());
		return auxTable;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Directory: " + name + "{\n");
		for(IComponent c : children)
			result.append(c.toString());
		result.append("}");
		return result.toString();
	}
	
	/*			*/
	/*	Visitor	*/
	/*			*/

	/**
	 * Method needed to implement a visitor pattern
	 */
	@Override
	public void accept(IVisitor visitor) {
		for(IComponent component : children)
			component.accept(visitor);
		visitor.visit(this);
	}

}
