package table.element;

/**
 * IntegerElement inside the dataframe Table
 */
public class IntegerElement extends TableElement {
	private Integer value;
	
	/**
	 * Constructor
	 * @param value Integer Value
	 */
	public IntegerElement(Integer value) {
		this.value = value;
	}

	/**
	 * Getter
	 * @return Integer value
	 */
	@Override
	public Integer getValue() {
		return value;
	}

	/**
	 * Setter
	 * @param e new Integer value
	 */
	@Override
	public void setValue(TableElement e) {
		this.value = (Integer) e.getValue();
	}
	
	/**
	 * Sum operation: this + e2
	 * @param e2 second operand
	 * @return this + e2
	 */
	@Override
	public IntegerElement add(TableElement e2) {
		return new IntegerElement(value + (Integer) e2.getValue());
	}
	
	/**
	 * Divide operation: this/e2
	 * @param n divisor
	 * @return this/e2
	 */
	@Override
	public TableElement divide(int n) {
		return new DoubleElement(Double.valueOf(value)/n);
	}

	/**
	 * @return Returns a new IntegerValue with a 0 as value
	 */
	@Override
	public IntegerElement getZero() {
		return new IntegerElement(0);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int compareTo(TableElement o) {
		return value.compareTo((Integer) o.getValue());
	}

}
