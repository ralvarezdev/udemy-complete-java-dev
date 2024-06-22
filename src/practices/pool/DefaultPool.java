package practices.pool;

import java.util.LinkedList;

import practices.ConnectionException;

public abstract class DefaultPool implements Pool {
	protected final String DRIVER;
	protected final Databases DB;
	protected final String POOL_NAME;
	protected final DatabaseConfig DB_CONFIG;
	protected final PoolConfig POOL_CONFIG;
	protected final LinkedList<Connection> CONNECTIONS;
	protected final int ATTEMPTS;
	protected final boolean AUTO_COMMIT;
	protected final boolean PRINT_POOL_MESSAGES;
	protected final boolean PRINT_CONNECTION_MESSAGES;

	protected int currMaxSize = 0;

	protected DefaultPool(String driver, Databases database, DatabaseConfig dbConfig, PoolConfig poolConfig,
			boolean autoCommit, boolean printPoolMessages, boolean printConnectionMessages, int attempts)
			throws NullPointerException {
		if (driver == null || database == null || dbConfig == null || poolConfig == null)
			throw new NullPointerException("There are some null configurations...");

		// Set static attributes
		DRIVER = driver;
		DB = database;
		POOL_NAME = "%s POOL".formatted(DB.getDatabaseName());
		DB_CONFIG = dbConfig;
		POOL_CONFIG = poolConfig;
		AUTO_COMMIT = autoCommit;
		PRINT_POOL_MESSAGES = printPoolMessages;
		PRINT_CONNECTION_MESSAGES = printConnectionMessages;
		ATTEMPTS = (attempts < 1) ? 5 : attempts;

		if (PRINT_POOL_MESSAGES)
			System.out.println("%s: Initializing pool...".formatted(POOL_NAME));

		// Initialize pool connections
		CONNECTIONS = new LinkedList<>();
		increasePoolSize(POOL_CONFIG.incrConns());
	}

	protected DefaultPool(String driver, Databases database, DatabaseConfig dbConfig, PoolConfig poolConfig,
			boolean autoCommit, boolean printPoolMessages, boolean printConnectionMessages)
			throws NullPointerException {
		this(driver, database, dbConfig, poolConfig, autoCommit, printPoolMessages, printConnectionMessages, 5);
	}

	protected DefaultPool(String driver, Databases database, DatabaseConfig dbConfig, PoolConfig poolConfig)
			throws NullPointerException {
		this(driver, database, dbConfig, poolConfig, true, false, false);
	}

	public synchronized boolean increasePoolSize(int incrConns) {
		if (currMaxSize + incrConns > POOL_CONFIG.maxConns())
			return false;

		if (PRINT_POOL_MESSAGES)
			System.out.println("%s: Increasing pool size from %d to %d...".formatted(POOL_NAME, currMaxSize,
					currMaxSize + incrConns));

		for (int i = 0; i < incrConns;)
			try {
				CONNECTIONS.add(
						new DefaultConnection(DRIVER, DB, DB_CONFIG, AUTO_COMMIT, PRINT_CONNECTION_MESSAGES, ATTEMPTS));
				i++;
			}

			catch (ConnectionException e) {
				System.err.println(e);
			}

		currMaxSize += incrConns;
		return true;
	}

	public synchronized boolean increasePoolSize() {
		return increasePoolSize(POOL_CONFIG.incrConns());
	}

	public synchronized int getSize() {
		return CONNECTIONS.size();
	}

	public synchronized Connection getConnection() {
		int size = getSize();

		if (size > 0)
			return CONNECTIONS.remove(size - 1);

		if (!increasePoolSize())
			while (size == 0)
				try {
					wait();
					size = getSize();

				} catch (InterruptedException e) {
					System.err.println(e);
					return null;
				}
		size = getSize();

		return CONNECTIONS.remove(size - 1);
	}

	public synchronized void putConnection(Connection connection) {
		CONNECTIONS.add(connection);
		notifyAll();
	}

	public synchronized void disconnectAll() {
		try {
			while (currMaxSize != CONNECTIONS.size())
				wait();

		} catch (InterruptedException e) {
			System.err.println(e);
			return;
		}

		for (Connection connection : CONNECTIONS) {
			connection.disconnect();
			currMaxSize--;
		}

		if (PRINT_POOL_MESSAGES)
			System.out.println("%s: Pool successfully disconnected...".formatted(POOL_NAME));
	}
}