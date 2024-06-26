package practices.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    private Socket clientSocket;

    private OutputStream outputStream;
    private PrintWriter printWriter;

    private InputStream inputStream;
    private InputStreamReader streamReader;
    private BufferedReader reader;

    protected final boolean PRINT_SOCKET_MESSAGES;

    public ClientSocket(boolean printSocketMessages) {
        PRINT_SOCKET_MESSAGES = printSocketMessages;
    }

    public void start(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);

        // Output-related
        outputStream = clientSocket.getOutputStream();
        printWriter = new PrintWriter(outputStream, true);

        // Input-related
        inputStream = clientSocket.getInputStream();
        streamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(streamReader);

        if (PRINT_SOCKET_MESSAGES)
            System.out.println("Client socket connection successfully established...");
    }

    public String send(String msg) {
        if (!clientSocket.isClosed()) {
            printWriter.println(msg);

            try {
                return reader.readLine();

            } catch (IOException e) {
                System.err.println(e);
            }
        }
        return null;
    }

    public void close() {
        try {
            reader.close();
            streamReader.close();
            inputStream.close();

            outputStream.close();
            clientSocket.close();

            if (PRINT_SOCKET_MESSAGES)
                System.out.println("Client socket connection successfully closed...");

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
