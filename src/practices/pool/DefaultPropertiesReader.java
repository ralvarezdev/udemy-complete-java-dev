package practices.pool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import practices.MissingPropertyException;

public final class DefaultPropertiesReader extends DefaultResourcePathGetter implements PropertiesReader {
	public DefaultPropertiesReader() {
		super();
	}

	public String getProperty(String resourceFilename, String fieldName)
			throws NullPointerException, FileNotFoundException, MissingPropertyException {
		if (fieldName == null)
			new NullPointerException("Properties field name list is null.");

		String resourcePath = getResourcePath(resourceFilename);

		try {
			Properties props = new Properties();
			props.load(new FileInputStream(resourcePath));

			String fieldValue = props.getProperty(fieldName);

			if (fieldValue == null)
				throw new MissingPropertyException("Missing '%s' property.".formatted(fieldName));

			return fieldValue;

		} catch (IOException e) {
			throw new MissingPropertyException("Properties file couldn't be loaded.");
		}
	}

	public Map<String, String> getProperties(String resourceFilename, List<String> propsFieldsName)
			throws NullPointerException, FileNotFoundException, MissingPropertyException {
		if (propsFieldsName == null)
			new NullPointerException("Properties fields name list is null.");

		HashMap<String, String> propsFieldsValues = new HashMap<>();
		String resourcePath = getResourcePath(resourceFilename);

		try {
			Properties props = new Properties();
			props.load(new FileInputStream(resourcePath));

			for (String fieldName : propsFieldsName) {
				if (fieldName == null)
					continue;

				String fieldValue = props.getProperty(fieldName);

				if (fieldValue == null)
					throw new MissingPropertyException("Missing '%s' property.".formatted(fieldName));

				propsFieldsValues.put(fieldName, fieldValue);
			}

		} catch (IOException e) {
			throw new MissingPropertyException("Properties file couldn't be loaded.");
		}

		return propsFieldsValues;
	}
}
