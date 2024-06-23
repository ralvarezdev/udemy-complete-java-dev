package practice.sockets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import practices.MissingPropertyException;
import practices.filereader.DefaultFileReader;
import practices.filereader.DefaultFileWriter;
import practices.pool.DefaultPropertiesReader;

public class Main {
	public static void main(String[] args) {
		String SERVER_PROPERTIES = "sockets-server.properties";
		int CLIENT_SOCKET_DELAY = 1000;

		String PORT_FIELDNAME = "SERVER_PORT";
		String IP_FIELDNAME = "SERVER_IP";

		DefaultPropertiesReader propsReader = new DefaultPropertiesReader();
		String ip = null;
		int port = 0;

		try {
			ip = propsReader.getProperty(SERVER_PROPERTIES, IP_FIELDNAME);
			String portString = propsReader.getProperty(SERVER_PROPERTIES, PORT_FIELDNAME);
			port = Integer.parseInt(portString);

		} catch (FileNotFoundException | NumberFormatException | MissingPropertyException e) {
			System.err.println(e);
			System.exit(-1);
		}

		// Initialize server socket
		FileTransferServerSocket serverSocket = getServerSocket();
		Thread serverSocketThread = serverSocket.startThread(port);

		// Wait n milliseconds to start client socket
		try {
			Thread.sleep(CLIENT_SOCKET_DELAY);

		} catch (InterruptedException e) {
			System.err.println(e);
			System.exit(-1);
		}

		// Initialize client socket and get file content
		try {
			initClientSocket(ip, port);

		} catch (NullPointerException | FileNotFoundException e) {
			System.err.println(e);
		}

		serverSocket.close();
	}

	public static FileTransferServerSocket getServerSocket() {
		String PEOPLE_CSV_FILENAME = "people.csv";
		int MAX_CHAR = 128;

		boolean PRINT_SERVER_MESSAGES = true;
		boolean PRINT_SOCKET_MESSAGES = false;

		HashMap<String, String> FILES_PATH = new HashMap<>();
		DefaultFileReader fileReader = new DefaultFileReader();

		FileTransferServerSocket serverSocket = null;

		try {
			FILES_PATH.put("people", fileReader.getResourcePath(PEOPLE_CSV_FILENAME));

			serverSocket = new FileTransferServerSocket(fileReader, FILES_PATH, MAX_CHAR, PRINT_SERVER_MESSAGES,
					PRINT_SOCKET_MESSAGES);

		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		}

		return serverSocket;
	}

	public static void initClientSocket(String ip, int port) throws NullPointerException, FileNotFoundException {
		String FILENAME = "people";
		String READ_CSV_FILENAME = "read-people.csv";
		String READ_CSV_PATH = String.join("\\", "bin", READ_CSV_FILENAME);

		boolean PRINT_SOCKET_MESSAGES = false;

		FileTransferClientSocket clientSocket = null;
		String content = null;

		// Get file content
		try {
			clientSocket = new FileTransferClientSocket(PRINT_SOCKET_MESSAGES);
			clientSocket.start(ip, port);
			content = clientSocket.getFileContent(FILENAME);

		} catch (IOException e) {
			System.err.println(e);
		}

		clientSocket.close();

		// Write file content
		DefaultFileWriter fileWriter = new DefaultFileWriter();

		try {

			try {
				fileWriter.getResourcePath(READ_CSV_FILENAME);

			} catch (FileNotFoundException e) {
				File writeFile = new File(READ_CSV_PATH);
				writeFile.createNewFile();
			}

		} catch (IOException f) {
			System.err.println(f);
		}

		fileWriter.overwriteFileContent(READ_CSV_FILENAME, content);
	}
}
