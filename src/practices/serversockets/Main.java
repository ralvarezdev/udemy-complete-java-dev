package practices.serversockets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import practices.MissingPropertyException;
import practices.filereader.DefaultFileReader;
import practices.pool.DefaultPropertiesReader;

public class Main {
	public static void main(String[] args) {
		String PEOPLE_CSV_FILENAME = "people.csv";
		String SERVER_PROPERTIES = "sockets-server.properties";
		String PORT_FIELDNAME = "SERVER_PORT";
		int MAX_CHAR = 128;
		boolean PRINT_SERVER_MESSAGES = true;
		boolean PRINT_SOCKET_MESSAGES = true;

		HashMap<String, String> FILES_PATH = new HashMap<>();
		DefaultFileReader fileReader = new DefaultFileReader();
		DefaultPropertiesReader propsReader = new DefaultPropertiesReader();
		String portString;
		int port = 0;

		try {
			FILES_PATH.put("people", fileReader.getResourcePath(PEOPLE_CSV_FILENAME));
			portString = propsReader.getProperty(SERVER_PROPERTIES, PORT_FIELDNAME);
			port = Integer.parseInt(portString);

		} catch (FileNotFoundException | NumberFormatException | MissingPropertyException e) {
			System.err.println(e);
			System.exit(-1);
		}

		try {
			FileTransferServerSocket serverSocket = new FileTransferServerSocket(fileReader, FILES_PATH, MAX_CHAR, port,
					PRINT_SERVER_MESSAGES, PRINT_SOCKET_MESSAGES);
			serverSocket.start();

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
