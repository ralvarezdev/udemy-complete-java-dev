package practices.pool;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import practices.MissingPropertyException;

public interface PropertiesReader {
	public String getResourcePath(String resourceFileName) throws NullPointerException, MissingPropertyException;

	public String getProperty(String resourceFilename, String fieldName)
			throws NullPointerException, MissingPropertyException;

	public Map<String, String> getProperties(String resourceFilename, List<String> propsName)
			throws NullPointerException, MissingPropertyException;

	public default Map<String, String> getProperties(String resourceFilename, String... propsName)
			throws NullPointerException, MissingPropertyException {
		return getProperties(resourceFilename, Arrays.asList(propsName));
	}
}
