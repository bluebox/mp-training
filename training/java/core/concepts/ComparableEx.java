package concepts;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Student implements Comparable<Student>{
	int age;
	String name;
	public Student(int age, String name) {
		this.age=age;
		this.name=name;
	}
	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Student that) {
		if(this.age> that.age) return 1;
		else return -1;
	}
	
}
public class ComparableEx {

	public static void main(String[] args) {
		List<Student> stud= new ArrayList<>();
		stud.add(new Student(21,"Vighnesh"));
		stud.add(new Student(22,"Isha"));
		stud.add(new Student(18,"Ritha"));
		stud.add(new Student(23,"Khanna"));
		System.out.println("Before sorting using Comparable:");
		for(Student s : stud)
			System.out.println(s);
		Collections.sort(stud);
		System.out.println("After sorting using Comparable:");
		for(Student s : stud)
			System.out.println(s);
		System.out.println();

		List<Student> studs=new ArrayList<>();
		studs.add(new Student(21,"Vighnesh"));
		studs.add(new Student(22,"Isha"));
		studs.add(new Student(18,"Ritha"));
		studs.add(new Student(23,"Khanna"));
		Comparator<Student> comp=(Student i, Student j)-> {
			if(i.age>j.age) return 1;
			else return -1;
		};
		System.out.println("Before sorting using Comparator:");
		for(Student s : studs)
			System.out.println(s);
		Collections.sort(studs,comp);
		System.out.println("After sorting using Comparator:");
		for(Student s : studs)
			System.out.println(s);
	}

}
