import java.util.*;

public class EmployeeSort {
    static class Employee implements Comparable<Employee> {
        String name;
        int age;

        Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int compareTo(Employee other) {
            // return this.name.compareTo(other.name);
            return Integer.compare(this.age, other.age);
        }

        public String toString() {
            return name + " - " + age;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30));
        employees.add(new Employee("Bob", 25));
        employees.add(new Employee("Charlie", 35));

        Collections.sort(employees);

        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}
