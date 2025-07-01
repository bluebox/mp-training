package Day5;
import java.util.*;
class Student implements Comparable<Student>{
	private int id;
	private String name;
	
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
    @Override
    public String toString() {
        return id + " - " + name;
    }


	@Override
	public int compareTo(Student that) {
		// TODO Auto-generated method stub
		return Integer.compare(this.id, that.id);
	}
}

 class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}


public class ComparableComparator {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
        students.add(new Student(3, "raju"));
        students.add(new Student(1, "babu"));
        students.add(new Student(2, "suri"));

        Collections.sort(students);
        System.out.println("Sorted by ID (Comparable):");
        for (Student s : students) {
            System.out.println(s);
        }

        Collections.sort(students, new NameComparator());
        System.out.println("\nSorted by Name (Comparator):");
        for (Student s : students) {
            System.out.println(s);
        }
	}
}
