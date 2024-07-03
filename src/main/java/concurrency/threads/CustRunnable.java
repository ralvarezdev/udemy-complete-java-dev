package concurrency.threads;

public class CustRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 100; i++)
            try {
                if (i % 2 == 0)
                    System.out.printf("Runnable %-4d%n", i);
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
    }
}
