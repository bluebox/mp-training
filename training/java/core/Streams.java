import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams{
    public static void main(String[] args) {
        List<String> fruits= Arrays.asList("apple","banana","kiwi","mango","avocado","Mulberry");
        System.out.println("fruits count is "+fruits.stream().count());

        System.out.println("\nFruits names start with a:");
        List<String> fruit=fruits.stream().filter(f-> f.startsWith("a")).toList();
        fruit.forEach(System.out::println);
        System.out.println();

        List<Integer> numbers= Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> ns= numbers.stream().filter(i->(i%2!=0)).map(i->i*i).toList();
        ns.forEach(System.out::println);
        System.out.println();

        List<String> names=Arrays.asList("ramu","raju","ramesh","rajesh","ravi");
        names.stream().filter(s->s.length()==4).map(String::toUpperCase).forEach(System.out::println);

        List<List<String>> courses=Arrays.asList(Arrays.asList("Java", "Python", "C++", "Go"),
                Arrays.asList("Data Structures", "Algorithms", "Database Systems"),
                Arrays.asList("HTML", "CSS", "JavaScript", "React"),
                Arrays.asList("Communication", "Teamwork", "Time Management"));

        System.out.println("\nAll courses are:");
        courses.stream().flatMap(List::stream).forEach(System.out::println);

        System.out.println("\nSorted Courses:");
        courses.stream().flatMap(List::stream).sorted().forEach(System.out::println);

        long count = courses.stream().flatMap(List::stream).count();
        System.out.println("\nTotal number of courses: " + count);
    }
}