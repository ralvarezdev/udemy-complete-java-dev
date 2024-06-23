package practices.files;

import java.io.IOException;

public interface FileReader extends ResourcePathGetter {
	public StringBuilder getFileContent(String filePath) throws NullPointerException, IOException;

	public default StringBuilder getResourceFileContent(String resourceFilename)
			throws NullPointerException, IOException {
		String resourcePath = getResourcePath(resourceFilename);
		return getFileContent(resourcePath);
	}
}
