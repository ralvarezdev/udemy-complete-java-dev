package practices.dbcomponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

import practices.MissingPropertyException;
import practices.files.PropertiesReader;
import practices.pools.DatabaseConfig;
import practices.pools.Databases;
import practices.pools.DefaultMySqlPool;
import practices.pools.DefaultPoolManager;
import practices.pools.DefaultPostgresPool;
import practices.pools.Pool;
import practices.pools.PoolConfig;
import practices.pools.PoolManager;
import practices.pools.ResultSetFunction;

public class DefaultDbComponent implements DbComponent {
    private final PropertiesReader DBCOMPONENT_PROPS_READER;
    private final PropertiesReader POOL_PROPS_READER;
    private final HashMap<Databases, Pool> POOLS;
    private final HashMap<Databases, PoolManager> POOL_MANAGERS;
    private final HashMap<Databases, Map<String, String>> DB_SENTENCES;
    private final String DB_PROPS_FILENAME;
    private final String POOL_PROPS_FILENAME;
    private final boolean PRINT_POOL_MESSAGES;
    private final boolean PRINT_POOL_MANAGER_MESSAGES;
    private final boolean PRINT_CONNECTION_MESSAGES;

    private Databases DEFAULT_DB = null;

    public DefaultDbComponent(PropertiesReader dbComponentPropsReader,PropertiesReader poolPropsReader, String dbPropsFilename, String poolPropsFilename,
                              boolean printPoolMessages, boolean printPoolManagerMessages, boolean printConnectionMessages)
            throws NullPointerException {
        if (dbPropsFilename == null || poolPropsFilename == null)
            throw new NullPointerException("There are some database configuration null filenames.");

        DBCOMPONENT_PROPS_READER = dbComponentPropsReader;
        POOL_PROPS_READER=poolPropsReader;
        POOLS = new HashMap<>();
        POOL_MANAGERS = new HashMap<>();
        DB_SENTENCES = new HashMap<>();
        DB_PROPS_FILENAME = dbPropsFilename;
        POOL_PROPS_FILENAME = poolPropsFilename;
        PRINT_POOL_MESSAGES = printPoolMessages;
        PRINT_POOL_MANAGER_MESSAGES = printPoolManagerMessages;
        PRINT_CONNECTION_MESSAGES = printConnectionMessages;
    }

    public DefaultDbComponent(List<Databases> databases, PropertiesReader dbComponentPropsReader,PropertiesReader poolPropsReader, String dbPropsFilename,
                              String poolPropsFilename, boolean printPoolMessages, boolean printPoolManagerMessages,
                              boolean printConnectionMessages) throws NullPointerException, IOException, MissingPropertyException {
        this(dbComponentPropsReader,poolPropsReader, dbPropsFilename, poolPropsFilename, printPoolMessages, printPoolManagerMessages,
                printConnectionMessages);

        var databasesSet = Set.copyOf(databases);

        for (Databases database : databasesSet) {
            DB_SENTENCES.put(database, new HashMap<>());
            loadPoolManager(database);
        }
    }

    public DefaultDbComponent(Databases[] databases, PropertiesReader dbComponentPropsReader, PropertiesReader poolPropsReader,String dbPropsFilename,
                              String poolPropsFilename, boolean printPoolMessages, boolean printPoolManagerMessages,
                              boolean printConnectionMessages) throws NullPointerException, IOException, MissingPropertyException {
        this(Arrays.asList(databases), dbComponentPropsReader, poolPropsReader, dbPropsFilename, poolPropsFilename, printPoolMessages,
                printPoolManagerMessages, printConnectionMessages);
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

    private synchronized void checkDefaultDatabase() throws NullPointerException {
        if (DEFAULT_DB == null)
            throw new NullPointerException("Default database hasn't been set.");
    }

    private synchronized PoolManager getPoolManager(Databases database) throws NullPointerException {
        checkPoolManager(database);
        return POOL_MANAGERS.get(database);
    }

    private synchronized Pool getPool(Databases database) throws NullPointerException {
        checkPool(database);
        return POOLS.get(database);
    }

    public synchronized Databases getDefaultDatabases() throws NullPointerException {
        checkDefaultDatabase();
        return DEFAULT_DB;
    }

    public synchronized void setDefaultDatabase(Databases database) throws NullPointerException {
        checkPoolManager(database);
        DEFAULT_DB = database;
    }

    public synchronized void loadPoolManager(Databases database, boolean autoCommit)
            throws NullPointerException, IOException, MissingPropertyException {
        checkDatabase(database);

        if (POOL_MANAGERS.containsKey(database))
            return;

        DatabaseConfig dbConfig = database.getDatabaseConfig(POOL_PROPS_READER, DB_PROPS_FILENAME);
        PoolConfig poolConfig = database.getDatabasePoolConfig(POOL_PROPS_READER, POOL_PROPS_FILENAME);

        Pool pool = switch (database) {
            case POSTGRES -> DefaultPostgresPool.getInstance(dbConfig, poolConfig, autoCommit, PRINT_POOL_MESSAGES,
                    PRINT_CONNECTION_MESSAGES);
            case MYSQL -> DefaultMySqlPool.getInstance(dbConfig, poolConfig, autoCommit, PRINT_POOL_MESSAGES,
                    PRINT_CONNECTION_MESSAGES);
        };

        PoolManager poolManager = new DefaultPoolManager(database, pool, PRINT_POOL_MANAGER_MESSAGES);

        POOLS.put(database, pool);
        POOL_MANAGERS.put(database, poolManager);
    }

    public void getConnection(Databases database) throws NullPointerException {
        getPoolManager(database).getConnection();
    }

    public void putConnection(Databases database) throws NullPointerException {
        getPoolManager(database).putConnection();
    }

    public synchronized void loadSentences(Databases database, String resourceFilename, List<String> sentenceFieldsName)
            throws NullPointerException, IOException, MissingPropertyException {
        Map<String, String> sentencesMap = DBCOMPONENT_PROPS_READER.getProperties(resourceFilename, sentenceFieldsName);
        DB_SENTENCES.put(database, sentencesMap);
    }

    public String getSentence(Databases database, String sentenceFieldName) throws NullPointerException {
        checkDatabaseSentences(database);

        String sentenceFieldValue = DB_SENTENCES.get(database).get(sentenceFieldName);

        if (sentenceFieldValue != null)
            return sentenceFieldValue;

        throw new NullPointerException("Database sentence not found.");
    }

    public synchronized void createPreparedStatement(Databases database, String sentenceFieldName)
            throws NullPointerException {
        String sql = getSentence(database, sentenceFieldName);
        getPoolManager(database).createPreparedStatement(sql);
    }

    public synchronized void closePreparedStatement(Databases database) throws NullPointerException {
        getPoolManager(database).closePreparedStatement();
    }

    public synchronized void setStringParameter(Databases database, int paramCounter, String param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setStringParameter(paramCounter, param);
    }

    public synchronized void setIntParameter(Databases database, int paramCounter, int param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setIntParameter(paramCounter, param);
    }

    public synchronized void setFloatParameter(Databases database, int paramCounter, float param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setFloatParameter(paramCounter, param);
    }

    public synchronized void setDoubleParameter(Databases database, int paramCounter, double param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setDoubleParameter(paramCounter, param);
    }

    public synchronized void setBigDecimalParameter(Databases database, int paramCounter, BigDecimal param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setBigDecimalParameter(paramCounter, param);
    }

    public synchronized void setLongParameter(Databases database, int paramCounter, long param)
            throws NullPointerException, SQLException {
        getPoolManager(database).setLongParameter(paramCounter, param);
    }

    public Integer executeUpdate(Databases database) throws NullPointerException {
        return getPoolManager(database).executeUpdate();
    }

    public <T> List<T> executeQuery(Databases database, ResultSetFunction<T> func) throws NullPointerException {
        return getPoolManager(database).executeQuery(func);
    }

    public void disconnectAll(Databases database) {
        getPool(database).disconnectAll();
    }
}
