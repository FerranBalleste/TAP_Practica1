package dataframe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import table.*;

/**
 * TXT dataframe class
 */
public class TXT extends Table implements IDataFrame{
	
	/**
	 * TXT dataframe constructor
	 * .txt values need to be separated with tabulator, use CSV instead in case of comma
	 * @param path input file location
	 */
	public TXT(String path) {
		try {
			File inputFile = new File(path);
			InputStream inputS = new FileInputStream(inputFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputS));
			
			//Header
			String regexp = "[\t]+";
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
