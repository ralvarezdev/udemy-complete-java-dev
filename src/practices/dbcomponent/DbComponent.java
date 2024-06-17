package practices.dbcomponent;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import practices.MissingPropertyException;
import practices.pool.Databases;
import practices.pool.ResultSetFunction;

public interface DbComponent {
	public Databases getDefaultDatabases() throws NullPointerException;

	public void setDefaultDatabase(Databases database) throws NullPointerException;

	public void loadPoolManager(Databases database, boolean autoCommit)
			throws NullPointerException, MissingPropertyException;

	public default void loadPoolManager(Databases database) throws NullPointerException, MissingPropertyException {
		loadPoolManager(database, true);
	}

	public void getConnection(Databases database) throws NullPointerException, MissingPropertyException;

	public default void getConnection() throws NullPointerException, MissingPropertyException {
		Databases database = getDefaultDatabases();
		getConnection(database);
	}

	public void putConnection(Databases database) throws NullPointerException;

	public default void putConnection() throws NullPointerException {
		Databases database = getDefaultDatabases();
		putConnection(database);
	}

	public void loadSentences(Databases database, String resourceFilename, List<String> sentenceFieldsName)
			throws NullPointerException, MissingPropertyException;

	public default void loadSentences(String resourceFilename, List<String> sentenceFieldsName)
			throws NullPointerException, MissingPropertyException {
		Databases database = getDefaultDatabases();
		loadSentences(database, resourceFilename, sentenceFieldsName);
	}

	public default void loadSentences(Databases database, String resourceFilename, String... sentenceFieldsName)
			throws NullPointerException, MissingPropertyException {
		loadSentences(database, resourceFilename, Arrays.asList(sentenceFieldsName));
	}

	public default void loadSentences(String resourceFilename, String... sentenceFieldsName)
			throws NullPointerException, MissingPropertyException {
		loadSentences(resourceFilename, Arrays.asList(sentenceFieldsName));
	}

	public String getSentence(Databases database, String sentenceFieldName) throws NullPointerException;

	public default String getSentence(String sentenceFieldName) throws NullPointerException {
		Databases database = getDefaultDatabases();
		return getSentence(database, sentenceFieldName);
	}

	public void createPreparedStatement(Databases database, String sentenceFieldName) throws NullPointerException;

	public default void createPreparedStatement(String sentenceFieldName) throws NullPointerException {
		Databases database = getDefaultDatabases();
		createPreparedStatement(database, sentenceFieldName);
	}

	public void closePreparedStatement(Databases database) throws NullPointerException;

	public default void closePreparedStatement() throws NullPointerException {
		Databases database = getDefaultDatabases();
		closePreparedStatement(database);
	}

	public void setStringParameter(Databases database, int paramCounter, String param)
			throws NullPointerException, SQLException;

	public default void setStringParameter(int paramCounter, String param) throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setStringParameter(database, paramCounter, param);
	}

	public void setIntParameter(Databases database, int paramCounter, int param)
			throws NullPointerException, SQLException;

	public default void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setIntParameter(database, paramCounter, param);
	}

	public void setFloatParameter(Databases database, int paramCounter, float param)
			throws NullPointerException, SQLException;

	public default void setFloatParameter(int paramCounter, float param) throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setFloatParameter(database, paramCounter, param);
	}

	public void setDoubleParameter(Databases database, int paramCounter, double param)
			throws NullPointerException, SQLException;

	public default void setDoubleParameter(int paramCounter, double param) throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setDoubleParameter(database, paramCounter, param);
	}

	public void setBigDecimalParameter(Databases database, int paramCounter, BigDecimal param)
			throws NullPointerException, SQLException;

	public default void setBigDecimalParameter(int paramCounter, BigDecimal param)
			throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setBigDecimalParameter(database, paramCounter, param);
	}

	public void setLongParameter(Databases database, int paramCounter, long param)
			throws NullPointerException, SQLException;

	public default void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException {
		Databases database = getDefaultDatabases();
		setLongParameter(database, paramCounter, param);
	}

	public Integer executeUpdate(Databases database) throws NullPointerException;

	public default Integer executeUpdate() throws NullPointerException {
		Databases database = getDefaultDatabases();
		return executeUpdate(database);
	}

	public <T> List<T> executeQuery(Databases database, ResultSetFunction<T> func) throws NullPointerException;

	public default <T> List<T> executeQuery(ResultSetFunction<T> func) throws NullPointerException {
		Databases database = getDefaultDatabases();
		return executeQuery(database, func);
	}

	public void disconnectAll(Databases database);

	public default void disconnectAll() {
		Databases database = getDefaultDatabases();
		disconnectAll(database);
	}

}
