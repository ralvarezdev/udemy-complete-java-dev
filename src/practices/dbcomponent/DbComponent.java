package practices.dbcomponent;

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

	public Integer executeUpdate(Databases database, String sentenceFieldName) throws NullPointerException;

	public Integer executeUpdate(Databases database, String sentenceFieldName, String... params)
			throws NullPointerException;

	public <T> List<T> executeQuery(Databases database, String sentenceFieldName, ResultSetFunction<T> func)
			throws NullPointerException;

	public <T> List<T> executeQuery(Databases database, String sentenceFieldName, ResultSetFunction<T> func,
			String... params) throws NullPointerException;

	public void disconnectAll(Databases database);
}
