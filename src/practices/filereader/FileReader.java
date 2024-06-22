package practices.filereader;

import java.io.FileNotFoundException;

import practices.pool.ResourcePathGetter;

public interface FileReader extends ResourcePathGetter {
	public StringBuilder getFileContent(String filePath) throws NullPointerException, FileNotFoundException;

	public default StringBuilder getResourceFileContent(String resourceFilename)
			throws NullPointerException, FileNotFoundException {
		String resourcePath = getResourcePath(resourceFilename);
		return getFileContent(resourcePath);
	}
}
