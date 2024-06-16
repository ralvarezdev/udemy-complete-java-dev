package practices.pool;

import java.util.List;

public interface PoolManager {
	public boolean isValid();

	public void getConnection();

	public void putConnection();

	public void commit();

	public void rollback();

	public Integer executeUpdate(String sql);

	public Integer executeUpdate(String sql, String... params);

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func);

	public <T> List<T> executeQuery(String sql, ResultSetFunction<T> func, String... params);
}
