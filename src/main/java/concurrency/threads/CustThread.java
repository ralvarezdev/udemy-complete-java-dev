package concurrency.threads;

public class CustThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++)
            try {
                if (i % 2 != 0)
                    System.out.println("Subclass Thread %-4d".formatted(i));
                Thread.sleep(100);

            } catch (InterruptedException e) {
                System.err.println(e);
                break;
            }
    }
}
