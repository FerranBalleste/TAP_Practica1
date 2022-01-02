package dataframe;

/**
 * Json dataframe factory, can be used to instantiate JSON class dataframes
 */
public class JSONFactory implements IDataFrameFactory{
	/**
	 * Creates a new dataframe from .json file
	 * @param path input file location
	 * @return JSON dataframe
	 */
	@Override
	public IDataFrame createDataFrame(String path) {
		return new JSON(path);
	}
}
