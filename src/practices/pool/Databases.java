package practices.pool;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

import practices.MissingPropertiesFileException;
import practices.MissingPropertyException;

public enum Databases {
	POSTGRES("postgres");

	private final String DATABASE_NAME;
	private final String DATABASE_PROPERTIES_NAME;
	private final String DATABASE_POOL_PROPERTIES_NAME;

	private Databases(String databaseName) {
		this.DATABASE_NAME = databaseName;

		this.DATABASE_PROPERTIES_NAME = "%s-db.properties".formatted(this.DATABASE_NAME);
		this.DATABASE_POOL_PROPERTIES_NAME = "%s-pool.properties".formatted(this.DATABASE_NAME);
	}

	public Map<String, String> getDatabaseProperties(PropertiesReader propsReader)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		DatabaseProperties[] dbPropsValues = DatabaseProperties.values();
		LinkedList<String> dbPropsFieldsName = new LinkedList<>();

		for (DatabaseProperties dbPropsValue : dbPropsValues)
			dbPropsFieldsName.add(dbPropsValue.getFieldName());

		return propsReader.getProperties(DATABASE_PROPERTIES_NAME, dbPropsFieldsName);
	}

	public DatabaseConfig getDatabaseConfig(PropertiesReader propsReader)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		Map<String, String> dbProps = getDatabaseProperties(propsReader);

		// Get properties values
		String DBHOST = dbProps.get(DatabaseProperties.DBHOST.getFieldName());
		String DBPORT = dbProps.get(DatabaseProperties.DBPORT.getFieldName());
		String DBNAME = dbProps.get(DatabaseProperties.DBNAME.getFieldName());
		String DBUSER = dbProps.get(DatabaseProperties.DBUSER.getFieldName());
		String DBPASS = dbProps.get(DatabaseProperties.DBPASS.getFieldName());

		return new DatabaseConfig(DBHOST, DBPORT, DBNAME, DBUSER, DBPASS);
	}

	public Map<String, String> getDatabasePoolProperties(PropertiesReader propsReader)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		DatabasePoolProperties[] poolPropsValues = DatabasePoolProperties.values();
		LinkedList<String> poolPropsFieldsName = new LinkedList<>();

		for (DatabasePoolProperties poolPropsValue : poolPropsValues)
			poolPropsFieldsName.add(poolPropsValue.getFieldName());

		return propsReader.getProperties(DATABASE_POOL_PROPERTIES_NAME, poolPropsFieldsName);
	}

	public PoolConfig getDatabasePoolConfig(PropertiesReader propsReader)
			throws IOException, MissingPropertyException, MissingPropertiesFileException {
		Map<String, String> poolProps = getDatabasePoolProperties(propsReader);

		// Get properties values
		int INIT_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.INIT_CONNS.getFieldName()));
		int INCR_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.INCR_CONNS.getFieldName()));
		int MAX_CONNS = Integer.parseInt(poolProps.get(DatabasePoolProperties.MAX_CONNS.getFieldName()));

		return new PoolConfig(INIT_CONNS, INCR_CONNS, MAX_CONNS);
	}
}
