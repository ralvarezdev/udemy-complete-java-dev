package practices.connections;

import practices.files.ResourcePathGetter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustThread extends Thread {
    private final ResourcePathGetter resourcePathGetter;
    private final String resourceFilename;

    public CustThread(ResourcePathGetter resourcePathGetter, String resourceFilename) {
        this.resourcePathGetter = resourcePathGetter;
        this.resourceFilename = resourceFilename;
    }

    @Override
    public void run() {
        Database db = new Database(resourcePathGetter, resourceFilename);
        Connection conn = db.getConnection();
        Statement statement;

        try {
            statement = conn.createStatement();

        } catch (SQLException e) {
            // Fatal error. Close given connection
            e.printStackTrace();

            try {
                conn.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            return;
        }

        try {
            // Select query
            String selectQuery = """
                    SELECT * FROM prod
                    """;

            // Execute query
            statement.execute(selectQuery);
            System.out.println("Thread " + this.getName() + " completed and closed successfully...");

        } catch (SQLException e) {
            // e.printStackTrace();
        } finally {
            try {
                statement.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
