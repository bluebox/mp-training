package training.java.core.ComparableProblems;
import java.util.*;
public class StudentProblem {
    public static void main(String[] args) {
        List<Student> students= new ArrayList<>();
        Student s1= new Student("srisai",451,86);
        Student s2= new Student("rahul",438,78);
        Student s3= new Student("badri",407,85);
        Student s4= new Student("ashok",405,81);
        students.addAll(Arrays.asList(s1,s2,s3));
        Collections.sort(students);
        System.out.println(students);
    }
}
class Student implements Comparable<Student>{
    private String sname;
    private int sid;
    private int smarks;
    public Student(String sname,int sid,int smarks){
        this.sname=sname;
        this.sid=sid;
        this.smarks=smarks;
    }
    public String getSname() {
        return sname;
    }
    public int getSid() {
        return sid;
    }
    public int getSmarks() {
        return smarks;
    }
    public int compareTo(Student other){
        return Integer.compare(this.sid, other.sid);
    }
    @Override
    public String toString() {
        return "\nStudent [sname=" + sname + ", sid=" + sid + ", smarks=" + smarks + "]";
    }
    
}
