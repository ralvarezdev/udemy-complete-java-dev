package practices.files;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {
    StringBuilder getFileContent(String filePath) throws NullPointerException, IOException;

    default StringBuilder getFileContent(Path filePath) throws NullPointerException, IOException {
        return getFileContent(filePath.toString());
    }

    default StringBuilder getResourceFileContent(ResourcePathGetter resourcePathGetter, String resourceFilename)
            throws NullPointerException, IOException {
        String resourcePath = resourcePathGetter.getResourcePath(resourceFilename);
        return getFileContent(resourcePath);
    }

    default StringBuilder getDataFileContent(DataPathGetter dataPathGetter, String dataFilename)
            throws NullPointerException, IOException {
        Path dataPath = dataPathGetter.getDataPath(dataFilename);
        return getFileContent(dataPath);
    }
}
