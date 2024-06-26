package lambdas;

import java.util.Arrays;
import java.util.function.Consumer;

public class MiniChallenge1 {
    public static void main(String[] args) {
        Consumer<String> printThePartsAnonymous = new Consumer<String>() {
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");

                Arrays.asList(parts).forEach(part -> System.out.println(part));
                System.out.println();
            }
        };

        Consumer<String> printThePartsLambda = sentence -> {
            String[] parts = sentence.split(" ");

            Arrays.asList(parts).forEach(part -> System.out.println(part));
            System.out.println();
        };

        String parts = """
                wheel-hub steering-wheel shock-absorber thermostat filters
                inner-tie-rods outer-tie-rods
                """;

        System.out.println("Anonymous Class Consumer:");
        printThePartsAnonymous.accept(parts);

        System.out.println("Lambda Expression Consumer:");
        printThePartsLambda.accept(parts);
    }
}
