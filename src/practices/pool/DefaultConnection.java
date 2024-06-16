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

public final class DefaultConnection implements practices.pool.Connection {
	private final String DRIVER;
	private final Databases DB;
	private final String DB_NAME;
	private final DatabaseConfig DB_CONFIG;
	private final boolean AUTO_COMMIT;
	private final boolean PRINT_MESSAGES;

	private Connection connection = null;

	public DefaultConnection(String driver, Databases database, DatabaseConfig dbConfig, boolean autoCommit,
			boolean printMessages, int attempts) throws NullPointerException, ConnectionException {
		if (driver == null || database == null || dbConfig == null)
			throw new NullPointerException("There are some null configurations...");

		DRIVER = driver;
		DB = database;
		DB_NAME = database.getDatabaseName();
		DB_CONFIG = dbConfig;
		AUTO_COMMIT = autoCommit;
		PRINT_MESSAGES = printMessages;

		// Try to connect n times
		if (attempts <= 0)
			attempts = 3;

		for (int i = 0; i < attempts; i++)
			if (connect(dbConfig, autoCommit))
				return;

		throw new ConnectionException("%s: Couldn't establish database connection.".formatted(DB_NAME));
	}

	public DefaultConnection(String driver, Databases databases, DatabaseConfig dbConfig, boolean autoCommit,
			boolean printMessages) throws NullPointerException, ConnectionException {
		this(driver, databases, dbConfig, autoCommit, printMessages, 3);
	}

	public DefaultConnection(String driver, Databases databases, DatabaseConfig dbConfig)
			throws NullPointerException, ConnectionException {
		this(driver, databases, dbConfig, true, false);
	}

	public synchronized boolean connect(DatabaseConfig dbConfig, boolean autoCommit) {
		if (dbConfig == null)
			throw new NullPointerException("%s: Database configuration is null.".formatted(DB_NAME));

		try {
			// Close existing connection, if exists
			if (isNull())
				disconnect();

			// Open connection to database
			connection = DriverManager.getConnection(dbConfig.url(DRIVER), dbConfig.user(), dbConfig.password());
			connection.setAutoCommit(autoCommit);

			if (PRINT_MESSAGES)
				System.out.println("%s: Connection successfully established...".formatted(DB_NAME));

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

			if (PRINT_MESSAGES)
				System.out.println("%s: Connection successfully closed...".formatted(DB_NAME));

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
			connect(DB_CONFIG, AUTO_COMMIT);
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
