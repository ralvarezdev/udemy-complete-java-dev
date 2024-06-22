package practices.pool;

import java.io.FileNotFoundException;

public interface ResourcePathGetter {
	public String getResourcePath(String resourceFilename) throws NullPointerException, FileNotFoundException;
}
