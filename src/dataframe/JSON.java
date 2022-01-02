package dataframe;

import org.json.CDL;
import org.json.JSONArray;
import table.Row;
import table.Table;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * JSON dataframe class
 */
public class JSON extends Table implements IDataFrame{
	
	/**
	 * JSON dataframe constructor
	 * @param path input file location
	 */
	public JSON(String path){
		try {
			String content = Files.readString(Paths.get(path));
			
			//JsonArray --> String[]
			JSONArray array = new JSONArray(content);
			String input = CDL.rowToString(array);
			input = input.substring(2, input.length() -3);
			String[] lines = input.split("}\",\"\\{");	//Separate each JsonObject into a string
			
			//labels
			//LatS:35,LatD:41,EW:W,NS:N,LonM:14,State:OH,LatM:9,City:Ravenna,LonS:23,LonD:81
			String auxLine = lines[0];
			labels = Arrays.stream(auxLine.split(","))
					.map(label -> label.split(":")[0])
					.collect(Collectors.toList());
			
			for(String line: lines) {
				String[] auxElements = Arrays.stream(line.split(","))
						.map(e -> e.split(":")[1])
						.toArray(String[]::new);
				rowList.add(new Row(auxElements));
			}
			
		} catch(Exception e) {
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