package practices.files;

import util.OS;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultDataPathGetter implements DataPathGetter {
    private final Path CURR_PATH;
    private final Path ROOT_PATH;
    private final Path DATA_PATH;

    public DefaultDataPathGetter() throws IOException {
        Class<DefaultDataPathGetter> classObject = DefaultDataPathGetter.class;
        DefaultResourcePathGetter<DefaultDataPathGetter> resourcePathGetter = new DefaultResourcePathGetter<>(classObject);

        String resourcePath = resourcePathGetter.getResourcePath("");
        CURR_PATH = Path.of((OS.getOS() == OS.Windows) ? resourcePath.substring(3) : resourcePath);

        ROOT_PATH = CURR_PATH.getParent().getParent().getParent().getParent();
        DATA_PATH = Paths.get(ROOT_PATH.toString(), "data");
    }

    public void checkDataFilename(String dataFilename) throws NullPointerException {
        if (dataFilename == null)
            throw new NullPointerException("Data filename is null.");
    }

    public Path getDataPath(String dataFilename) throws NullPointerException {
        checkDataFilename(dataFilename);

        return Paths.get(DATA_PATH.toString(), dataFilename);
    }
}
