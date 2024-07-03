package datastructures.linkedlist;

import datastructures.arraylist.Place;
import util.Console;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListChallenge {
    private final static String INIT = "i", ADD = "a", FORWARD = "f", BACKWARD = "b", LIST = "l", QUIT = "q";

    private final LinkedList<Place> places;
    private ListIterator<Place> iter;

    public LinkedListChallenge() {
        this.places = new LinkedList<>();
    }

    public static void main(String[] args) {
        LinkedListChallenge list = new LinkedListChallenge();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean quit = false;

        while (!quit) {
            printActions();
            System.out.print("Enter a Command: ");

            try {
                input = scanner.nextLine().substring(0, 1).toLowerCase();
            } catch (StringIndexOutOfBoundsException e) {
                continue;
            }
            System.out.println();

            if (list.length() == 0 && !input.equals(INIT)) {
                System.out.println("Error: Must Initialize First");
                System.out.println();

                System.out.print("Press ENTER to Continue");
                scanner.nextLine();
                continue;
            }

            switch (input) {
                case INIT -> addFirstPlace(list);
                case ADD -> list.addPlace();
                case FORWARD -> list.moveForward();
                case BACKWARD -> list.moveBackward();
                case LIST -> list.printPlaces();
                case QUIT -> quit = true;
            }
        }

        scanner.close();
    }

    private static void printActions() {
        Console.clear();
        System.out.println("""
                Available actions (select word or letter):
                (I)nit Places
                (A)dd Place
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit
                """);
    }

    private int length() {
        return places.size();
    }

    private void clearPlaces() {
        places.clear();
    }

    public static void addFirstPlace(LinkedListChallenge places) {
        Scanner scanner = new Scanner(System.in);

        places.clearPlaces();

        System.out.print("Enter starting place name: ");
        String startPlaceName = scanner.nextLine();
        System.out.println();

        places.addPlace(startPlaceName, 0);

    }

    public void addPlace() {
        Scanner scanner = new Scanner(System.in);

        String name, input;
        int distance;

        System.out.print("Enter place name: ");
        name = scanner.nextLine();

        for (Place place : places)
            if (place.getName().equals(name)) {
                System.out.println("ERROR: Place already added");
                System.out.println();

                Console.pressEnterToCont();
                return;
            }

        while (true) {
            System.out.print("Enter distance from the starting place: ");
            input = scanner.nextLine();
            System.out.println();

            try {
                distance = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            if (distance > 0)
                break;

            System.out.println("ERROR: Invalid distance. Must be greater than 0");
            System.out.println();

            Console.pressEnterToCont();
            return;
        }

        addPlace(name, distance);
    }

    public void addPlace(String name, int distance) {
        int i = 0;

        // Get Index where Place should be Added
        while (i < places.size()) {
            if (places.get(i).getDistance() > distance)
                break;

            i++;
        }

        places.add(i, new Place(name, distance));
        this.iter = this.places.listIterator();
    }

    private static void printCurrPlace(Place place) {
        System.out.println("Current Place: " + place.getName() + " (" + place.getDistance() + ")");
    }

    public void moveForward() {
        if (iter.hasNext())
            printCurrPlace(iter.next());
        else
            System.out.println("ERROR: Out of range");
        System.out.println();

        Console.pressEnterToCont();
    }

    public void moveBackward() {
        if (iter.hasPrevious())
            printCurrPlace(iter.previous());
        else
            System.out.println("ERROR: Out of range");
        System.out.println();

        Console.pressEnterToCont();
    }

    public void printPlaces() {
        int i = 1;
        System.out.println("Places:");

        for (Place place : places)
            System.out.println(i++ + " -> " + place.getName() + " (" + place.getDistance() + ")");
        System.out.println();

        Console.pressEnterToCont();
    }
}
