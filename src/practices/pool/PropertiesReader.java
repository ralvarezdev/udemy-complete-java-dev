package practices.pool;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import practices.MissingPropertiesFileException;
import practices.MissingPropertyException;

public interface PropertiesReader {
	public String getResourcePath(String resourceFileName) throws MissingPropertiesFileException;

	public Map<String, String> getProperties(String path, List<String> propsName)
			throws IOException, MissingPropertyException, MissingPropertiesFileException;

	public default Map<String, String> getProperties(String path, String[] propsName)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		return getProperties(path, new LinkedList<>(Arrays.asList(propsName)));
	}
}
