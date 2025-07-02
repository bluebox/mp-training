package Day_1_7_25;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        QueryList<Student> studentList = new QueryList<>();

        studentList.addItem(new Student("S001", "prabhas", "Math"));
        studentList.addItem(new Student("S002", "new", "Biology"));
       

        System.out.println("Students matching 'math':");
        List<Student> mathStudents = studentList.getMatches("math");
        for (Student s : mathStudents) {
            System.out.println(s);
        }
    
}}


