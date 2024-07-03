package datastructures.arraylist;

import util.Console;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ArrayListChallenge {
    public final static int SHUTDOWN = 0, ADD = 1, REMOVE = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> items = new ArrayList<>();
        boolean shutdown = false;
        int number;

        while (!shutdown) {
            printActions();

            while (true) {
                System.out.print("Enter a number for which action you want to do: ");
                String input = scanner.nextLine();

                try {
                    number = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    continue;
                }
                break;
            }
            System.out.println();

            switch (number) {
                case SHUTDOWN -> shutdown = true;
                case ADD -> addItems(items);
                case REMOVE -> removeItems(items);
            }
            System.out.println();

            if (shutdown)
                continue;

            items.sort(Comparator.naturalOrder());
            System.out.println("Items: " + items);

            System.out.println();
            Console.pressEnterToCont();
        }
        scanner.close();
    }

    private static void printActions() {
        Console.clear();
        System.out.println("""
                Available options:
                0 - to shutdown
                1 - to add item(s) to list (comma delimited list)
                2 - to remove any items (comma delimited list)
                """);
    }

    private static void addItems(ArrayList<String> items) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Item(s) to Add: ");
        String input = scanner.nextLine();

        String[] itemsToAdd = input.split(",");

        for (String item : itemsToAdd)
            if (!items.contains(item))
                items.add(item);
    }

    private static void removeItems(ArrayList<String> items) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Item(s) to Remove: ");
        String input = scanner.nextLine();

        String[] itemsToRemove = input.split(",");

        for (String item : itemsToRemove)
            items.remove(item);
    }
}