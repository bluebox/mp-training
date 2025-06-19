package streams;

import java.util.*;
import java.util.stream.*;

public class StreamExample {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(1, "Alice", "IT", 80000, List.of("Java", "Spring", "Docker")),
            new Employee(2, "Bob", "Finance", 60000, List.of("Excel", "Finance", "SQL")),
            new Employee(3, "Charlie", "IT", 95000, List.of("Java", "Kubernetes")),
            new Employee(4, "David", "HR", 50000, List.of("Recruitment", "Communication")),
            new Employee(5, "Eva", "IT", 105000, List.of("Java", "Spring Boot", "Cloud")),
            new Employee(6, "Frank", "Finance", 65000, List.of("Accounting", "Excel")),
            new Employee(7, "Grace", "IT", 75000, List.of("Python", "ML", "AI"))
        );
        System.out.println("\nIT Employees:");
        employees.stream()
                .filter(e -> e.department.equals("IT"))
                .forEach(System.out::println);

        System.out.println("\nEmployee Names:");
        employees.stream()
                .map(e -> e.name)
                .forEach(System.out::println);

        System.out.println("\nUnique Skills:");
        employees.stream()
                .flatMap(e -> e.skills.stream())
                .distinct()
                .forEach(System.out::println);

        
        System.out.println("\nSorted by salary (descending):");
        employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.salary, e1.salary))
                .forEach(System.out::println);

       
        System.out.println("\nTop 3 highest paid:");
        employees.stream()
                .sorted(Comparator.comparingDouble(e -> -e.salary))
                .limit(3)
                .forEach(System.out::println);

        
        System.out.println("\nGroup by Department:");
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(e -> e.department));
        grouped.forEach((dept, emps) -> {
            System.out.println(dept + ":");
            emps.forEach(System.out::println);
        });

        
        System.out.println("\nNames joined:");
        String names = employees.stream()
                .map(e -> e.name)
                .collect(Collectors.joining(", "));
        System.out.println(names);

        
        System.out.println("\nPartitioned by salary > 80000:");
        Map<Boolean, List<Employee>> partitioned = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.salary > 80000));
        partitioned.forEach((key, val) -> {
            System.out.println(key ? "High Earners:" : "Others:");
            val.forEach(System.out::println);
        });

        System.out.println("\nSalaries :");
        DoubleSummaryStatistics stats = employees.stream()
                .collect(Collectors.summarizingDouble(e -> e.salary));
        System.out.println("Avg: " + stats.getAverage());
        System.out.println("Max: " + stats.getMax());
        System.out.println("Min: " + stats.getMin());
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: " + stats.getSum());

        System.out.println("\nTotal Salary (reduce):");
        double totalSalary = employees.stream()
                .map(e -> e.salary)
                .reduce(0.0, Double::sum);
        System.out.println("Total: $" + totalSalary);

        
        System.out.println("\nHR " +
            employees.stream().anyMatch(e -> e.department.equals("HR")));
        System.out.println("All IT? " +
            employees.stream().allMatch(e -> e.department.equals("IT")));
        System.out.println("None earns < 40000? " +
            employees.stream().noneMatch(e -> e.salary < 40000));

        
        System.out.println("Max usage:");
        employees.stream()
                .filter(e -> e.salary > 80000)
                .peek(e -> System.out.println("Filtered: " + e.name))
                .map(e -> e.name.toUpperCase())
                .peek(e -> System.out.println("Mapped: " + e))
                .forEach(System.out::println);

        
        System.out.println("Parallel Stream:");
        employees.parallelStream()
                .map(e -> e.name)
                .forEach(System.out::println);
    }
}
