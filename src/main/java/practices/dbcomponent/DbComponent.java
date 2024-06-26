package practices.dbcomponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import practices.MissingPropertyException;
import practices.pools.Databases;
import practices.pools.ResultSetFunction;

public interface DbComponent {
    Databases getDefaultDatabases() throws NullPointerException;

    void setDefaultDatabase(Databases database) throws NullPointerException;

    void loadPoolManager(Databases database, boolean autoCommit)
            throws NullPointerException, IOException, MissingPropertyException;

    default void loadPoolManager(Databases database)
            throws NullPointerException, IOException, MissingPropertyException {
        loadPoolManager(database, true);
    }

    void getConnection(Databases database) throws NullPointerException, MissingPropertyException;

    default void getConnection() throws NullPointerException, MissingPropertyException {
        Databases database = getDefaultDatabases();
        getConnection(database);
    }

    void putConnection(Databases database) throws NullPointerException;

    default void putConnection() throws NullPointerException {
        Databases database = getDefaultDatabases();
        putConnection(database);
    }

    void loadSentences(Databases database, String resourceFilename, List<String> sentenceFieldsName)
            throws NullPointerException, IOException, MissingPropertyException;

    default void loadSentences(String resourceFilename, List<String> sentenceFieldsName)
            throws NullPointerException, IOException, MissingPropertyException {
        Databases database = getDefaultDatabases();
        loadSentences(database, resourceFilename, sentenceFieldsName);
    }

    default void loadSentences(Databases database, String resourceFilename, String... sentenceFieldsName)
            throws NullPointerException, IOException, MissingPropertyException {
        loadSentences(database, resourceFilename, Arrays.asList(sentenceFieldsName));
    }

    default void loadSentences(String resourceFilename, String... sentenceFieldsName)
            throws NullPointerException, IOException, MissingPropertyException {
        loadSentences(resourceFilename, Arrays.asList(sentenceFieldsName));
    }

    String getSentence(Databases database, String sentenceFieldName) throws NullPointerException;

    default String getSentence(String sentenceFieldName) throws NullPointerException {
        Databases database = getDefaultDatabases();
        return getSentence(database, sentenceFieldName);
    }

    void createPreparedStatement(Databases database, String sentenceFieldName) throws NullPointerException;

    default void createPreparedStatement(String sentenceFieldName) throws NullPointerException {
        Databases database = getDefaultDatabases();
        createPreparedStatement(database, sentenceFieldName);
    }

    void closePreparedStatement(Databases database) throws NullPointerException;

    default void closePreparedStatement() throws NullPointerException {
        Databases database = getDefaultDatabases();
        closePreparedStatement(database);
    }

    void setStringParameter(Databases database, int paramCounter, String param)
            throws NullPointerException, SQLException;

    default void setStringParameter(int paramCounter, String param) throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setStringParameter(database, paramCounter, param);
    }

    void setIntParameter(Databases database, int paramCounter, int param)
            throws NullPointerException, SQLException;

    default void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setIntParameter(database, paramCounter, param);
    }

    void setFloatParameter(Databases database, int paramCounter, float param)
            throws NullPointerException, SQLException;

    default void setFloatParameter(int paramCounter, float param) throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setFloatParameter(database, paramCounter, param);
    }

    void setDoubleParameter(Databases database, int paramCounter, double param)
            throws NullPointerException, SQLException;

    default void setDoubleParameter(int paramCounter, double param) throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setDoubleParameter(database, paramCounter, param);
    }

    void setBigDecimalParameter(Databases database, int paramCounter, BigDecimal param)
            throws NullPointerException, SQLException;

    default void setBigDecimalParameter(int paramCounter, BigDecimal param)
            throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setBigDecimalParameter(database, paramCounter, param);
    }

    void setLongParameter(Databases database, int paramCounter, long param)
            throws NullPointerException, SQLException;

    default void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException {
        Databases database = getDefaultDatabases();
        setLongParameter(database, paramCounter, param);
    }

    Integer executeUpdate(Databases database) throws NullPointerException;

    default Integer executeUpdate() throws NullPointerException {
        Databases database = getDefaultDatabases();
        return executeUpdate(database);
    }

    <T> List<T> executeQuery(Databases database, ResultSetFunction<T> func) throws NullPointerException;

    default <T> List<T> executeQuery(ResultSetFunction<T> func) throws NullPointerException {
        Databases database = getDefaultDatabases();
        return executeQuery(database, func);
    }

    void disconnectAll(Databases database);

    default void disconnectAll() {
        Databases database = getDefaultDatabases();
        disconnectAll(database);
    }

}
