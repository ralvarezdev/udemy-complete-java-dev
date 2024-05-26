package practices.connections;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

class Database {
	private static boolean loadedProps = false;
	private static String DBHOST;
	private static String PORT;
	private static String DBNAME;
	private static String USER;
	private static String DBPASS;

	private static boolean connected = false;
	private static Connection connection = null;

	public Database() {
		if (!connected) {
			if (!loadedProps) {
				try {
					String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
					String appConfigPath = rootPath + "db.properties";

					Properties appProps = new Properties();
					appProps.load(new FileInputStream(appConfigPath));

					DBHOST = appProps.getProperty("DBHOST");
					PORT = appProps.getProperty("PORT");
					DBNAME = appProps.getProperty("DBNAME");
					USER = appProps.getProperty("USER");
					DBPASS = appProps.getProperty("DBPASS");

					List<String> paramsList = Arrays.asList(DBHOST, PORT, DBNAME, USER, DBPASS);
					LinkedList<String> paramsLinkedList = new LinkedList<>(paramsList);

					for (String param : paramsLinkedList)
						if (param == null)
							throw new MissingPropertyException("Missing some database properties.");

				} catch (Exception e) {
					System.err.println(e);
					System.exit(-1);
				}

				loadedProps = true;

				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					System.err.println(e);
					System.exit(-1);
				}
			}

			// Open Connection to Database
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://" + DBHOST + ":" + PORT + "/" + DBNAME,
						USER, DBPASS);
			} catch (SQLException e) {
				System.err.println(e);
			}

			// Next line is commented just for testing purposes of
			// how the database stresses on unlimited connections
			// connected = true;
		}
	}

	public Connection getConnection() {
		return connection;
	}
}