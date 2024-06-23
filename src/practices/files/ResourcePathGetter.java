package practices.files;

import java.io.IOException;

public interface ResourcePathGetter {
	public String getResourcePath(String resourceFilename) throws NullPointerException, IOException;
}
