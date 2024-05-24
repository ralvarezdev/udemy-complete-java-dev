package practices.connections;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustThread extends Thread {
	@Override
	public void run() {
		Database db = new Database();
		Connection conn = db.getConnection();
		Statement statement = null;

		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			// Fatal error. Close given connection
			System.err.println(e);

			try {
				conn.close();
			} catch (SQLException f) {
				System.err.println(f);
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
			System.out.println("Thread " + this.getName() + " completed and closed sucessfully...");

		} catch (SQLException e) {
			// System.err.println(e);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				System.err.println(e);
			}

			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
