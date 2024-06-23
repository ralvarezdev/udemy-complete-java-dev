package practice.sockets;

public enum FileTransferClient {
	MORE("+"), FORCED_END("."), UNDEFINED("0");

	private final String message;

	private FileTransferClient(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public static FileTransferClient fromString(String message) {
		if (message == null)
			return UNDEFINED;

		for (FileTransferClient f : FileTransferClient.values())
			if (f.getMessage().equalsIgnoreCase(message))
				return f;

		return UNDEFINED;
	}
}
