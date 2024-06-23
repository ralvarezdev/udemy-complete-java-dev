package practice.sockets;

public enum FileTransferClientMessages {
	MORE("+"), FORCED_END("."), UNDEFINED("0");

	private final String message;

	private FileTransferClientMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public static FileTransferClientMessages fromString(String message) {
		if (message == null)
			return UNDEFINED;

		for (FileTransferClientMessages f : FileTransferClientMessages.values())
			if (f.getMessage().equalsIgnoreCase(message))
				return f;

		return UNDEFINED;
	}
}
