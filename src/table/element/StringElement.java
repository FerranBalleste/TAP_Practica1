package table.element;

/**
 * String element inside the dataframe table
 */
public class StringElement extends TableElement {
	private String value;
	
	/**
	 * Constructor
	 * @param value String value
	 */
	public StringElement(String value) {
		this.value = value;
	}

	/**
	 * @return String Value
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * @param e new String
	 */
	@Override
	public void setValue(TableElement e) {
		this.value = (String) e.getValue();
	}
	
	/**
	 * Sum operation: this + e2.
	 * It concatenates the two strings
	 * @param e2 second operand
	 * @return this + e2
	 */
	@Override
	public StringElement add(TableElement e2) {
		return new StringElement(value + e2.getValue());
	}
	
	/**
	 * Divide operation cannot be done between two strings
	 */
	@Override
	public StringElement divide(int n) {
		return new StringElement("Performing a division by an Integer on a String makes no sense.");
	}
	
	/**
	 * @return Returns an empty string
	 */
	@Override
	public StringElement getZero() {
		return new StringElement("");
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int compareTo(TableElement o) {
		return value.compareTo((String) o.getValue());
	}
	
}
