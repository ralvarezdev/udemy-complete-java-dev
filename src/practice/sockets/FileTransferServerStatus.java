package practice.sockets;

public enum FileTransferServerStatus {
	START_CONN, FOUND, NOT_FOUND, START_LINE, ONGOING_LINE, END_LINE, END_FILE, END_CONN;
}
