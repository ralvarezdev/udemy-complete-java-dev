package practices.pool;

public final class DefaultMySqlPool extends DefaultPool {
	private static DefaultMySqlPool INSTANCE;

	private DefaultMySqlPool(DatabaseConfig dbConfig, PoolConfig poolConfig, boolean autoCommit,
			boolean printMessages) {
		super("mysql", Databases.MYSQL, dbConfig, poolConfig, autoCommit, printMessages);
	}

	public synchronized static DefaultMySqlPool getInstance(DatabaseConfig dbConfig, PoolConfig poolConfig,
			boolean autoCommit, boolean printMessages) throws NullPointerException {
		if (dbConfig == null || poolConfig == null)
			throw new NullPointerException("There are some null configurations...");

		if (INSTANCE == null)
			INSTANCE = new DefaultMySqlPool(dbConfig, poolConfig, autoCommit, printMessages);

		return INSTANCE;
	}

	public synchronized static DefaultMySqlPool getInstance(DatabaseConfig dbConfig, PoolConfig poolConfig)
			throws NullPointerException {
		return getInstance(dbConfig, poolConfig, true, true);
	}
}