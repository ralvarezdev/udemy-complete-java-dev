package practices.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import practices.ConnectionException;

class DefaultConnection implements practices.pool.Connection {
	private final boolean AUTO_COMMIT;
	private final DatabaseConfig CONFIG;
	private final String DRIVER;

	private Connection connection = null;

	public DefaultConnection(String driver, DatabaseConfig config, boolean autoCommit, int attempts)
			throws ConnectionException {
		this.DRIVER = driver;
		this.AUTO_COMMIT = autoCommit;
		this.CONFIG = config;

		// Try to connect n times
		if (attempts <= 0)
			attempts = 3;

		for (int i = 0; i < attempts; i++)
			if (connect(config, autoCommit))
				return;

		throw new ConnectionException("Couldn't establish database connection...");
	}

	public DefaultConnection(String driver, DatabaseConfig config, boolean autoCommit) throws ConnectionException {
		this(driver, config, autoCommit, 3);
	}

	public synchronized boolean connect(DatabaseConfig config, boolean autoCommit) {
		try {
			// Close existing connection, if exists
			if (isNull())
				disconnect();

			// Open connection to database
			this.connection = DriverManager.getConnection(config.url(DRIVER), config.user(), config.password());
			this.connection.setAutoCommit(autoCommit);

			System.out.println("Connection successfully established...");

		} catch (SQLException e) {
			setNull();
			System.err.println(e);

			return false;
		}

		return true;
	}

	public synchronized boolean commit() {
		if (isNull())
			return false;

		try {
			connection.commit();

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

		return true;
	}

	public synchronized boolean rollback() {
		if (isNull())
			return false;

		try {
			connection.rollback();

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

		return true;
	}

	public synchronized void disconnect() {
		try {
			if (isClosed())
				return;

			connection.close();
			System.out.println("Connection successfully closed...");

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public synchronized boolean isNull() {
		return connection == null;
	}

	private synchronized void setNull() {
		connection = null;
	}

	public synchronized boolean isClosed() {
		if (isNull())
			return true;

		try {
			return connection.isClosed();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return true;
	}

	public synchronized boolean isValid() {
		if (isNull())
			return false;

		try {
			return connection.isValid(5);

		} catch (SQLException e) {
			System.err.println(e);
		}

		return false;
	}

	private synchronized void checkConnection() {
		if (!isValid()) {
			disconnect();
			connect(CONFIG, AUTO_COMMIT);
		}
	}

	private synchronized void closeStatement(Statement statement) {
		try {
			if (statement != null)
				statement.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private synchronized void closeStatement(PreparedStatement prepStatement) {
		try {
			if (prepStatement != null)
				prepStatement.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private synchronized PreparedStatement setPreparedStatement(PreparedStatement prepStatement, String... params)
			throws SQLException {
		try {
			int paramCounter = 1;

			for (String param : params)
				prepStatement.setString(paramCounter++, param);

		} catch (SQLException e) {
			throw e;
		}

		return prepStatement;
	}

	public synchronized Integer executeUpdate(String sql) {
		checkConnection();

		Statement statement = null;
		Integer result = null;

		try {
			statement = connection.createStatement();
			result = statement.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println(e);

		}

		finally {
			closeStatement(statement);
		}

		return result;
	}

	public synchronized Integer executeUpdate(String sql, String... params) {
		checkConnection();

		PreparedStatement prepStatement = null;
		Integer result = null;

		try {
			prepStatement = setPreparedStatement(connection.prepareStatement(sql), params);
			result = prepStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		}

		finally {
			closeStatement(prepStatement);
		}

		return result;
	}

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func) {
		checkConnection();

		LinkedList<T> list = new LinkedList<>();
		Statement statement = null;
		ResultSet result = null;

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(sql);

			while (result.next())
				list.add(func.apply(result));

			result.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		finally {
			closeStatement(statement);
		}

		return list;
	}

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func, String... params) {
		checkConnection();

		LinkedList<T> list = new LinkedList<>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = setPreparedStatement(connection.prepareStatement(sql), params);
			result = statement.executeQuery();

			while (result.next())
				list.add(func.apply(result));

			result.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		finally {
			closeStatement(statement);
		}

		return list;
	}
}
