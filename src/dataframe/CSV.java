package dataframe;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import table.*;

/**
 * CSV dataframe class
 */
public class CSV extends Table implements IDataFrame{

	/**
	 * CSV dataframe constructor
	 * @param path input file location
	 */
	public CSV(String path) {
		try {
			File inputFile = new File(path);
			InputStream inputS = new FileInputStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputS));
			
			//Header
			String regexp = ",[ ]*";
			labels = Arrays.asList(br.readLine().replace("\"", "").split(regexp));
			
			//Data
			rowList = br.lines().map(String::trim)
					.map(line -> line.split(regexp))
					.map(Row::new).collect(Collectors.toList());
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
    /**
     * @return Table with the same labels and data as this dataframe
     */
    public Table getTable() {
    	return new Table(labels, rowList);
    }
}
