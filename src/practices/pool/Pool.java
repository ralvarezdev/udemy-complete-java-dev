package practices.pool;

public interface Pool {
	public boolean increasePoolSize(int incrConns);

	public boolean increasePoolSize();

	public int getSize();

	public Connection getConnection();

	public void putConnection(Connection connection);

	public void disconnectAll();
}
