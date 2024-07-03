package concurrency.threads;

public class CustThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++)
            try {
                if (i % 2 != 0)
                    System.out.printf("Subclass Thread %-4d%n", i);
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
    }
}
