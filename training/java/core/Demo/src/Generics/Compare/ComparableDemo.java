package Generics.Compare;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ComparableDemo {
    public static void main(String[] args) {
        List<StudentComparable> students = new ArrayList<>();
        students.add(new StudentComparable("Alice", 22));
        students.add(new StudentComparable("Bob", 20));
        students.add(new StudentComparable("Charlie", 25));
        students.add(new StudentComparable("David", 22));

        System.out.println("Before sorting (natural order by age):");
        students.forEach(System.out::println);

        Collections.sort(students); 
        
        System.out.println("\nAfter sorting (natural order by age):");
        students.forEach(System.out::println);
    }
}
