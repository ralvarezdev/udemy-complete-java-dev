package practices.pools;

import java.io.IOException;
import java.util.LinkedList;

import practices.MissingPropertyException;
import practices.dbcomponent.DefaultDbComponent;
import practices.files.DefaultPropertiesReader;

public class Main {
    public static void main(String[] args) {
        int MAX_THREADS = 1000;
        String DB_PROPS_FILENAME = "pools-db.properties";
        String POOL_PROPS_FILENAME = "pools-pool.properties";

        LinkedList<CustThread> threads = new LinkedList<>();

        Pool pool = null;

        // Read properties
        Databases db = Databases.POSTGRES;

        try {
            DefaultPropertiesReader<Pool> propsReader = new DefaultPropertiesReader<>(Pool.class);
            DatabaseConfig dbConfig = db.getDatabaseConfig(propsReader, DB_PROPS_FILENAME);
            PoolConfig poolConfig = db.getDatabasePoolConfig(propsReader, POOL_PROPS_FILENAME);
            pool = DefaultPostgresPool.getInstance(dbConfig, poolConfig, true, true, false);

        } catch (NullPointerException | IOException | MissingPropertyException e) {
            System.err.println(e);
            System.exit(-1);
        }

        for (int i = 1; i <= MAX_THREADS; i++) {
            PoolManager poolManager = new DefaultPoolManager(db, pool, false);

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