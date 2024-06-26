package basics.firststeps;

public class Char {
    public static void main(String[] args) {
        Char.challenge();
    }

    public static void challenge() {
        char mySimpleChar = '?';
        char myUnicodeChar = '\u003f';
        char myDecimalChar = 63;

        System.out.println("Char Challenge");
        System.out.println("My values are: " + mySimpleChar + myUnicodeChar + myDecimalChar);
    }
}
