package practices.filereader;

import java.io.FileNotFoundException;

import practices.pool.ResourcePathGetter;

public interface FileWriter extends ResourcePathGetter {
	public void writeFileContent(String resourceFilename, String content, boolean append)
			throws NullPointerException, FileNotFoundException;

	public default void overwriteFileContent(String resourceFilename, String content)
			throws NullPointerException, FileNotFoundException {
		writeFileContent(resourceFilename, content, false);
	}

	public default void appendFileContent(String resourceFilename, String content)
			throws NullPointerException, FileNotFoundException {
		writeFileContent(resourceFilename, content, true);

	}
}
