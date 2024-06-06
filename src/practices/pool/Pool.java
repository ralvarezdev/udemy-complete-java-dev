package practices.pool;

import java.io.FileInputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.Properties;

import practices.MissingPropertiesFileException;
import practices.MissingPropertyException;

public enum Pool {
	INSTANCE;

	private final static String DB_PROPS_FILENAME = "db.properties";
	private final static String POOL_PROPS_FILENAME = "pool.properties";

	private DatabaseConfig config;
	private int INIT_CONNS, INCR_CONNS, MAX_CONNS;
	private boolean AUTO_COMMIT = true;

	private LinkedList<Conn> conns;
	private int currMaxSize = 0;

	private Pool() {
		try {
			ClassLoader context = Thread.currentThread().getContextClassLoader();
			URL dbPropsResource = context.getResource(DB_PROPS_FILENAME);
			URL poolPropsResource = context.getResource(POOL_PROPS_FILENAME);

			if (dbPropsResource == null)
				throw new MissingPropertiesFileException("Missing %s file.".formatted(DB_PROPS_FILENAME));

			if (poolPropsResource == null)
				throw new MissingPropertiesFileException("Missing %s file.".formatted(POOL_PROPS_FILENAME));

			String dbPropsPath = dbPropsResource.getPath();
			String poolPropsPath = poolPropsResource.getPath();

			// Open connection to 'db.properties' and 'pool.properties' files
			Properties dbProps = new Properties();
			dbProps.load(new FileInputStream(dbPropsPath));

			Properties poolProps = new Properties();
			poolProps.load(new FileInputStream(poolPropsPath));

			// Get database connection details properties
			String host = dbProps.getProperty("DBHOST");
			String port = dbProps.getProperty("PORT");
			String database = dbProps.getProperty("DBNAME");
			String user = dbProps.getProperty("USER");
			String password = dbProps.getProperty("DBPASS");

			// Get pool properties
			String initConns = poolProps.getProperty("INIT_CONNS");
			String incrConns = poolProps.getProperty("INCR_CONNS");
			String maxConns = poolProps.getProperty("MAX_CONNS");

			String[] props = { host, port, database, user, password, initConns, incrConns, maxConns };

			for (String prop : props)
				if (prop == null)
					throw new MissingPropertyException("Missing some database properties.");

			config = new DatabaseConfig(host, port, database, user, password);

			INIT_CONNS = Integer.parseInt(initConns);
			INCR_CONNS = Integer.parseInt(incrConns);
			MAX_CONNS = Integer.parseInt(maxConns);

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		// Look for PostgreSQL Driver
		try {
			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			System.err.println(e);
			System.exit(-1);
		}

		// Initialize pool connections
		conns = new LinkedList<>();
		increasePoolSize(INIT_CONNS);
	}

	public static Pool getInstance() {
		return INSTANCE;
	}

	private synchronized boolean increasePoolSize(int incrConns) {
		if (currMaxSize + incrConns > MAX_CONNS) {
			// System.out.println("Max pool size reached...");
			return false;
		}

		System.out.println("Increasing pool size from %d to %d...".formatted(currMaxSize, currMaxSize + incrConns));
		for (int i = 0; i < incrConns; i++)
			conns.add(new Conn(config, AUTO_COMMIT));

		currMaxSize += incrConns;
		return true;
	}

	private synchronized boolean increasePoolSize() {
		return increasePoolSize(INCR_CONNS);
	}

	private synchronized int getSize() {
		return this.conns.size();
	}

	public synchronized Conn getConnection() {
		int size = getSize();

		if (size > 0)
			return this.conns.remove(size - 1);

		if (!increasePoolSize())
			while (size == 0)
				try {
					wait();
					size = getSize();

				} catch (IllegalMonitorStateException | InterruptedException e) {
					System.err.println(e);
					return null;
				}
		size = getSize();

		return this.conns.remove(size - 1);
	}

	public synchronized void putConnection(Conn connection) {
		conns.add(connection);
		notifyAll();
	}

	public synchronized void disconnectAll() {
		for (Conn conn : conns) {
			conn.disconnect();
			currMaxSize--;
		}
	}
}