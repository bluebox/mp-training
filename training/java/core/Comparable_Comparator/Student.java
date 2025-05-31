package Comparable_Comparator;
import java.util.Arrays;

class Student implements Comparable<Student> {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int compareTo(Student s) {
        return s.id - this.id ;  // sort by id
    }

    public String toString() {
        return id + " " + name;
    }

    public static void main(String[] args) {
        Student s1 = new Student(2, "Alice");
        Student s2 = new Student(1, "Bob");
        Student s3 = new Student(3, "Charlie");

        Student[] students = {s1, s2, s3};
        Arrays.sort(students); // uses compareTo

        for (Student s : students)
            System.out.println(s);
    }
}
