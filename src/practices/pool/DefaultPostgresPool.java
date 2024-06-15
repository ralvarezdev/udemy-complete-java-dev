package practices.pool;

import practices.NullConfigException;

public final class DefaultPostgresPool extends DefaultPool {
	private static DefaultPostgresPool INSTANCE;

	private DefaultPostgresPool(DatabaseConfig dbConfig, PoolConfig poolConfig, boolean autoCommit) {
		super("postgresql", dbConfig, poolConfig, autoCommit);
	}

	public synchronized static DefaultPostgresPool getInstance(DatabaseConfig dbConfig, PoolConfig poolConfig,
			boolean autoCommit) throws NullConfigException {
		if (dbConfig == null || poolConfig == null)
			throw new NullConfigException("Database or pool configuration is null...");

		if (INSTANCE == null)
			INSTANCE = new DefaultPostgresPool(dbConfig, poolConfig, autoCommit);

		return INSTANCE;
	}
}
