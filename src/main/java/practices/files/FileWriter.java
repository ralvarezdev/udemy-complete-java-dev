package practices.files;

import java.io.IOException;
import java.nio.file.Path;

public interface FileWriter {
    void writeFileContent(String filePath, String content, boolean append)
            throws NullPointerException, IOException;

    default void writeResourceFileContent(ResourcePathGetter resourcePathGetter, String resourceFilename, String content, boolean append)
            throws NullPointerException, IOException {

        String filePath = resourcePathGetter.getResourcePath("");
        String resourcePath = resourcePathGetter.getResourcePath(resourceFilename);

        writeFileContent(filePath + resourcePath, content, append);
    }

    default void writeDataFileContent(DataPathGetter dataPathGetter, String dataFilename, String content, boolean append)
            throws NullPointerException, IOException {

        Path filePath = dataPathGetter.getDataPath(dataFilename);
        writeFileContent(filePath.toString(), content, append);
    }

    default void overwriteFileContent(String filePath, String content)
            throws NullPointerException, IOException {
        writeFileContent(filePath, content, false);
    }

    default void overwriteResourceFileContent(ResourcePathGetter resourcePathGetter, String resourceFilename, String content)
            throws NullPointerException, IOException {
        writeResourceFileContent(resourcePathGetter, resourceFilename, content, false);
    }

    default void overwriteDataFileContent(DataPathGetter dataPathGetter, String resourceFilename, String content)
            throws NullPointerException, IOException {
        writeDataFileContent(dataPathGetter, resourceFilename, content, false);
    }

    default void appendFileContent(String filePath, String content)
            throws NullPointerException, IOException {
        writeFileContent(filePath, content, true);
    }

    default void appendResourceFileContent(ResourcePathGetter resourcePathGetter, String resourceFilename, String content)
            throws NullPointerException, IOException {
        writeResourceFileContent(resourcePathGetter, resourceFilename, content, true);
    }

    default void appendDataFileContent(DataPathGetter dataPathGetter, String dataFilename, String content)
            throws NullPointerException, IOException {
        writeDataFileContent(dataPathGetter, dataFilename, content, true);
    }
}
