package practice.sockets;

public enum FileTransferServer {
	FOUND("!"), NOT_FOUND("?"), START_LINE("{"), END_LINE("}"), FINISHED(""), UNDEFINED("0");

	private final String message;

	private FileTransferServer(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public static FileTransferServer fromString(String message) {
		if (message == null)
			return UNDEFINED;

		for (FileTransferServer f : FileTransferServer.values())
			if (f.getMessage().equalsIgnoreCase(message))
				return f;

		return UNDEFINED;
	}
}
