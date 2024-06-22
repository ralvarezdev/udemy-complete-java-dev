package practices.pool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import practices.MissingPropertyException;

public class DefaultPropertiesWriter extends DefaultResourcePathGetter implements PropertiesWriter {
	public DefaultPropertiesWriter() {
		super();
	}

	public void setProperties(Properties props, Map<String, String> fields) throws NullPointerException {
		checkProperties(props);

		for (String key : fields.keySet())
			if (key != null) {
				String value = fields.get(key);

				if (value != null)
					props.setProperty(key, value);
			}
	}

	public void setProperties(String resourceFilename, Map<String, String> fields, String comment)
			throws NullPointerException, FileNotFoundException, MissingPropertyException {
		String resourcePath = getResourcePath(resourceFilename);
		Properties props = null;

		try {
			props = new Properties();
			props.load(new FileInputStream(resourcePath));

			setProperties(props, fields);

		} catch (IOException e) {
			throw new MissingPropertyException("Properties file couldn't be loaded.");
		}

		writeProperties(props, resourcePath, comment);
	}

	public void writeProperties(Properties props, String resourcePath, String comment)
			throws NullPointerException, MissingPropertyException {
		checkProperties(props);

		try {
			props.store(new FileWriter(resourcePath), comment);

		} catch (IOException e) {
			throw new MissingPropertyException("Properties file couldn't be loaded.");
		}
	}
}
