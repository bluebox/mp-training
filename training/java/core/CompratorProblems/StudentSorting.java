package training.java.core.CompratorProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSorting {
    public static void main(String[] args) {        
        List<Student> students= new ArrayList<>();
        Student s1= new Student("srisai",451,86);
        Student s2= new Student("rahul",438,78);
        Student s3= new Student("badri",407,85);
        Student s4= new Student("ashok",405,81);
        students.addAll(Arrays.asList(s1,s2,s3,s4));
        Collections.sort(students,new SortByMarks());
        System.out.println(students);
    }
}


class Student {
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
    @Override
    public String toString() {
        return "\nStudent [sname=" + sname + ", sid=" + sid + ", smarks=" + smarks + "]";
    }
    
}

class SortById implements Comparator<Student>{
    public int compare(Student a,Student b){
        return Integer.compare(a.getSid(), b.getSid());
    }
}

class SortByName implements Comparator<Student>{
    public int compare(Student a,Student b){
        return a.getSname().compareTo(b.getSname());
    }
}

class SortByMarks implements Comparator<Student>{
    public int compare(Student a,Student b){
        return Integer.compare(a.getSmarks(),b.getSmarks());
    }
}