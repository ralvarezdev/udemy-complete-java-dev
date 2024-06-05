package practices.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

class Conn {
	private Connection connection;

	private final static String INVALID_CONN = "Connection hasn't been initialized...";

	private final static int VALID_DELAY = 5;

	private final boolean AUTO_COMMIT;
	private final DatabaseConfig config;

	public Conn(DatabaseConfig config, boolean autoCommit) {
		connect(config, autoCommit);
		this.AUTO_COMMIT = autoCommit;
		this.config = config;
	}

	private synchronized void connect(DatabaseConfig config, boolean autoCommit) {
		// Open Connection to database
		try {
			connection = DriverManager.getConnection(config.url(), config.user(), config.password());

			if (!autoCommit)
				connection.setAutoCommit(false);

			System.out.println("Connection successfully established...");

		} catch (SQLException e) {
			connection = null;
			System.err.println(e);
		}
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
			return;
		}
	}

	public synchronized boolean isNull() {
		if (connection == null) {
			System.err.println(INVALID_CONN);
			return true;
		}
		return false;
	}

	public synchronized boolean isClosed() {
		if (isNull())
			return true;

		try {
			return connection.isClosed();

		} catch (SQLException e) {
			System.err.println(e);
			return true;
		}
	}

	public synchronized boolean isValid() {
		if (isNull())
			return false;

		try {
			return connection.isValid(VALID_DELAY);

		} catch (SQLException e) {
			System.err.println(e);
		}

		return false;
	}

	private synchronized void checkConnection() {
		if (!isValid()) {
			this.disconnect();
			this.connect(config, AUTO_COMMIT);
		}
	}

	private synchronized void closeStatement(Statement statement) {
		try {
			statement.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private synchronized void closeStatement(PreparedStatement statement) {
		try {
			statement.close();

		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	private synchronized void setPreparedStatement(PreparedStatement statement, String... params) throws SQLException {
		try {
			int paramCounter = 1;

			for (String param : params)
				statement.setString(paramCounter++, param);

		} catch (SQLException e) {
			throw e;
		}
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

		closeStatement(statement);
		return result;
	}

	public synchronized Integer executeUpdate(String sql, String... params) {
		checkConnection();

		PreparedStatement statement = null;
		Integer result = null;

		try {
			statement = connection.prepareStatement(sql);
			setPreparedStatement(statement, params);

			result = statement.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		}

		closeStatement(statement);
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
			closeStatement(statement);

		} catch (SQLException e) {
			System.err.println(e);
		}

		return list;
	}

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func, String... params) {
		checkConnection();

		LinkedList<T> list = new LinkedList<>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement(sql);
			setPreparedStatement(statement, params);
			result = statement.executeQuery();

			while (result.next())
				list.add(func.apply(result));

			result.close();
			closeStatement(statement);

		} catch (SQLException e) {
			System.err.println(e);
		}

		return list;
	}
}
