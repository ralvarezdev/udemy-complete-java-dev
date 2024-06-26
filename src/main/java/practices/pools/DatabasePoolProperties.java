package practices.pools;

public enum DatabasePoolProperties {
    INIT_CONNS("INIT_CONNS"), INCR_CONNS("INCR_CONNS"), MAX_CONNS("MAX_CONNS");

    private final String FIELD_NAME;

    DatabasePoolProperties(String fieldName) {
        FIELD_NAME = fieldName;
    }

    public String getFieldName(Databases database) {
        return "%s_%s".formatted(database.getDatabaseName(), FIELD_NAME);
    }
}
