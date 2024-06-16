package practices.dbcomponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import practices.MissingPropertyException;
import practices.pool.DatabaseConfig;
import practices.pool.Databases;
import practices.pool.DefaultMySqlPool;
import practices.pool.DefaultPoolManager;
import practices.pool.DefaultPostgresPool;
import practices.pool.Pool;
import practices.pool.PoolConfig;
import practices.pool.PoolManager;
import practices.pool.PropertiesReader;
import practices.pool.ResultSetFunction;

public class DefaultDbComponent implements DbComponent {
	private final PropertiesReader PROPS_READER;
	private final HashMap<Databases, Pool> POOLS;
	private final HashMap<Databases, PoolManager> POOL_MANAGERS;
	private final HashMap<Databases, Map<String, String>> DB_SENTENCES;
	private final String DB_PROPS_FILENAME;
	private final String POOL_PROPS_FILENAME;
	private final boolean PRINT_MESSAGES;

	public DefaultDbComponent(PropertiesReader propsReader, String dbPropsFilename, String poolPropsFilename,
			boolean printMessages) throws NullPointerException {
		if (dbPropsFilename == null || poolPropsFilename == null)
			throw new NullPointerException("There are some database configuration null filenames.");

		PROPS_READER = propsReader;
		POOLS = new HashMap<>();
		POOL_MANAGERS = new HashMap<>();
		DB_SENTENCES = new HashMap<>();
		DB_PROPS_FILENAME = dbPropsFilename;
		POOL_PROPS_FILENAME = poolPropsFilename;
		PRINT_MESSAGES = printMessages;
	}

	public DefaultDbComponent(List<Databases> databases, PropertiesReader propsReader, String dbPropsFilename,
			String poolPropsFilename, boolean printMessages) throws NullPointerException, MissingPropertyException {
		this(propsReader, dbPropsFilename, poolPropsFilename, printMessages);

		var databasesSet = Set.copyOf(databases);

		for (Databases database : databasesSet) {
			DB_SENTENCES.put(database, new HashMap<>());
			loadPoolManager(database);
		}
	}

	public DefaultDbComponent(Databases[] databases, PropertiesReader propsReader, String dbPropsFilename,
			String poolPropsFilename, boolean printMessages) throws NullPointerException, MissingPropertyException {
		this(Arrays.asList(databases), propsReader, dbPropsFilename, poolPropsFilename, printMessages);
	}

	private synchronized void checkDatabase(Databases database) throws NullPointerException {
		if (database == null)
			throw new NullPointerException("Database is null.");
	}

	private synchronized void checkPool(Databases database) throws NullPointerException {
		checkDatabase(database);

		if (!POOL_MANAGERS.containsKey(database))
			throw new NullPointerException("Database pool hasn't been loaded.");
	}

	private synchronized void checkPoolManager(Databases database) throws NullPointerException {
		checkDatabase(database);

		if (!POOL_MANAGERS.containsKey(database))
			throw new NullPointerException("Database pool manager hasn't been loaded.");
	}

	private synchronized void checkDatabaseSentences(Databases database) throws NullPointerException {
		checkDatabase(database);

		if (!DB_SENTENCES.containsKey(database))
			throw new NullPointerException("Database sentences haven't been loaded.");
	}

	public synchronized void loadPoolManager(Databases database, boolean autoCommit)
			throws NullPointerException, MissingPropertyException {
		checkDatabase(database);

		if (POOL_MANAGERS.containsKey(database))
			return;

		DatabaseConfig dbConfig = database.getDatabaseConfig(PROPS_READER, DB_PROPS_FILENAME);
		PoolConfig poolConfig = database.getDatabasePoolConfig(PROPS_READER, POOL_PROPS_FILENAME);

		Pool pool = switch (database) {
		case POSTGRES -> DefaultPostgresPool.getInstance(dbConfig, poolConfig, autoCommit, PRINT_MESSAGES);
		case MYSQL -> DefaultMySqlPool.getInstance(dbConfig, poolConfig, autoCommit, PRINT_MESSAGES);
		};

		PoolManager poolManager = new DefaultPoolManager(pool);

		POOLS.put(database, pool);
		POOL_MANAGERS.put(database, poolManager);
	}

	public void getConnection(Databases database) throws NullPointerException {
		checkDatabase(database);
		POOL_MANAGERS.get(database).getConnection();
	}

	public void putConnection(Databases database) throws NullPointerException {
		checkDatabase(database);

		if (POOLS.containsKey(database))
			POOL_MANAGERS.get(database).putConnection();
	}

	public synchronized void loadSentences(Databases database, String resourceFilename, List<String> sentenceFieldsName)
			throws NullPointerException, MissingPropertyException {
		Map<String, String> sentencesMap = PROPS_READER.getProperties(resourceFilename, sentenceFieldsName);
		DB_SENTENCES.put(database, sentencesMap);
	}

	public String getSentence(Databases database, String sentenceFieldName) throws NullPointerException {
		checkDatabaseSentences(database);

		String sentenceFieldValue = DB_SENTENCES.get(database).get(sentenceFieldName);

		if (sentenceFieldValue != null)
			return sentenceFieldValue;

		throw new NullPointerException("Database sentence not found.");
	}

	public Integer executeUpdate(Databases database, String sentenceFieldName) throws NullPointerException {
		String sentenceFieldValue = getSentence(database, sentenceFieldName);

		checkPoolManager(database);
		return POOL_MANAGERS.get(database).executeUpdate(sentenceFieldValue);

	}

	public Integer executeUpdate(Databases database, String sentenceFieldName, String... params)
			throws NullPointerException {
		String sentenceFieldValue = getSentence(database, sentenceFieldName);

		checkPoolManager(database);
		return POOL_MANAGERS.get(database).executeUpdate(sentenceFieldValue, params);
	}

	public <T> List<T> executeQuery(Databases database, String sentenceFieldName, ResultSetFunction<T> func)
			throws NullPointerException {
		String sentenceFieldValue = getSentence(database, sentenceFieldName);

		checkPoolManager(database);
		return POOL_MANAGERS.get(database).executeQuery(sentenceFieldValue, func);
	}

	public <T> List<T> executeQuery(Databases database, String sentenceFieldName, ResultSetFunction<T> func,
			String... params) throws NullPointerException {
		String sentenceFieldValue = getSentence(database, sentenceFieldName);

		checkPoolManager(database);
		return POOL_MANAGERS.get(database).executeQuery(sentenceFieldValue, func, params);
	}

	public void disconnectAll(Databases database) {
		checkPool(database);
		POOLS.get(database).disconnectAll();
	}
}
