package practices.filereader;

import java.io.FileNotFoundException;

import practices.pool.ResourcePathGetter;

public interface FileReader extends ResourcePathGetter {
	public StringBuilder getFileContent(String resourceFilename) throws NullPointerException, FileNotFoundException;
}
