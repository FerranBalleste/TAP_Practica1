package table.element;

import java.util.function.Predicate;

/**
 * Abstract element from the dataframe.
 * Each element that will be added to the table needs to extend this class
 */
public abstract class TableElement implements Comparable<TableElement>{

	/**
	 * @return Obect value (Integer, Double, String...)
	 */
	public abstract Object getValue();
	
	/**
	 * @param e new value
	 */
	public abstract void setValue(TableElement e);
	
	/**
	 * Sum operation: this + e2
	 * @param e2 second operand
	 * @return this + e2
	 */
	public abstract TableElement add(TableElement e2);
	
	/**
	 * Divide operation: this/e2
	 * @param n divisor
	 * @return this/e2
	 */
	public abstract TableElement divide(int n);
	
	/**
	 * Value that will be used in reduce() operations as a Zero/Neutral value.
	 * 0 in case of Integer
	 * 0.0 in case of Double
	 * "" in case of String
	 * The method is needed to avoid using instanceof.
	 * @return Zero/Neutral value of the class type
	 */
	public abstract TableElement getZero();
	
	/**
	 * Compares this value with b, returns the bigger one
	 * @param b comparison target
	 * @return max value between the two
	 */
	public TableElement max(TableElement b) {
		int comparison = this.compareTo(b);
		return (comparison > 0) ? this : b;
	}

	/**
	 * Compares this value with b, returns the smallest one
	 * @param b comparison target
	 * @return min value between the two
	 */
	public TableElement min(TableElement b) {
		int comparison = this.compareTo(b);
		return (comparison < 0) ? this : b;
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract int compareTo(TableElement o);
	
	//
	//Predicates that can be used to filter the dataframe
	//
	
	/**
	 * Predicate used to filter elements bigger than a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isBigger(TableElement e2) {
		return u -> u.compareTo(e2) > 0;
	}
	
	/**
	 * Predicate used to filter elements equals to a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isEqual(TableElement e2) {
		return u -> u.compareTo(e2) == 0;
	}
	
	/**
	 * Predicate used to filter elements smaller than a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isSmaller(TableElement e2) {
		return u -> u.compareTo(e2) < 0;
	}
	
	/**
	 * Predicate used to filter elements bigger or equal than a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isBiggerOrEqual(TableElement e2) {
		return u -> u.compareTo(e2) >= 0;
	}
	
	/**
	 * Predicate used to filter elements smaller or equal than a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isSmallerOrEqual(TableElement e2) {
		return u -> u.compareTo(e2) <= 0;
	}
	
	/**
	 * Predicate used to filter elements not equal at a certain value
	 * @param e2 comparison value
	 * @return predicate
	 */
	public static Predicate<TableElement> isNotEqual(TableElement e2) {
		return u -> u.compareTo(e2) != 0;
	}
}
