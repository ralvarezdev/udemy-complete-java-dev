package practices.pool;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import practices.MissingPropertyException;
import practices.files.PropertiesReader;

public enum Databases {
	POSTGRES("POSTGRES"), MYSQL("MYSQL");

	private final String DATABASE_NAME;

	private Databases(String databaseName) {
		DATABASE_NAME = databaseName;
	}

	public String getDatabaseName() {
		return DATABASE_NAME;
	}

	private void checkProps(PropertiesReader propsReader, String propsFilename)
			throws NullPointerException, MissingPropertyException {
		if (propsReader == null)
			throw new NullPointerException("Database properties reader is null.");

		if (propsFilename == null)
			throw new NullPointerException("Database properties filename is null.");
	}

	public Map<String, String> getDatabaseProperties(PropertiesReader propsReader, String propsFilename)
			throws NullPointerException, IOException, MissingPropertyException {
		checkProps(propsReader, propsFilename);

		DatabaseProperties[] dbPropsFields = DatabaseProperties.values();
		LinkedList<String> dbPropsFieldsName = new LinkedList<>();

		for (DatabaseProperties dbPropsField : dbPropsFields)
			dbPropsFieldsName.add(dbPropsField.getFieldName(this));

		return propsReader.getProperties(propsFilename, dbPropsFieldsName);
	}

	public DatabaseConfig getDatabaseConfig(PropertiesReader propsReader, String propsFilename)
			throws NullPointerException, IOException, MissingPropertyException {
		Map<String, String> dbProps = getDatabaseProperties(propsReader, propsFilename);

		// Get properties values
		String DBHOST = dbProps.get(DatabaseProperties.DBHOST.getFieldName(this));
		String DBPORT = dbProps.get(DatabaseProperties.DBPORT.getFieldName(this));
		String DBNAME = dbProps.get(DatabaseProperties.DBNAME.getFieldName(this));
		String DBUSER = dbProps.get(DatabaseProperties.DBUSER.getFieldName(this));
		String DBPASS = dbProps.get(DatabaseProperties.DBPASS.getFieldName(this));

		return new DatabaseConfig(DBHOST, DBPORT, DBNAME, DBUSER, DBPASS);
	}

	public Map<String, String> getDatabasePoolProperties(PropertiesReader propsReader, String propsFilename)
			throws NullPointerException, IOException, MissingPropertyException {
		checkProps(propsReader, propsFilename);

		DatabasePoolProperties[] poolPropsFields = DatabasePoolProperties.values();
		LinkedList<String> poolPropsFieldsName = new LinkedList<>();

		for (DatabasePoolProperties poolPropsField : poolPropsFields)
			poolPropsFieldsName.add(poolPropsField.getFieldName(this));

		return propsReader.getProperties(propsFilename, poolPropsFieldsName);
	}

	public PoolConfig getDatabasePoolConfig(PropertiesReader propsReader, String propsFilename)
			throws NullPointerException, IOException, MissingPropertyException {
		Map<String, String> poolProps = getDatabasePoolProperties(propsReader, propsFilename);

		// Get properties values
		int INIT_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.INIT_CONNS.getFieldName(this)));
		int INCR_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.INCR_CONNS.getFieldName(this)));
		int MAX_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.MAX_CONNS.getFieldName(this)));

		return new PoolConfig(INIT_CONNS, INCR_CONNS, MAX_CONNS);
	}
}
