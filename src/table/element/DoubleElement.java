package table.element;

/**
 * DoubleElement inside the dataframe Table
 */
public class DoubleElement extends TableElement {
	private Double value;
	
	/**
	 * Constructor
	 * @param value Double value
	 */
	public DoubleElement(Double value) {
		this.value = value;
	}

	/**
	 * Getter
	 * @return Double value
	 */
	@Override
	public Double getValue() {
		return value;
	}

	/**
	 * Setter
	 * @param e new Double value
	 */
	@Override
	public void setValue(TableElement e) {
		this.value = (Double) e.getValue();
	}
	
	
	/**
	 * Sum operation: this + e2
	 * @param e2 second operand
	 * @return this + e2
	 */
	@Override
	public DoubleElement add(TableElement e2) {
		return new DoubleElement(value + (Double) e2.getValue());
	}
	
	/**
	 * Divide operation: this/e2
	 * @param n divisor
	 * @return this/e2
	 */
	@Override
	public DoubleElement divide(int n) {
		return new DoubleElement(value/n);
	}
	
	/**
	 * @return Returns a new DoubleValue with a 0.0 as value
	 */
	@Override
	public DoubleElement getZero() {
		return new DoubleElement(0.0);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int compareTo(TableElement o) {
		return value.compareTo((Double) o.getValue());
	}
	
}
