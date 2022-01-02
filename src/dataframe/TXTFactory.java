package dataframe;

/**
 * Txt dataframe factory, can be used to instantiate TXT class dataframes
 */
public class TXTFactory implements IDataFrameFactory{
	/**
	 * Creates a new dataframe from .json file
	 * @param path input file location
	 * @return JSON dataframe
	 */
	@Override
	public IDataFrame createDataFrame(String path) {
		return new TXT(path);
	}
}
