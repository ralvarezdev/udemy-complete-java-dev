package practices.pool;

import java.sql.SQLException;

public class CustThread extends Thread {
	@Override
	public void run() {
		PoolManager pool = new PoolManager();

		pool.getConnection();
		var prodIds = pool.executeQuery("SELECT * FROM prod", (result) -> {
			try {
				return result.getString("id_producto");

			} catch (SQLException e) {
				System.err.println(e);
			}
			return null;
		});
		pool.putConnection();

		System.out.println("Thread %-5s: %-10d".formatted(this.getName(), prodIds.size()));
	}
}
