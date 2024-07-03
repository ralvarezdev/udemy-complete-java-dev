package concurrency.threads;

public class Main {
    public static void main(String[] args) {
        Thread custRunnable = new Thread(new CustRunnable());
        CustThread custThread = new CustThread();

        custRunnable.start();
        custThread.start();

        try {
            Thread.sleep(1000);
            custRunnable.interrupt();

            Thread.sleep(2000);
            custThread.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
