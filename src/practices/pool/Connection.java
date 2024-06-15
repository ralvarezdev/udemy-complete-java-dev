package practices.pool;

import java.util.List;

interface Connection {
	public boolean connect(DatabaseConfig config, boolean autoCommit);

	public void disconnect();

	public boolean commit();

	public boolean rollback();

	public boolean isClosed();

	public boolean isValid();

	public Integer executeUpdate(String sql);

	public Integer executeUpdate(String sql, String... params);

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func);

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func, String... params);
}
