package Practice.june28;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Topper{

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<Student> students=new ArrayList<Student>();
		try {
			for(int i=1;i<=2;i++) {
				System.out.println("Enter the name of student"+i);
				String name=sc.next();
				sc.nextLine();
				System.out.println("Enter marks obtained by the student(0->100)"+i);
				int marks=sc.nextInt();
				isValid(marks);
				System.out.println();
				students.add(new Student(marks,name));
			}
		}
		catch(InvalidMarksException e) {
			System.out.println(e.getMessage());
			sc.close();
			return;
		}
		int max=0;
		Student topScorrer=students.get(0);
		for(Student s:students) {
			if(max<s.getMarks()) {
				max=s.getMarks();
				topScorrer=s;
			}
		}
		System.out.println("Student "+topScorrer.getsName()+" got first in class with "+topScorrer.getMarks()+" Marks");
		sc.close();
	}
	public static void isValid(int marks) throws InvalidMarksException {
		if(marks<0 ||marks >100) {
			throw new InvalidMarksException(" Marks entered: "+marks+" are invalid!!!\n Please enter valid marks");
		}
	}

}
