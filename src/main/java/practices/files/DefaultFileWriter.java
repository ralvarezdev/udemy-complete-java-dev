package practices.files;

import java.io.*;
import java.io.FileWriter;

public class DefaultFileWriter implements practices.files.FileWriter {
    private boolean isContentEmpty(String content) {
        return content == null || content.isBlank();
    }

    public void writeFileContent(String filePath, String content, boolean append)
            throws NullPointerException, IOException {
        if (isContentEmpty(content))
            return;

        File writeFile = new File(filePath);

        // Create file
        if (!writeFile.exists())
            if (!writeFile.createNewFile())
                throw new FileNotFoundException("File %s not found and couldn't be created".formatted(filePath));

        try {
            try (FileWriter resourceFileWriter = new FileWriter(filePath, append)) {

                try (BufferedWriter bWriter = new BufferedWriter(resourceFileWriter)) {
                    if (append)
                        bWriter.append("\n%s".formatted(content));

                    else
                        bWriter.append(content);
                }
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
