package challenges_4th_july.StreamsPracticeExample;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class PeopleQueryOperations {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "HR"),
            new Person("Bob", 24, "Engineering"),
            new Person("Charlie", 35, "HR"),
            new Person("David", 29, "Marketing"),
            new Person("Eve", 22, "Engineering")
        );

        // 1. Filter people older than 25
        List<Person> olderPeople = people.stream()
                                         .filter(p -> p.getAge() > 25)
                                         .collect(Collectors.toList());
        System.out.println("People older than 25: " + olderPeople);

        // 2. Map names to uppercase
        List<String> uppercaseNames = people.stream()
                                            .map(p -> p.getName().toUpperCase())
                                            .collect(Collectors.toList());
        System.out.println("Uppercase names: " + uppercaseNames);

        // 3. Group people by department
        Map<String, List<Person>> peopleByDepartment = people.stream()
                                                             .collect(Collectors.groupingBy(Person::getDepartment));
        System.out.println("People by department: " + peopleByDepartment);

        // 4. Calculate average age of all people
        OptionalDouble averageAge = people.stream()
                                          .mapToInt(Person::getAge)
                                          .average();
        averageAge.ifPresent(avg -> System.out.println("Average age: " + avg));

        // 5. Find the oldest person
        people.stream()
              .max((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
              .ifPresent(oldest -> System.out.println("Oldest person: " + oldest));
    }
}
