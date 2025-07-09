import java.util.Arrays;
import java.util.Comparator;

public class ComparatorExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student students[]= {
				new Student("sreeja",101,400),
				new Student("chandana",105,300),
				new Student("hemanth",102,300),
				new Student("vignesh",104,500)
		};
		Comparator<Student>cp=(Student o1, Student o2)-> {
				return o1.name.compareTo(o2.name);
		};
			
		Arrays.sort(students,cp);
	System.out.println(Arrays.toString(students));
}
	}
