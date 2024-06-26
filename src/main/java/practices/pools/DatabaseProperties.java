package practices.pools;

public enum DatabaseProperties {
    DBHOST("DBHOST"), DBPORT("DBPORT"), DBNAME("DBNAME"), DBUSER("DBUSER"), DBPASS("DBPASS");

    private final String FIELD_NAME;

    DatabaseProperties(String fieldName) {
        FIELD_NAME = fieldName;
    }

    public String getFieldName(Databases database) {
        return "%s_%s".formatted(database.getDatabaseName(), FIELD_NAME);
    }
}
