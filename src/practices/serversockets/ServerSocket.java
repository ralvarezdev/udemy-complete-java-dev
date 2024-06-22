package practices.serversockets;

import java.io.IOException;
import java.net.Socket;
import java.util.function.BiConsumer;

public abstract class ServerSocket {
	protected final String NAME;
	protected final java.net.ServerSocket SERVER_SOCKET;
	protected final int PORT;
	protected final boolean PRINT_SERVER_MESSAGES;
	protected final boolean PRINT_SOCKET_MESSAGES;

	protected BiConsumer<Socket, String> SOCKET_HANDLER = null;

	public ServerSocket(int port, boolean printServerMessages, boolean printSocketMessages) throws IOException {
		SERVER_SOCKET = new java.net.ServerSocket(port);
		PORT = (port != 0) ? port : SERVER_SOCKET.getLocalPort();

		PRINT_SERVER_MESSAGES = printServerMessages;
		PRINT_SOCKET_MESSAGES = printSocketMessages;
		NAME = "SERVER SOCKET %d".formatted(PORT);

		if (printServerMessages)
			System.out.println("Initializing server socket at port %d".formatted(PORT));
	}

	public ServerSocket(boolean printServerMessages, boolean printSocketMessages) throws IOException {
		this(0, printServerMessages, printSocketMessages);
	}

	public ServerSocket() throws IOException {
		this(0, false, false);
	}

	protected void setSocketHandler(BiConsumer<Socket, String> socketHandler) {
		SOCKET_HANDLER = socketHandler;

		if (PRINT_SERVER_MESSAGES)
			System.out.println("%s: Successfully set socket handler".formatted(NAME));
	}

	public void start() throws NullPointerException {
		if (SOCKET_HANDLER == null)
			throw new NullPointerException("Socket handler hasn't been set...");

		while (true) {
			Socket socket = null;

			try {
				socket = SERVER_SOCKET.accept();

			} catch (IOException e) {
				System.err.println(e);
				continue;
			}

			if (PRINT_SERVER_MESSAGES)
				System.out.println("%s: Accepted incoming connection...".formatted(NAME));

			new ClientSocketHandler(socket, SOCKET_HANDLER, PRINT_SOCKET_MESSAGES).start();
		}
	}

	public void close() {
		try {
			if (!SERVER_SOCKET.isClosed()) {
				SERVER_SOCKET.close();

				if (PRINT_SERVER_MESSAGES)
					System.out.println("%s: Server socket successfully closed...".formatted(NAME));
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}