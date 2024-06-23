package practices.files;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public interface PropertiesWriter extends ResourcePathGetter {
	public void setProperties(Properties props, Map<String, String> fields) throws NullPointerException;

	public void setProperties(String resourceFilename, Map<String, String> fields, String comment)
			throws NullPointerException, IOException;

	public default void setProperty(Properties props, String fieldName, String fieldValue) throws NullPointerException {
		if (fieldName == null || fieldValue == null)
			throw new NullPointerException("Field name or field value is null...");

		HashMap<String, String> field = new HashMap<>();
		field.put(fieldName, fieldValue);

		setProperties(props, field);
	}

	public default void setProperty(String resourceFilename, String fieldName, String fieldValue, String comment)
			throws NullPointerException, IOException {
		if (fieldName == null || fieldValue == null)
			throw new NullPointerException("Field name or field value is null...");

		HashMap<String, String> field = new HashMap<>();
		field.put(fieldName, fieldValue);

		setProperties(resourceFilename, field, comment);
	}

	public void writeProperties(Properties props, String resourceFilename, String comment)
			throws NullPointerException, IOException;
}
