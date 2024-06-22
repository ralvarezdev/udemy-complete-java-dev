package practices.filereader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import practices.pool.DefaultResourcePathGetter;

public class DefaultFileWriter extends DefaultResourcePathGetter implements practices.filereader.FileWriter {
	public DefaultFileWriter() {
		super();
	}

	private boolean isContentEmpty(String content) {
		return content == null || content.isBlank();
	}

	public void writeFileContent(String resourceFilename, String content, boolean append)
			throws NullPointerException, FileNotFoundException {
		if (isContentEmpty(content))
			return;

		String resourcePath = getResourcePath(resourceFilename);

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
