package lambdas;

import java.util.function.Supplier;

public class MiniChallenge6To7 {
    public static void main(String[] args) {
        // Mini Challenge 6
        Supplier<String> iLoveJava = () -> "I love Java";

        // Mini Challenge 7
        System.out.println(iLoveJava.get());
    }
}
