package practices.files;

import java.nio.file.Path;

public interface DataPathGetter {
    Path getDataPath(String dataFilename) throws NullPointerException;
}
