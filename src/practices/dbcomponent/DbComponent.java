package practices.dbcomponent;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import practices.MissingPropertyException;
import practices.pool.Databases;
import practices.pool.ResultSetFunction;

public interface DbComponent {
	public void loadPoolManager(Databases database, boolean autoCommit)
			throws NullPointerException, MissingPropertyException;

	public default void loadPoolManager(Databases database) throws NullPointerException, MissingPropertyException {
		loadPoolManager(database, true);
	}

	public void getConnection(Databases database) throws NullPointerException, MissingPropertyException;

	public void putConnection(Databases database) throws NullPointerException;

	public void loadSentences(Databases database, String resourceFilename, List<String> sentenceFieldsName)
			throws NullPointerException, MissingPropertyException;

	public default void loadSentences(Databases database, String resourceFilename, String... sentenceFieldsName)
			throws NullPointerException, MissingPropertyException {
		loadSentences(database, resourceFilename, Arrays.asList(sentenceFieldsName));
	}

	public String getSentence(Databases database, String sentenceFieldName) throws NullPointerException;

	public void createPreparedStatement(Databases database, String sentenceFieldName) throws NullPointerException;

	public void closePreparedStatement(Databases database) throws NullPointerException;

	public void setStringParameter(Databases database, int paramCounter, String param)
			throws NullPointerException, SQLException;

	public void setIntParameter(Databases database, int paramCounter, int param)
			throws NullPointerException, SQLException;

	public void setFloatParameter(Databases database, int paramCounter, float param)
			throws NullPointerException, SQLException;

	public void setDoubleParameter(Databases database, int paramCounter, double param)
			throws NullPointerException, SQLException;

	public void setBigDecimalParameter(Databases database, int paramCounter, BigDecimal param)
			throws NullPointerException, SQLException;

	public void setLongParameter(Databases database, int paramCounter, long param)
			throws NullPointerException, SQLException;

	public Integer executeUpdate(Databases database) throws NullPointerException;

	public <T> List<T> executeQuery(Databases database, ResultSetFunction<T> func) throws NullPointerException;

	public void disconnectAll(Databases database);
}
