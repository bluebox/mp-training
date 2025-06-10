import java.util.Arrays;
import java.util.List;

public class Person {
    String name;
    int age;
    String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String toString() {
        return name + " (" + age + "), " + city;
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "New York"),
            new Person("Bob", 25, "Los Angeles"),
            new Person("Charlie", 35, "Chicago"),
            new Person("Diana", 22, "New York"),
            new Person("Edward", 40, "San Francisco"),
            new Person("Fiona", 28, "Los Angeles")
        );

        
        people.stream()
            .filter(s -> s.age<25 || s.city.equals("New York"))
            .forEach(System.OUT::println);
    }
}
