package comparatorch;
import java.util.*;
class Student1{
	String name;
	Integer age;
	public Student1( Integer age,String name) {
		
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	

	public String toString() {
		return name+":"+age;
	}
	
	
	
}
//class comparebyname implements Comparator<Student1>{
//	public int compare(Student1 a,Student1 b) {
//		int namecompare=a.getName().compareTo(b.getName());
//		int agecompare=a.getAge().compareTo(b.getAge());
//		
//		return namecompare==0 ? agecompare:namecompare;
//	}
//	
//}

public class practice2 {
	public static void main(String[] args) {
		List<Student1> Student1s=new ArrayList<>();
		Student1s.add(new Student1(21,"niha"));
		Student1s.add(new Student1(19,"meer"));
		Student1s.add(new Student1(54,"sai"));
		Student1s.add(new Student1(1,"hari"));
		Student1s.add(new Student1(20,"hari"));
		Student1s.sort(Comparator.comparing(Student1::getName).thenComparing(Student1::getAge));
		System.out.println("sorted order of Student1s:");
		Student1s.forEach(s->System.out.println(s));
	}

}
