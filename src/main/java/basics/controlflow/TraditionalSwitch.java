package basics.controlflow;

public class TraditionalSwitch {
    public static void main(String[] args) {
        TraditionalSwitch.challenge();
    }

    public static void challenge() {
        String word = "ralvarezdev";

        System.out.println("Traditional Switch Challenge");

        for (int i = 0; i < word.length(); i++)
            System.out.print(nato(word.charAt(i)) + " ");
    }

    private static String nato(char c) {
        switch (Character.toLowerCase(c)) {
            case 'a':
                return "Able";
            case 'b':
                return "Baker";
            case 'c':
                return "Charlie";
            case 'd':
                return "Dog";
            case 'e':
                return "Easy";
            case 'f':
                return "Fox";
            case 'g':
                return "George";
            case 'h':
                return "How";
            case 'i':
                return "Item";
            case 'j':
                return "Jig";
            case 'k':
                return "King";
            case 'l':
                return "Love";
            case 'm':
                return "Mike";
            case 'n':
                return "Nan";
            case 'o':
                return "Oboe";
            case 'p':
                return "Peter";
            case 'q':
                return "Queen";
            case 'r':
                return "Roger";
            case 's':
                return "Sugar";
            case 't':
                return "Tare";
            case 'u':
                return "Uncle";
            case 'v':
                return "Victor";
            case 'w':
                return "William";
            case 'x':
                return "X-ray";
            case 'y':
                return "Yoke";
            case 'z':
                return "Zebra";
            default:
                return "Not Found";
        }
    }
}
