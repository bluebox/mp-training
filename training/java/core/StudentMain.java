import java.util.Scanner;

class Student {
    private int studentId;
    private String studentName;
    private String studentAddress;
    private String collegeName;

    public Student(int studentId, String studentName, String studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.collegeName = "NIT";
    }

    public Student(int studentId, String studentName, String studentAddress, String collegeName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.collegeName = collegeName;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public String getCollegeName() {
        return collegeName;
    }
}

public class StudentMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student's Id:");
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Student's Name:");
        String name = sc.nextLine();

        System.out.println("Enter Student's address:");
        String address = sc.nextLine();

        System.out.println("Whether the student is from NIT(Yes/No):");
        String isFromNIT = sc.nextLine();

        Student student;

        if (isFromNIT.equalsIgnoreCase("Yes")) {
            student = new Student(id, name, address);
        } else if (isFromNIT.equalsIgnoreCase("No")) {
            System.out.println("Enter the college name:");
            String college = sc.nextLine();
            student = new Student(id, name, address, college);
        } else {
            System.out.println("Wrong Input");
            return;
        }

        System.out.println("Student id:" + student.getStudentId());
        System.out.println("Student name:" + student.getStudentName());
        System.out.println("Address:" + student.getStudentAddress());
        System.out.println("College name:" + student.getCollegeName());
    }
}
