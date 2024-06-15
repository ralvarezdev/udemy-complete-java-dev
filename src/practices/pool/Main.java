package practices.pool;

import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
		int MAX_THREADS = 1000;

		LinkedList<CustThread> threads = new LinkedList<>();

		Pool pool = null;

		// Read properties
		try {
			DefaultPropertiesReader propsReader = new DefaultPropertiesReader();
			DatabaseConfig dbConfig = Databases.POSTGRES.getDatabaseConfig(propsReader);
			PoolConfig poolConfig = Databases.POSTGRES.getDatabasePoolConfig(propsReader);
			pool = DefaultPostgresPool.getInstance(dbConfig, poolConfig, true);

		} catch (Exception e) {
			System.err.println(e);
			System.exit(-1);
		}

		for (int i = 1; i <= MAX_THREADS; i++) {
			PoolManager poolManager = new DefaultPoolManager(pool);

			CustThread thread = new CustThread(poolManager);
			threads.add(thread);

			thread.setName("" + i);
			thread.start();
		}

		// Wait for all threads to finish its tasks
		for (CustThread thread : threads)
			try {
				thread.join();

			} catch (InterruptedException e) {
				System.err.println(e);
			}

		// Close all connections
		pool.disconnectAll();
	}
}