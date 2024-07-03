package practices.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DefaultResourcePathGetter implements ResourcePathGetter {
    protected final Class<?> CLASS;

    public DefaultResourcePathGetter(Class<?> classObject) {
        CLASS = classObject;
    }

    protected void checkResourceFilename(String resourceFilename) throws NullPointerException {
        if (resourceFilename == null)
            throw new NullPointerException("Resource filename is null.");
    }

    protected void checkProperties(Properties props) throws NullPointerException {
        if (props == null)
            throw new NullPointerException("Properties instance is null...");
    }

    public String getResourcePath(String resourceFilename) throws NullPointerException, IOException {
        checkResourceFilename(resourceFilename);

        URL resource = CLASS.getResource(resourceFilename);

        if (resource == null)
            throw new FileNotFoundException("Missing %s file.".formatted(resourceFilename));

        return resource.getPath();
    }
}
