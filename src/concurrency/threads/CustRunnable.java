package concurrency.threads;

public class CustRunnable implements Runnable {
	public void run() {
		for (int i = 1; i <= 100; i++)
			try {
				if (i % 2 == 0)
					System.out.println("Runnable %-4d".formatted(i));
				Thread.sleep(100);

			} catch (Exception e) {
				System.err.println(e);
				break;
			}
	};
}
