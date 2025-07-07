import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TernaryOperators {
	public static void main( ) {
		List<Student> list=Arrays.asList(
				new Student("Akash",21,9),
				new Student("Ram",32,10),
				new Student("Laxman",28,9.9f),
				new Student("Hanuman",27,10f),
				new Student("Ravi",21,7));
		long  count=list.stream().filter(s->s.getAge()>=20 && s.getAge()<=22).count();
		System.out.println("The count is:"+count);
		List<String> names =list.stream().map(s->s.getName()).collect(Collectors.toList());
		System.out.println("The Names are:");
		names.stream().forEach(System.out::println);
		boolean isAllAbove_18=list.stream().allMatch(s-> s.getAge()>=18);
		boolean isAnyoneFail=list.stream().anyMatch(s->s.getGrade()<=7);
		System.out.println("isAllAbove_18 :"+isAllAbove_18);
		System.out.println("isAnyoneFail :"+isAnyoneFail);
		Comparator<Student> c= (s1,s2)-> (s1.getAge()>s2.getAge())?1:(s1.getAge()<s2.getAge())?-1:0;
		List<Student> sortedByAge = list.stream().sorted(c).collect(Collectors.toList());
		System.out.println("The student list after sorting according to age is:");
		sortedByAge.stream().forEach(System.out::println);
	}
}
