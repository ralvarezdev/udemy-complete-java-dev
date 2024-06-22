package practices.serversockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import practices.filereader.DefaultFileReader;

public class FileTransferServerSocket extends ServerSocket {
	private final Map<String, String> FILES_PATH;
	private final Map<String, String> FILES_CONTENT;
	private final int MAX_CHAR;

	public FileTransferServerSocket(DefaultFileReader fileReader, Map<String, String> filesPath, int maxChar, int port,
			boolean printServerMessages, boolean printSocketMessages) throws NullPointerException, IOException {
		super(port, printServerMessages, printSocketMessages);

		if (filesPath == null)
			throw new NullPointerException("Files path are null...");

		if (fileReader == null)
			throw new NullPointerException("File reader is null...");

		FILES_PATH = filesPath;
		FILES_CONTENT = new HashMap<>();
		MAX_CHAR = maxChar;

		for (String filename : filesPath.keySet()) {
			String filePath = filesPath.get(filename);

			if (filename == null || filePath == null)
				continue;

			StringBuilder content = fileReader.getFileContent(filePath);
			FILES_CONTENT.put(filename, content.toString());
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
				printWriter = new PrintWriter(outputStream, true);

				inputStream = clientSocket.getInputStream();
				streamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(streamReader);

				String inputLine, filename = null, fileContent = null;
				int i = 0, fileLength = 0;

				if (PRINT_MESSAGES)
					System.out.println("%s: Waiting for socket messages...".formatted(NAME));

				while ((inputLine = reader.readLine()) != null) {
					if (PRINT_MESSAGES)
						System.out.println("%s: Received message from socket...".formatted(NAME));

					if (".".equals(inputLine)) {
						printWriter.println("Closing client socket...");
						break;

					} else if ("+".equals(inputLine)) {
						if (filename == null) {
							printWriter.println("Filename must be set first...");
							continue;
						}

						if (i + MAX_CHAR < fileLength)
							printWriter.println(fileContent.substring(i, i + MAX_CHAR));

						else
							printWriter.println(fileContent.substring(i));

					} else if (FILES_CONTENT.containsKey(inputLine)) {
						filename = inputLine;
						fileContent = FILES_CONTENT.get(filename);
						i = 0;
						fileLength = fileContent.length();

					} else {
						filename = fileContent = null;
						printWriter.println("Command not recognized by server...");
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
}