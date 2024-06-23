package practice.sockets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
	private final Map<String, String[]> FILES_CONTENT;
	private final int MAX_CHAR;

	private PrintWriter printWriter;

	public FileTransferServerSocket(DefaultFileReader fileReader, Map<String, String> filesPath, int maxChar,
			boolean printServerMessages, boolean printSocketMessages)
			throws NullPointerException, FileNotFoundException {
		super(printServerMessages, printSocketMessages);

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
				printWriter = new PrintWriter(outputStream, true);
				this.printWriter = printWriter;

				inputStream = clientSocket.getInputStream();
				streamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(streamReader);

				String inputLine, filename = null;
				String[] fileContent = null;
				String line = null;
				int index = 0;

				if (PRINT_MESSAGES)
					System.out.println("%s: Waiting for socket messages...".formatted(NAME));

				while ((inputLine = reader.readLine()) != null) {

					if (PRINT_MESSAGES)
						System.out.println("%s: Received message from socket...".formatted(NAME));

					FileTransferClient clientResponse = FileTransferClient.fromString(inputLine);
					String message = clientResponse.getMessage();

					if (message == FileTransferClient.FORCED_END.getMessage()) {
						send("Closing client socket...");
						break;

					} else if (message == FileTransferClient.MORE.getMessage()) {
						if (filename == null)
							send("Filename must be set first...");

						if (index >= fileContent.length || filename == null) {
							send(FileTransferServer.FINISHED);
							continue;
						}

						line = fileContent[index++];

						send(FileTransferServer.START_LINE);
						for (int lineIndex = 0; lineIndex < line.length(); lineIndex += MAX_CHAR)
							if (lineIndex + MAX_CHAR < line.length())
								send(line.substring(lineIndex, lineIndex + MAX_CHAR));

							else
								send(line.substring(lineIndex));

						send(FileTransferServer.END_LINE);

						continue;
					}

					if (FILES_CONTENT.containsKey(inputLine)) {
						filename = inputLine;

						fileContent = FILES_CONTENT.get(filename);
						index = 0;

						send(FileTransferServer.FOUND);
						continue;
					}

					fileContent = null;
					filename = null;
					send(FileTransferServer.NOT_FOUND);
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

	private void send(FileTransferServer f) {
		send(f.getMessage());
	}
}