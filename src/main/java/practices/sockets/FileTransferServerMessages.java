package practices.sockets;

public enum FileTransferServerMessages {
    FOUND("!"), NOT_FOUND("?"), START_LINE("{"), END_LINE("}"), END_FILE("."), UNDEFINED("0");

    private final String message;

    private FileTransferServerMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static FileTransferServerMessages fromString(String message) {
        if (message == null)
            return UNDEFINED;

        for (FileTransferServerMessages f : FileTransferServerMessages.values())
            if (f.getMessage().equalsIgnoreCase(message))
                return f;

        return UNDEFINED;
    }
}
