package commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

	private String filePath;
	Properties prop;
	
	public PropertyUtils(String filePath) throws IOException {
		
		this.filePath = filePath;
		FileInputStream fip = new FileInputStream(filePath);
		
		prop = new Properties();
		prop.load(fip);
	}
	
	public String getPropertyvalue(String key) {
		String value = "";
		if(prop != null) {
			value = prop.getProperty(key);
			
		}
		return value;
	}
	
}
