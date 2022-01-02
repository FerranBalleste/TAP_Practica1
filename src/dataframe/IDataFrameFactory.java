package dataframe;

/**
 * Interface for dataframe factories (CSVFactory, JSONFactory, TXTFactory...)
 */
public interface IDataFrameFactory {
	/**
	 * Creates a new dataframe
	 * @param path input file location
	 * @return dataframe
	 */
	IDataFrame createDataFrame(String path);
}
