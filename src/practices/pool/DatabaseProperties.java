package practices.pool;

public enum DatabaseProperties {
	DBHOST("DBHOST"), DBPORT("DBPORT"), DBNAME("DBNAME"), DBUSER("DBUSER"), DBPASS("DBPASS");

	private final String fieldName;

	private DatabaseProperties(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return this.fieldName;
	}
}
