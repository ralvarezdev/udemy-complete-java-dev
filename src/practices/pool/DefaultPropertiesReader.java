package practices.pool;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import practices.MissingPropertiesFileException;
import practices.MissingPropertyException;

public class DefaultPropertiesReader implements PropertiesReader {
	private final ClassLoader CONTEXT;

	public DefaultPropertiesReader() {
		this.CONTEXT = Thread.currentThread().getContextClassLoader();
	}

	public String getResourcePath(String resourceFileName) throws MissingPropertiesFileException {
		URL resource = CONTEXT.getResource(resourceFileName);

		if (resource == null)
			throw new MissingPropertiesFileException("Missing %s file.".formatted(resourceFileName));

		return resource.getPath();
	}

	public Map<String, String> getProperties(String resourceFileName, List<String> propsFieldsName)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		HashMap<String, String> propsValues = new HashMap<>();

		try {
			Properties props = new Properties();
			String resourcePath = getResourcePath(resourceFileName);
			props.load(new FileInputStream(resourcePath));

			for (String propFieldName : propsFieldsName) {
				String propValue = props.getProperty(propFieldName);
				if (propsValues == null)
					throw new MissingPropertyException("Missing some properties.");

				propsValues.put(propFieldName, propValue);
			}

		} catch (IOException | MissingPropertyException | MissingPropertiesFileException e) {
			throw e;
		}

		return propsValues;
	}
}
