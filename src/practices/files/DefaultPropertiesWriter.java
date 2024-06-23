package practices.files;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

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
			throws NullPointerException, IOException {
		String resourcePath = getResourcePath(resourceFilename);
		Properties props = null;

		props = new Properties();
		props.load(new FileInputStream(resourcePath));

		setProperties(props, fields);

		writeProperties(props, resourcePath, comment);
	}

	public void writeProperties(Properties props, String resourcePath, String comment)
			throws NullPointerException, IOException {
		checkProperties(props);

		props.store(new FileWriter(resourcePath), comment);
	}
}
