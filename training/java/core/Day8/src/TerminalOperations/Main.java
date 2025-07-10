package TerminalOperations;

public class Main {
    public static void main(String[] args) {
        Course java = new Course("JMC", "Java Masterclass");
        Course python = new Course("PYMC", "Python Masterclass");

        Student s1 = Student.getRandomStudent(java, python);

        System.out.println("Student ID: " + s1.getId());
        System.out.println("Country: " + s1.getCountry());
        System.out.println("Current Age: " + s1.getAge());
        System.out.println("Java Completion: " + s1.getPercentComplete("JMC") + "%");
    }
}

