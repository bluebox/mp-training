import java.util.Arrays;
import java.util.List;
public class MethodReference2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list=Arrays.asList("Tarun","ravi","Jagadeesh","balu");
//		list.forEach(MethodExpression2::print);
		list.forEach(Student::new);
	}
}

class Student
{
	String name;
	Student(String name)
	{
		this.name=name;
		System.out.println(name);
	}
}

