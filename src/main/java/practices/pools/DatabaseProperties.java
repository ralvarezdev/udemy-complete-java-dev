package practices.pools;

import org.jetbrains.annotations.NotNull;

public enum DatabaseProperties {
    DBHOST("DBHOST"), DBPORT("DBPORT"), DBNAME("DBNAME"), DBUSER("DBUSER"), DBPASS("DBPASS");

    private final String FIELD_NAME;

    DatabaseProperties(String fieldName) {
        FIELD_NAME = fieldName;
    }

    public String getFieldName(@NotNull DatabaseTags database) {
        return "%s_%s".formatted(database.getFieldDatabaseTagName(), FIELD_NAME);
    }
}
