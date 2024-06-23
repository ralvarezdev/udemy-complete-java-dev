package practices.files;

import java.io.IOException;

public interface FileWriter extends ResourcePathGetter {
	public void writeFileContent(String resourceFilename, String content, boolean append)
			throws NullPointerException, IOException;

	public default void overwriteFileContent(String resourceFilename, String content)
			throws NullPointerException, IOException {
		writeFileContent(resourceFilename, content, false);
	}

	public default void appendFileContent(String resourceFilename, String content)
			throws NullPointerException, IOException {
		writeFileContent(resourceFilename, content, true);

	}
}
