import java.util.Arrays;
import java.util.*;

public class GenericsPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> students = Arrays.asList(
			    new Student("Alice", 3.8),
			    new Student("Bob", 3.5),
			    new Student("Charlie", 3.9)
			);
			Collections.sort(students); // Comparable cgpa
			students.sort(new StudentNameComparator()); // Comparator /(Name)

	}
	public static void countTypes(List<?> list) {
	    int strings = 0, integers = 0;
	    for (Object obj : list) {
	        if (obj instanceof String) strings++;
	        else if (obj instanceof Integer) integers++;
	    }
	    System.out.printf("Strings: %d, Integers: %d\n", strings, integers);
	}


}
//generics accepts only numeric
class Stats<T extends Number & Comparable<Number>> {
	//Comparable<Number> makes T ensures can be comparable.
    private T[] numbers;

    public Stats(T[] numbers) {
        this.numbers = numbers;
    }

    public double average() {
        double sum = 0.0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum / numbers.length;
    }
}

//Create a Student class with name and gpa. Sort a list of students using a Comparator<Student> by GPA descending.
class Student implements Comparable<Student>
{
	String name;
	double gpa;
	Student(String name,double gpa)
	{
		this.name=name;
		this.gpa=gpa;
	}
	public String toString()
	{
		return name+" ,gpa :"+gpa;
	}
	public double getGpa()
	{
		return gpa;
	}
	public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa); // Ascending GPA
    }
}

class StudentNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

class GpaDescendingComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        // Descending order: compare s2 to s1
        return Double.compare(s2.getGpa(), s1.getGpa());
    }
}
class GenericMath {
    public static <T extends Number & Comparable<T>> T max(List<T> numbers) {
        if (numbers == null || numbers.isEmpty()) return null;
        T max = numbers.get(0);
        for (T num : numbers) {
            if (num.compareTo(max) > 0) {
                max = num;
            }
        }
        return max;
    }
}



