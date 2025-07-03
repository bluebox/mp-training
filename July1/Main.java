package July1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        QueryList<Student> studentList = new QueryList<>();

        studentList.addItem(new Student("101", "Sahithi", "ML"));
        studentList.addItem(new Student("102", "Manaswini", "NLP"));
       

        System.out.println("Students matching 'ML':");
        List<Student> MLStudents = studentList.getMatches("ml");
        for (Student s : MLStudents) {
            System.out.println(s);
        }
        
        
        QueryList<LPAStudent> lpastudentList = new QueryList<>();

        lpastudentList.addItem(new LPAStudent("101", "Sahithi", "Social", 8.56));
        lpastudentList.addItem(new LPAStudent("102", "Manaswini", "Science", 9.56));
       

        System.out.println("Students matching 'Science':");
        List<LPAStudent> scienceStudents = lpastudentList.getMatches("science");
        for (Student s : scienceStudents) {
            System.out.println(s);
        }
    
}}