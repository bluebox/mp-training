package Generics.Compare;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 22, 103));
        students.add(new Student("Bob", 20, 101));
        students.add(new Student("Charlie", 25, 102));
        students.add(new Student("David", 22, 100)); 

        System.out.println("Original List:");
        students.forEach(System.out::println);
        
        
        Comparator<Student> sortByName= (s1,s2) -> s1.getName().compareTo(s2.getName());
        Collections.sort(students, sortByName);
        System.out.println("Sorted by name:");
        students.forEach(System.out::println);
        
        Comparator<Student> sortByAge= (s1,s2) -> Integer.compare(s1.getAge(),s2.getAge());
        Collections.sort(students, sortByAge);
        System.out.println("Sorted by age:");
        students.forEach(System.out::println);
        
        Comparator<Student> sortByAgeThenName = Comparator
                .comparing(Student::getAge)
                .thenComparing(Student::getName);
            Collections.sort(students, sortByAgeThenName);
            System.out.println("Sorted by Age (Asc), then Name:");
            students.forEach(System.out::println);
            
            
            
         Comparator<Student> sortByAgeThenId = Comparator
                    .comparing(Student::getAge)
                    .thenComparing(Student::getId);
                Collections.sort(students, sortByAgeThenId);
                System.out.println("Sorted by Age (Asc), then ID:");
                students.forEach(System.out::println);

	}

}
