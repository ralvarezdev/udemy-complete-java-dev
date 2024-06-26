package nestedclasses.localclasses;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Department {
    private enum ContEmployeesSorting {
        YEARS_WORKED, FULL_NAME
    }

    public static void main(String[] args) {
        Random random = new Random();
        LinkedList<Employee> employees = new LinkedList<>();
        String firstName, lastName, hireDate;

        String[] posFirstName = {"Ramon", "David", "John", "Jesus", "Javier", "Rebecca", "Ivana", "Grecia"};
        String[] posLastName = {"Lopez", "Avila", "Alvarez", "Perez", "Garcia", "Gutierrez", "Vera"};

        for (int i = 0; i < 10; i++) {
            firstName = posFirstName[random.nextInt(0, posFirstName.length)];
            lastName = posLastName[random.nextInt(0, posLastName.length)];
            hireDate = "%02d/%02d/%04d".formatted(random.nextInt(1, 28 + 1), random.nextInt(1, 12 + 1),
                    random.nextInt(1980, LocalDate.now().getYear() - 18));

            employees.add(new Employee(firstName, lastName, hireDate));
            // System.out.println("%s %s %s".formatted(firstName, lastName, hireDate));
        }

        printOrderedList(employees, "YEARS_WORKED");
        printOrderedList(employees, "FULL_NAME");
        printOrderedList(employees, "WONT_BE_FOUND");
    }

    public static void printOrderedList(List<Employee> employees, String sortOrder) {
        int currYear = LocalDate.now().getYear();

        class ContainedEmployee {
            final Employee employee;
            final String fullName;
            final int yearsWorked;

            private ContainedEmployee(Employee employee) {
                String hireDate = employee.hireDate();
                int hireDateInt = Integer.parseInt(hireDate.substring(hireDate.length() - 4));

                this.employee = employee;
                this.fullName = employee.firstName() + " " + employee.lastName();
                this.yearsWorked = currYear - hireDateInt;
            }

            @Override
            public String toString() {
                return "%-20s | %-12s | %-3d".formatted(fullName, employee.hireDate(), yearsWorked);
            }
        }

        LinkedList<ContainedEmployee> containedEmployees = new LinkedList<>();
        for (Employee employee : employees)
            containedEmployees.add(new ContainedEmployee(employee));

        var fullNameComparator = new Comparator<ContainedEmployee>() {
            @Override
            public int compare(ContainedEmployee o1, ContainedEmployee o2) {
                return o1.fullName.compareTo(o2.fullName);
            }
        };

        var yearsWorkedComparator = new Comparator<ContainedEmployee>() {
            @Override
            public int compare(ContainedEmployee o1, ContainedEmployee o2) {
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

        ContEmployeesSorting sorting;
        try {
            sorting = ContEmployeesSorting.valueOf(sortOrder.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Sorting option not found for: " + sortOrder);
            return;
        }

        switch (sorting) {
            case YEARS_WORKED -> containedEmployees.sort(yearsWorkedComparator);
            default -> containedEmployees.sort(fullNameComparator);
        }

        System.out.printf("List of Sorted Contained Employees by '%s':%n", sorting);
        for (ContainedEmployee contEmployee : containedEmployees)
            System.out.println(contEmployee);
        System.out.println();
    }
}
