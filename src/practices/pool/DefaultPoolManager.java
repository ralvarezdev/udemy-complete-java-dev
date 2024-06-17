package practices.pool;

import java.math.BigDecimal;
import java.sql.SQLException;
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
		if (isValid())
			connection.commit();
	}

	public synchronized void rollback() {
		if (isValid())
			connection.rollback();
	}

	public synchronized void createPreparedStatement(String sql) {
		if (isValid())
			connection.createPreparedStatement(sql);
	}

	public synchronized void closePreparedStatement() {
		if (isValid())
			connection.closePreparedStatement();
	}

	public synchronized void setStringParameter(int paramCounter, String param)
			throws NullPointerException, SQLException {
		if (isValid())
			connection.setStringParameter(paramCounter, param);
	}

	public synchronized void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException {
		if (isValid())
			connection.setIntParameter(paramCounter, param);
	}

	public synchronized void setFloatParameter(int paramCounter, float param)
			throws NullPointerException, SQLException {
		if (isValid())
			connection.setFloatParameter(paramCounter, param);
	}

	public synchronized void setDoubleParameter(int paramCounter, double param)
			throws NullPointerException, SQLException {
		if (isValid())
			connection.setDoubleParameter(paramCounter, param);
	}

	public synchronized void setBigDecimalParameter(int paramCounter, BigDecimal param)
			throws NullPointerException, SQLException {
		if (isValid())
			connection.setBigDecimalParameter(paramCounter, param);
	}

	public synchronized void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException {
		if (isValid())
			connection.setLongParameter(paramCounter, param);
	}

	public synchronized Integer executeUpdate() {
		if (isValid())
			return connection.executeUpdate();
		return null;
	}

	public synchronized <T> List<T> executeQuery(ResultSetFunction<T> func) {
		if (isValid())
			return connection.executeQuery(func);
		return null;
	}
}
