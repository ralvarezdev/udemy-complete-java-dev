package practices.pool;

public final class DefaultPostgresPool extends DefaultPool {
	private static DefaultPostgresPool INSTANCE;

	private DefaultPostgresPool(DatabaseConfig dbConfig, PoolConfig poolConfig, boolean autoCommit,
			boolean printMessages) {
		super("postgresql", Databases.POSTGRES, dbConfig, poolConfig, autoCommit, printMessages);
	}

	public synchronized static DefaultPostgresPool getInstance(DatabaseConfig dbConfig, PoolConfig poolConfig,
			boolean autoCommit, boolean printMessages) throws NullPointerException {
		if (dbConfig == null || poolConfig == null)
			throw new NullPointerException("There are some null configurations...");

		if (INSTANCE == null)
			INSTANCE = new DefaultPostgresPool(dbConfig, poolConfig, autoCommit, printMessages);

		return INSTANCE;
	}

	public synchronized static DefaultPostgresPool getInstance(DatabaseConfig dbConfig, PoolConfig poolConfig)
			throws NullPointerException {
		return getInstance(dbConfig, poolConfig, true, true);
	}
}
