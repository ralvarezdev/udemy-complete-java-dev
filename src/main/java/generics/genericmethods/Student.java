package generics.genericmethods;

import java.util.Random;

public class Student implements QueryItem, Comparable<Student> {
    private final int studentId;
    private final String name;
    private final String course;
    private final int yearStarted;

    protected static Random random = new Random();
    protected static int studentIdCounter = 1;

    private static final String[] firstNames = {"Ann", "Bill", "Cathy", "John", "Tim"};
    private static final String[] courses = {"C++", "Java", "Python"};

    public Student() {
        int lastNameIndex = random.nextInt(65, 91);
        studentId = studentIdCounter++;
        name = firstNames[random.nextInt(5)] + " " + (char) lastNameIndex;
        course = courses[random.nextInt(3)];
        yearStarted = random.nextInt(2018, 2023);
    }

    @Override
    public String toString() {
        return "%-10d %-15s %-15s %d".formatted(studentId, name, course, yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        String fName = fieldName.toUpperCase();
        return switch (fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == Integer.parseInt(value);
            case "ID" -> studentId == Integer.parseInt(value);
            default -> false;
        };
    }

    @Override
    public int compareTo(Student s) {
        return Integer.valueOf(studentId).compareTo(Integer.valueOf(s.studentId));
    }
}
