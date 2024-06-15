package practices.pool;

import java.util.LinkedList;

import practices.ConnectionException;

public class DefaultPool implements Pool {
	protected final boolean AUTO_COMMIT;
	protected final String DRIVER;
	protected final DatabaseConfig DB_CONFIG;
	protected final PoolConfig POOL_CONFIG;
	protected final LinkedList<Connection> CONNECTIONS;

	protected int currMaxSize = 0;

	protected DefaultPool(String driver, DatabaseConfig dbConfig, PoolConfig poolConfig, boolean autoCommit) {
		// Set static attributes
		DRIVER = driver;
		DB_CONFIG = dbConfig;
		POOL_CONFIG = poolConfig;
		AUTO_COMMIT = autoCommit;

		// Initialize pool connections
		CONNECTIONS = new LinkedList<>();
		increasePoolSize(POOL_CONFIG.incrConns());
	}

	public synchronized boolean increasePoolSize(int incrConns) {
		if (currMaxSize + incrConns > POOL_CONFIG.maxConns())
			return false;

		System.out.println("Increasing pool size from %d to %d...".formatted(currMaxSize, currMaxSize + incrConns));
		for (int i = 0; i < incrConns;)
			try {
				CONNECTIONS.add(new DefaultConnection(DRIVER, DB_CONFIG, AUTO_COMMIT));
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

				} catch (IllegalMonitorStateException | InterruptedException e) {
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
		for (Connection connection : CONNECTIONS) {
			connection.disconnect();
			currMaxSize--;
		}
	}
}