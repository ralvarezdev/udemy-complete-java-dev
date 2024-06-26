package practices.files;

import java.io.IOException;

public interface ResourcePathGetter {
    String getResourcePath(String resourceFilename) throws NullPointerException, IOException;
}
