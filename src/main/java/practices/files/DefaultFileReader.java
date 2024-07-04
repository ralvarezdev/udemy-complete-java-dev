package practices.files;

import java.io.*;

public class DefaultFileReader implements FileReader {

    public void checkFilePath(String filePath) throws NullPointerException {
        if (filePath == null)
            throw new NullPointerException("File path is null.");
    }

    public StringBuilder getFileContent(String filePath) throws NullPointerException, IOException {
        StringBuilder content = new StringBuilder();

        checkFilePath(filePath);
        File file = new File(filePath);

        try (FileInputStream inputStream = new FileInputStream(file)) {

            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {

                try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                    String line;

                    while ((line = reader.readLine()) != null)
                        content.append(line).append("\n");

                }
            }

        }

        return content;
    }
}
