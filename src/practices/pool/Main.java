package practices.pool;

import java.util.LinkedList;

public class Main {
	private static final int MAX_THREADS = 1000;

	public static void main(String[] args) {
		LinkedList<CustThread> threads = new LinkedList<>();

		for (int i = 1; i <= MAX_THREADS; i++) {
			CustThread thread = new CustThread();
			threads.add(thread);

			thread.setName("" + i);
			thread.start();
		}

		// Wait for all threads to finish its tasks
		for (CustThread thread : threads)
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println(e);
			}

		// Close all connections
		Pool pool = Pool.getInstance();
		pool.disconnectAll();
	}
}