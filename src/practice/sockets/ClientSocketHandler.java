package practice.sockets;

import java.io.IOException;
import java.net.Socket;
import java.util.function.BiConsumer;

public class ClientSocketHandler extends Thread {
	private final Socket CLIENT_SOCKET;
	private final BiConsumer<Socket, String> HANDLER;
	private final boolean PRINT_SOCKET_MESSAGES;

	public ClientSocketHandler(Socket clientSocket, BiConsumer<Socket, String> handler, boolean printSocketMessages) {
		if (clientSocket == null)
			throw new NullPointerException("%s: Client socket is null...".formatted(getName()));

		if (handler == null)
			throw new NullPointerException("%s: Client socket handler is null...".formatted(getName()));

		CLIENT_SOCKET = clientSocket;
		HANDLER = handler;
		PRINT_SOCKET_MESSAGES = printSocketMessages;
	}

	public void run() {
		String name = PRINT_SOCKET_MESSAGES ? getName() : null;
		HANDLER.accept(CLIENT_SOCKET, name);

		try {
			CLIENT_SOCKET.close();
			if (PRINT_SOCKET_MESSAGES)
				System.out.println("%s: Client socket successfully closed...".formatted(getName()));

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
