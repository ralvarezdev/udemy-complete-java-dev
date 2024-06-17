package practices.dbcomponent;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import practices.MissingPropertyException;
import practices.pool.Databases;
import practices.pool.DefaultPropertiesReader;
import practices.pool.ResultSetFunction;

public class Main {
	public static void main(String[] args) {
		String DB_PROPS_FILENAME = "pools-db.properties";
		String POOL_PROPS_FILENAME = "pools-pool.properties";
		boolean PRINT_MESSAGES = false;

		Databases DB = Databases.POSTGRES;
		String DB_NAME = DB.getDatabaseName();
		String DATABASE_SENTENCES_FILENAME = "dbcomponent-mysql-sentences.properties";

		String SELECT_ALL = "SELECT_ALL";
		String SELECT_ALL_BYID = "SELECT_ALL_BYID";
		String INSERT = "INSERT";

		DefaultPropertiesReader propsReader = new DefaultPropertiesReader();

		// Initialize DB Component
		DefaultDbComponent dbComponent = new DefaultDbComponent(propsReader, DB_PROPS_FILENAME, POOL_PROPS_FILENAME,
				PRINT_MESSAGES);
		LinkedList<String> dbSentences;

		// Load Pool Manager and get connection
		try {
			dbComponent.loadPoolManager(DB);
			dbComponent.getConnection(DB);

			// Load sentences
			dbSentences = new LinkedList<>(Arrays.asList(SELECT_ALL, SELECT_ALL_BYID, INSERT));

			dbComponent.loadSentences(DB, DATABASE_SENTENCES_FILENAME, dbSentences);

		} catch (MissingPropertyException e) {
			System.err.println(e);
			System.exit(-1);
		}

		// - Execute Sentences
		List<String[]> results;

		ResultSetFunction<String[]> func = (result) -> {
			try {
				return new String[] { result.getString("id_producto"), result.getString("de_producto") };

			} catch (SQLException e) {
				System.err.println(e);
			}
			return null;
		};

		try {
			// Select all sentence
			dbComponent.createPreparedStatement(DB, SELECT_ALL);
			results = dbComponent.executeQuery(DB, func);
			dbComponent.closePreparedStatement(DB);

			System.out.println("- Select all sentence from %s".formatted(DB_NAME));
			int lastID = results.size();

			if (lastID > 0) {
				System.out.println(Arrays.deepToString(results.getFirst()));
				System.out.println("...");
				System.out.println(Arrays.deepToString(results.getLast()));
			}
			System.out.println();

			/*
			 * // Insert sentence dbComponent.createPreparedStatement(DB, INSERT);
			 * dbComponent.setStringParameter(DB, 1, "Inserted from DB Component");
			 * dbComponent.executeUpdate(DB); dbComponent.closePreparedStatement(DB);
			 * 
			 * System.out.println("- Inserting to %s".formatted(DB_NAME));
			 * System.out.println(); lastID++;
			 */

			// Select all by ID sentence
			dbComponent.createPreparedStatement(DB, SELECT_ALL_BYID);
			dbComponent.setIntParameter(DB, 1, lastID);
			results = dbComponent.executeQuery(DB, func);
			dbComponent.closePreparedStatement(DB);

			System.out.println("- Select all by ID sentence from %s".formatted(DB_NAME));

			if (results.size() > 0)
				System.out.println(Arrays.deepToString(results.getFirst()));

		} catch (SQLException e) {
			System.err.println(e);
		}

		// Put connection
		dbComponent.putConnection(DB);
		dbComponent.disconnectAll(DB);
	}
}
