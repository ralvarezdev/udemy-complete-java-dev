package practices.dbcomponent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import practices.MissingPropertyException;
import practices.files.DefaultPropertiesReader;
import practices.pools.Databases;
import practices.pools.Pool;
import practices.pools.ResultSetFunction;

public class Main {
    public static void main(String[] args) {
        String DB_PROPS_FILENAME = "pools-db.properties";
        String POOL_PROPS_FILENAME = "pools-pool.properties";
        boolean PRINT_POOL_MESSAGES = true;
        boolean PRINT_POOL_MANAGER_MESSAGES = true;
        boolean PRINT_CONNECTION_MESSAGES = true;

        Databases DB = Databases.POSTGRES;
        String DB_NAME = DB.getDatabaseName();
        String DATABASE_SENTENCES_FILENAME = "dbcomponent-mysql-sentences.properties";

        String SELECT_ALL = "SELECT_ALL";
        String SELECT_ALL_BYID = "SELECT_ALL_BYID";
        String INSERT = "INSERT";

        DefaultPropertiesReader<Pool> poolPropsReader = new DefaultPropertiesReader<>(Pool.class);
        DefaultPropertiesReader<DbComponent> dbComponentPropsReader = new DefaultPropertiesReader<>(DbComponent.class);

        // Initialize DB Component
        DefaultDbComponent dbComponent = new DefaultDbComponent(dbComponentPropsReader, poolPropsReader, DB_PROPS_FILENAME, POOL_PROPS_FILENAME,
                PRINT_POOL_MESSAGES, PRINT_POOL_MANAGER_MESSAGES, PRINT_CONNECTION_MESSAGES);

        // Load Pool Manager, sentences and get connection
        LinkedList<String> dbSentences;

        try {
            dbComponent.loadPoolManager(DB);
            dbComponent.setDefaultDatabase(DB);
            dbComponent.getConnection();

            // Load sentences
            dbSentences = new LinkedList<>(Arrays.asList(SELECT_ALL, SELECT_ALL_BYID, INSERT));

            dbComponent.loadSentences(DATABASE_SENTENCES_FILENAME, dbSentences);

        } catch (IOException | MissingPropertyException e) {
            System.err.println(e);
            System.exit(-1);
        }

        // - Execute Sentences
        List<String[]> results;

        ResultSetFunction<String[]> func = (result) -> {
            try {
                return new String[]{result.getString("id_producto"), result.getString("de_producto")};

            } catch (SQLException e) {
                System.err.println(e);
            }
            return null;
        };

        try {
            // Select all sentence
            dbComponent.createPreparedStatement(SELECT_ALL);
            results = dbComponent.executeQuery(func);
            dbComponent.closePreparedStatement();

            System.out.printf("- Select all sentence from %s%n", DB_NAME);
            int lastID = results.size();

            if (lastID > 0) {
                System.out.println(Arrays.deepToString(results.getFirst()));
                System.out.println("...");
                System.out.println(Arrays.deepToString(results.getLast()));
            }
            System.out.println();

            /*
             * // Insert sentence dbComponent.createPreparedStatement(INSERT);
             * dbComponent.setStringParameter(1, "Inserted from DB Component");
             * dbComponent.executeUpdate(); dbComponent.closePreparedStatement(DB);
             *
             * System.out.println("- Inserting to %s".formatted(DB_NAME));
             * System.out.println(); lastID++;
             */

            // Select all by ID sentence
            dbComponent.createPreparedStatement(SELECT_ALL_BYID);
            dbComponent.setIntParameter(1, lastID);
            results = dbComponent.executeQuery(func);
            dbComponent.closePreparedStatement();

            System.out.printf("- Select all by ID sentence from %s%n", DB_NAME);

            if (!results.isEmpty())
                System.out.println(Arrays.deepToString(results.getFirst()));

        } catch (SQLException e) {
            System.err.println(e);
        }

        // Put connection
        dbComponent.putConnection();
        dbComponent.disconnectAll();
    }
}
