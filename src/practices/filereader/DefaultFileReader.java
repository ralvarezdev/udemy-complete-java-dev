package practices.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import practices.pool.DefaultResourcePathGetter;

public class DefaultFileReader extends DefaultResourcePathGetter implements FileReader {
	public DefaultFileReader() {
		super();
	}

	public String getResourcePath(String resourceFilename) throws NullPointerException, FileNotFoundException {
		if (resourceFilename == null)
			throw new NullPointerException("Resource filename is null.");

		URL resource = CONTEXT.getResource(resourceFilename);

		if (resource == null)
			throw new FileNotFoundException("Missing %s file.".formatted(resourceFilename));

		return resource.getPath();
	}

	public StringBuilder getFileContent(String resourceFilename) throws NullPointerException, FileNotFoundException {
		String resourcePath = getResourcePath(resourceFilename);
		StringBuilder content = new StringBuilder();

		File resourceFile = new File(resourcePath);

		try (FileInputStream inputStream = new FileInputStream(resourceFile)) {

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
