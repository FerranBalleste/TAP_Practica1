package dataframe;

/**
 * CSV dataframe factory, can be used to instantiate CSV class dataframes
 */
public class CSVFactory implements IDataFrameFactory{
	/**
	 * Creates a new dataframe from .csv file
	 * @param path input file location
	 * @return CSV dataframe
	 */
	@Override
	public IDataFrame createDataFrame(String path) {
		return new CSV(path);
	}
	
}
