package practices.files;

import util.OS;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultDataPathGetter implements DataPathGetter {
    private final Path DATA_PATH;

    public DefaultDataPathGetter() throws IOException {
        DefaultResourcePathGetter resourcePathGetter = new DefaultResourcePathGetter(DefaultDataPathGetter.class);

        String resourcePath = resourcePathGetter.getResourcePath("");
        Path CURR_PATH = Path.of((OS.getOS() == OS.Windows) ? resourcePath.substring(3) : resourcePath);

        Path ROOT_PATH = CURR_PATH.getParent().getParent().getParent().getParent();
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
