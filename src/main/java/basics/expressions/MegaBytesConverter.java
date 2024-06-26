package basics.expressions;

// Exercise 3 
public class MegaBytesConverter {
    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        if (kiloBytes < 0) {
            System.out.println("Invalid Value");
            return;
        }

        final int MB_TO_KB = 1024;

        int convMegaBytes = kiloBytes / MB_TO_KB;
        int convKiloBytes = kiloBytes % MB_TO_KB;

        System.out.println(kiloBytes + " KB = " + convMegaBytes + " MB and " + convKiloBytes + " KB");
    }
}
