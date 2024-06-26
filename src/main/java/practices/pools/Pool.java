package practices.pools;

public interface Pool {
    boolean increasePoolSize(int incrConns);

    boolean increasePoolSize();

    int getSize();

    Connection getConnection();

    void putConnection(Connection connection);

    void disconnectAll();
}
