package practices.pool;

import java.util.List;

public final class DefaultPoolManager implements PoolManager {
	private final Pool POOL;

	private Connection connection;

	public DefaultPoolManager(Pool pool) throws NullPointerException {
		if (pool == null)
			throw new NullPointerException("Pool is null.");

		POOL = pool;
	}

	private synchronized boolean isNull() {
		return connection == null;
	}

	public synchronized boolean isValid() {
		if (connection != null)
			return connection.isValid();
		return false;
	}

	public synchronized void getConnection() {
		if (isNull())
			connection = POOL.getConnection();
	}

	public synchronized void putConnection() {
		if (!isNull()) {
			POOL.putConnection(connection);
			connection = null;
		}
	}

	public synchronized void commit() {
		if (!isNull())
			connection.commit();
	}

	public synchronized void rollback() {
		if (!isNull())
			connection.rollback();
	}

	public synchronized Integer executeUpdate(String sql) {
		if (!isNull())
			return connection.executeUpdate(sql);
		return null;
	}

	public synchronized Integer executeUpdate(String sql, String... params) {
		if (!isNull())
			return connection.executeUpdate(sql, params);
		return null;
	}

	public synchronized <T> List<T> executeQuery(String sql, ResultSetFunction<T> func) {
		if (!isNull())
			return connection.executeQuery(sql, func);
		return null;
	}

	public synchronized <T> List<T> executeQuery(String sql, ResultSetFunction<T> func, String... params) {
		if (!isNull())
			return connection.executeQuery(sql, func, params);
		return null;
	}
}
