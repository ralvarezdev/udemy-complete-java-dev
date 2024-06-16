package practices.pool;

import java.sql.SQLException;

public class CustThread extends Thread {
	private final PoolManager POOL_MANAGER;

	public CustThread(PoolManager poolManager) {
		POOL_MANAGER = poolManager;
	}

	@Override
	public void run() {
		POOL_MANAGER.getConnection();

		var prodIds = POOL_MANAGER.executeQuery("SELECT * FROM prod", (result) -> {
			try {
				return result.getString("id_producto");

			} catch (SQLException e) {
				System.err.println(e);
			}
			return null;
		});
		POOL_MANAGER.putConnection();

		System.out.println("Thread %-5s: %-10d".formatted(this.getName(), prodIds.size()));
	}
}
