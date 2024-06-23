package practices.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DefaultFileReader extends DefaultResourcePathGetter implements FileReader {
	public DefaultFileReader() {
		super();
	}

	public String getResourcePath(String resourceFilename) throws NullPointerException, IOException {
		if (resourceFilename == null)
			throw new NullPointerException("Resource filename is null.");

		URL resource = CONTEXT.getResource(resourceFilename);

		if (resource == null)
			throw new FileNotFoundException("Missing %s file.".formatted(resourceFilename));

		return resource.getPath();
	}

	public StringBuilder getFileContent(String filePath) throws NullPointerException, IOException {
		StringBuilder content = new StringBuilder();

		File file = new File(filePath);

		try (FileInputStream inputStream = new FileInputStream(file)) {

			try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

				try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
					String line;

					while ((line = reader.readLine()) != null)
						content.append(line).append("\n");

				} catch (IOException e) {
					throw e;
				}

			} catch (IOException e) {
				throw e;
			}

		} catch (IOException e) {
			System.err.println(e);
			return null;
		}

		return content;
	}
}
