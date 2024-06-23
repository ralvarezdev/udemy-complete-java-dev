package practice.sockets;

public class FileTransferClientSocket extends ClientSocket {
	public FileTransferClientSocket(boolean printSocketMessages) {
		super(printSocketMessages);
	}

	private String send(FileTransferClient f) {
		return send(f.getMessage());
	}

	public String getFileContent(String filename) {
		String serverResponseString = send(filename);
		FileTransferServer serverResponse = FileTransferServer.fromString(serverResponseString);
		String message = serverResponse.getMessage();

		if (message == FileTransferServer.NOT_FOUND.getMessage()) {
			System.out.println("File '%s' not found on server...".formatted(filename));
			return null;
		}

		if (message != FileTransferServer.FOUND.getMessage()) {
			System.out.println("Unregistered response from server...");
			return null;
		}

		StringBuilder content = new StringBuilder();
		StringBuilder line = null;

		while (true) {

			serverResponseString = send(FileTransferClient.MORE);
			serverResponse = FileTransferServer.fromString(serverResponseString);
			message = serverResponse.getMessage();

			if (message == FileTransferServer.FINISHED.getMessage()) {
				if (PRINT_SOCKET_MESSAGES)
					System.out.println("File content successfully read from server...");
				break;
			}

			if (PRINT_SOCKET_MESSAGES)
				System.out.println("Getting more content from server...");

			if (message == FileTransferServer.START_LINE.getMessage()) {
				line = new StringBuilder();
				continue;
			}

			else if (message == FileTransferServer.END_LINE.getMessage()) {
				line.append("\n");
				content.append(line);
				continue;
			}

			line.append(serverResponseString);
		}

		return content.toString();
	}
}
