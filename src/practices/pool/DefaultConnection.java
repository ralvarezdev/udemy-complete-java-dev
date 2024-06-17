package practices.pool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private PreparedStatement prepStatement = null;

	public DefaultConnection(String driver, Databases database, DatabaseConfig dbConfig, boolean autoCommit,
			boolean printMessages, int attempts) throws NullPointerException, ConnectionException {
		if (driver == null || database == null || dbConfig == null)
			throw new NullPointerException("There are some null configurations...");

		DRIVER = driver;
		DB = database;
		DB_NAME = DB.getDatabaseName();
		DB_CONFIG = dbConfig;
		AUTO_COMMIT = autoCommit;
		PRINT_MESSAGES = printMessages;

		// Try to connect n times
		if (attempts <= 0)
			attempts = 3;

		for (int i = 0; i < attempts; i++)
			if (connect(dbConfig, autoCommit))
				return;

			else if (PRINT_MESSAGES)
				System.out.println("%s: Attempt to establish database connection failed...".formatted(DB_NAME));

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

	private synchronized void checkPrepStatement() throws NullPointerException {
		if (prepStatement == null)
			throw new NullPointerException("%s: Prepared statement hasn't been initialized.".formatted(DB_NAME));
	}

	public synchronized void createPreparedStatement(String sql) {
		checkConnection();

		if (prepStatement != null)
			closePreparedStatement();

		try {
			prepStatement = connection.prepareStatement(sql);

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public synchronized void closePreparedStatement() {
		try {
			if (prepStatement != null)
				prepStatement.close();

			prepStatement = null;

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private void checkParamCounter(int paramCounter) throws SQLException {
		if (paramCounter < 1)
			throw new SQLException("%s: Invalid parameter index.".formatted(DB_NAME));
	}

	public synchronized void setStringParameter(int paramCounter, String param)
			throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setString(paramCounter, param);
	}

	public synchronized void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setInt(paramCounter, param);
	}

	public synchronized void setFloatParameter(int paramCounter, float param)
			throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setFloat(paramCounter, param);
	}

	public synchronized void setDoubleParameter(int paramCounter, double param)
			throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setDouble(paramCounter, param);
	}

	public synchronized void setBigDecimalParameter(int paramCounter, BigDecimal param)
			throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setBigDecimal(paramCounter, param);
	}

	public synchronized void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException {
		checkPrepStatement();
		checkParamCounter(paramCounter);
		prepStatement.setLong(paramCounter, param);
	}

	public synchronized Integer executeUpdate() throws NullPointerException {
		checkConnection();
		checkPrepStatement();

		try {
			return prepStatement.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return null;
	}

	public synchronized <T> List<T> executeQuery(ResultSetFunction<T> func) throws NullPointerException {
		checkConnection();
		checkPrepStatement();

		LinkedList<T> list = new LinkedList<>();
		ResultSet result = null;

		try {
			result = prepStatement.executeQuery();

			while (result.next())
				list.add(func.apply(result));

			result.close();
			return list;

		} catch (SQLException e) {
			System.err.println(e);
		}

		return null;
	}
}
