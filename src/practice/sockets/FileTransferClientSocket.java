package practice.sockets;

public class FileTransferClientSocket extends ClientSocket {
	public FileTransferClientSocket(boolean printSocketMessages) {
		super(printSocketMessages);
	}

	private String send(FileTransferClientMessages f) {
		return send(f.getMessage());
	}

	public String getFileContent(String filename) {
		String serverResponseString = send(filename);
		FileTransferServerMessages serverResponse = FileTransferServerMessages.fromString(serverResponseString);

		if (serverResponse == FileTransferServerMessages.NOT_FOUND) {
			System.out.println("File '%s' not found on server...".formatted(filename));
			return null;
		}

		if (serverResponse != FileTransferServerMessages.FOUND) {
			System.out.println("Unregistered response from server...");
			return null;
		}

		StringBuilder content = new StringBuilder();
		StringBuilder line = null;

		while (true) {

			serverResponseString = send(FileTransferClientMessages.MORE);
			serverResponse = FileTransferServerMessages.fromString(serverResponseString);

			if (serverResponse == FileTransferServerMessages.END_FILE) {
				if (PRINT_SOCKET_MESSAGES)
					System.out.println("File content successfully read from server...");
				break;
			}

			if (serverResponse == FileTransferServerMessages.START_LINE) {
				line = new StringBuilder();

				if (PRINT_SOCKET_MESSAGES) {
					// System.out.println();
					System.out.println("Getting line from server...");
				}
			}

			else if (serverResponse != FileTransferServerMessages.END_LINE) {
				line.append(serverResponseString);

				/*
				 * if (PRINT_SOCKET_MESSAGES) System.out.println(serverResponseString);
				 */
			}

			else {
				line.append("\n");
				content.append(line);
			}

		}

		return content.toString();
	}
}
