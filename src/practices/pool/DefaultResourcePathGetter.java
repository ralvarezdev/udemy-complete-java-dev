package practices.pool;

import java.io.FileNotFoundException;
import java.net.URL;

public class DefaultResourcePathGetter implements ResourcePathGetter {
	protected final ClassLoader CONTEXT;

	public DefaultResourcePathGetter() {
		CONTEXT = Thread.currentThread().getContextClassLoader();
	}

	public String getResourcePath(String resourceFilename) throws NullPointerException, FileNotFoundException {
		if (resourceFilename == null)
			throw new NullPointerException("Resource filename is null.");

		URL resource = CONTEXT.getResource(resourceFilename);

		if (resource == null)
			throw new FileNotFoundException("Missing %s file.".formatted(resourceFilename));

		return resource.getPath();
	}
}
