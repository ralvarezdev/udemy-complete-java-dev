package practices.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DefaultFileWriter extends DefaultResourcePathGetter implements practices.files.FileWriter {
	public DefaultFileWriter() {
		super();
	}

	private boolean isContentEmpty(String content) {
		return content == null || content.isBlank();
	}

	public void writeFileContent(String resourceFilename, String content, boolean append)
			throws NullPointerException, IOException {
		if (isContentEmpty(content))
			return;

		String resourcePath = null;
		try {
			String filePath = getResourcePath("");
			File writeFile = new File(filePath + resourceFilename);

			// Create file
			if (!writeFile.exists())
				writeFile.createNewFile();

			resourcePath = getResourcePath(resourceFilename);

		} catch (IOException e) {
			System.err.println(e);
		}

		try (FileWriter resourceFileWriter = new FileWriter(resourcePath, append)) {

			try (BufferedWriter bWriter = new BufferedWriter(resourceFileWriter)) {
				if (append)
					bWriter.append("\n%s".formatted(content));

				else
					bWriter.append(content);

			} catch (IOException e) {
				throw e;
			}

		} catch (IOException e) {
			System.err.println(e);
			return;
		}
	}
}
