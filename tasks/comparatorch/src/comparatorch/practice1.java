package comparatorch;
import java.util.*;

class Student {
	int roll_no;
	String name;
public Student(int roll_no, String name) {
		
		this.roll_no = roll_no;
		this.name = name;
		
	}

	public String toString() {
		return name+" "+roll_no;
	}	
}
class sortbyroll implements Comparator<Student>{
	public int compare(Student a,Student b) {
		return a.roll_no-b.roll_no;
		
	}
	
public class practice1 {
	public static void main(String[] args) {
		List<Student> students=new ArrayList<>();
		students.add(new Student(21,"Niha"));
		students.add(new Student(19,"meer"));
		students.add(new Student(54,"sai"));
		students.add(new Student(1,"hari"));
		Collections.sort(students, new sortbyroll());
		System.out.println("students sort by roll number:");
		students.forEach(s->System.out.println(s));
	}
}
}
