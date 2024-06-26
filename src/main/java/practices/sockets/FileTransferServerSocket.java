package practices.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import practices.files.DefaultFileReader;

public class FileTransferServerSocket extends ServerSocket {
    private final Map<String, String[]> FILES_CONTENT;
    private final int MAX_CHAR;

    private PrintWriter printWriter;

    public FileTransferServerSocket(DefaultFileReader fileReader, Map<String, String> filesPath, int maxChar,
                                    boolean printServerMessages, boolean printSocketMessages) throws NullPointerException, IOException {
        super(printServerMessages, printSocketMessages);

        if (filesPath == null)
            throw new NullPointerException("Files path are null...");

        if (fileReader == null)
            throw new NullPointerException("File reader is null...");

        FILES_CONTENT = new HashMap<>();
        MAX_CHAR = maxChar;

        for (String filename : filesPath.keySet()) {
            String filePath = filesPath.get(filename);

            if (filename == null || filePath == null)
                continue;

            StringBuilder content = fileReader.getFileContent(filePath);
            FILES_CONTENT.put(filename, content.toString().split("\n"));
        }

        // Set client socket handler
        setSocketHandler((Socket clientSocket, String name) -> {
            // Print messages
            boolean PRINT_MESSAGES = name != null;

            // Output-related
            OutputStream outputStream = null;
            PrintWriter printWriter = null;

            // Input-related
            InputStream inputStream = null;
            InputStreamReader streamReader = null;
            BufferedReader reader = null;

            try {
                outputStream = clientSocket.getOutputStream();
                this.printWriter = printWriter = new PrintWriter(outputStream, true);

                inputStream = clientSocket.getInputStream();
                streamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(streamReader);

                String inputLine, filename = null;
                String[] fileContent = null;
                int index = 0, lineIndex = 0;
                FileTransferServerStatus status = FileTransferServerStatus.START_CONN;

                if (PRINT_MESSAGES)
                    System.out.println("%s: Waiting for socket messages...".formatted(NAME));

                while ((inputLine = reader.readLine()) != null && status != FileTransferServerStatus.END_CONN) {

                    if (PRINT_MESSAGES)
                        System.out.println("%s: Received message from socket...".formatted(NAME));

                    FileTransferClientMessages clientResponse = FileTransferClientMessages.fromString(inputLine);

                    if (clientResponse == FileTransferClientMessages.FORCED_END)
                        status = onForcedEnd();

                    else if (clientResponse == FileTransferClientMessages.MORE) {
                        status = onMore(status, filename, fileContent, index, lineIndex);

                        if (status == FileTransferServerStatus.ONGOING_LINE)
                            lineIndex += MAX_CHAR;

                        else if (status == FileTransferServerStatus.END_LINE) {
                            index++;
                            lineIndex = 0;
                        }
                    } else if (!FILES_CONTENT.containsKey(inputLine))
                        status = onFileNotFound();

                    else {
                        filename = inputLine;
                        fileContent = FILES_CONTENT.get(filename);
                        index = 0;
                        status = onFileFound();
                    }
                }

            } catch (IOException e) {
                System.err.println(e);
            }

            if (PRINT_SOCKET_MESSAGES)
                System.out.println("%s: Closing socket...".formatted(NAME));

            try {
                reader.close();
                streamReader.close();
                inputStream.close();

                printWriter.close();
                outputStream.close();

            } catch (IOException e) {
                System.err.println(e);
            }
        });
    }

    public FileTransferServerSocket(DefaultFileReader fileReader, Map<String, String> filesPath, int maxChar)
            throws NullPointerException, IOException {
        this(fileReader, filesPath, maxChar, false, false);
    }

    public FileTransferServerSocket(DefaultFileReader fileReader, Map<String, String> filesPath)
            throws NullPointerException, IOException {
        this(fileReader, filesPath, 128);
    }

    public Thread startThread(int port) {
        Runnable runnable = () -> {
            try {
                super.start(port);

            } catch (NullPointerException | IOException e) {
                System.err.println(e);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return thread;
    }

    public Thread startThread() {
        return startThread(0);
    }

    private void send(String message) {
        printWriter.println(message);
    }

    private void send(FileTransferServerMessages f) {
        send(f.getMessage());
    }

    private FileTransferServerStatus onForcedEnd() {
        send("Closing client socket...");
        return FileTransferServerStatus.END_CONN;
    }

    private FileTransferServerStatus onMore(FileTransferServerStatus status, String filename, String[] fileContent,
                                            int index, int lineIndex) {
        if (status == FileTransferServerStatus.START_CONN || status == FileTransferServerStatus.NOT_FOUND) {
            send("Filename must be set first...");
            return FileTransferServerStatus.NOT_FOUND;
        }

        if (index >= fileContent.length) {
            send(FileTransferServerMessages.END_FILE);
            return FileTransferServerStatus.END_FILE;
        }

        if (status == FileTransferServerStatus.FOUND || status == FileTransferServerStatus.END_LINE) {
            send(FileTransferServerMessages.START_LINE);
            return FileTransferServerStatus.START_LINE;
        }

        String line = fileContent[index];

        if (lineIndex >= line.length()) {
            send(FileTransferServerMessages.END_LINE);
            return FileTransferServerStatus.END_LINE;
        }

        int substringUpperIndex = lineIndex + MAX_CHAR;

        if (substringUpperIndex < line.length())
            send(line.substring(lineIndex, substringUpperIndex));

        else
            send(line.substring(lineIndex));

        return FileTransferServerStatus.ONGOING_LINE;
    }

    private FileTransferServerStatus onFileFound() {
        send(FileTransferServerMessages.FOUND);
        return FileTransferServerStatus.FOUND;
    }

    private FileTransferServerStatus onFileNotFound() {
        send(FileTransferServerMessages.NOT_FOUND);
        return FileTransferServerStatus.NOT_FOUND;
    }
}