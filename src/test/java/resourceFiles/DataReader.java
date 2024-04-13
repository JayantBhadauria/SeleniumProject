package resourceFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<LinkedHashMap<String,String>> getJSONDataToMap() throws IOException {
	// Convert JSON to String
	String JSONData=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\resourceFiles\\StrategyDetails.json"),StandardCharsets.UTF_8);
	// Convert String to Map (Jackson databind dependency is required for this)
	ObjectMapper mapper=new ObjectMapper();
	List<LinkedHashMap<String,String>> maps=mapper.readValue(JSONData,new TypeReference<List<LinkedHashMap<String,String>>>(){} );
	return maps;
	}
	
}
