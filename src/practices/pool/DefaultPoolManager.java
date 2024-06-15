package practices.pool;

import java.util.List;

public class DefaultPoolManager implements PoolManager {
	private final Pool POOL;

	private Connection connection;

	public DefaultPoolManager(Pool pool) {
		this.POOL = pool;
	}

	private synchronized boolean isNull() {
		return this.connection == null;
	}

	public synchronized void getConnection() {
		if (isNull())
			this.connection = POOL.getConnection();
	}

	public synchronized void putConnection() {
		if (!isNull()) {
			POOL.putConnection(connection);
			this.connection = null;
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
