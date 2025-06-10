import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.Map;
import java.util.stream.Stream;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;




public class Main{
    public static void main(String[] args){
        Random random = new Random();
        Course java=new Course("J","JAVA");
        Course py=new Course("PY","PYTHON",50);
        Course dbms=new Course("DBMS","DATA BASE",65);
        Stream.generate(() ->Student.getRandomStudent(java,py,dbms))
        .limit(5)
        .forEach(System.out::println);
        
        System.out.println("*".repeat(50));
        //SummaryStatistics 

        
        System.out.println("*".repeat(50));
        Stream.generate(() ->Student.getRandomStudent(java,py,dbms))
        .limit(5)
        .filter(s -> s.getGender().equals("Male"))
        .forEach(System.out::println);


    }
}



public class Course{
    private String courseCode;
    private String title;
    private int lectureCount;
    public Course(String courseCode,String title,int lectureCount){
        this.courseCode=courseCode;
        this.title=title;
        this.lectureCount=lectureCount;
    }
    public Course(String courseCode,String title){
        this( courseCode, title,100);
    }
    public String toString(){
        return (courseCode+" "+title);
    }
    public String getCode(){
        return courseCode;
    }
    public int getLectureCount(){
        return lectureCount;
    }

}


public class courseEngagement{
    private Course course;
    private LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;
    public courseEngagement(Course course,LocalDate enrollmentDate,String engagementType,int lastLecture,LocalDate lastActivityDate){
        this.course=course;
        this.enrollmentDate=enrollmentDate;
        this.engagementType=engagementType;
        this.lastLecture=lastLecture;
        this.lastActivityDate=lastActivityDate;
    }
public String getCourseCode(){
    return course.getCode();
}
public int getEnrollmentYear(){
    return enrollmentDate.getYear();
}
public int getLastActivityYear(){
    return lastActivityDate.getYear();
}
public Month getLastActivityMonth(){
    return lastActivityDate.getMonth();
}
public int getMonthsSinceActive(){
    Period gap=Period.between(LocalDate.now(),lastActivityDate);
    return gap.getMonths();
}
public double getPercentageComplete(){
    return (lastLecture*100/course.getLectureCount());

}


}


public class Student{
    public long i=0;
    private long studentId;
    private String countryCode;
    private int yearEnrolled;
    private int ageEnrolled;
    private String gender;
    private boolean programmingExperience;
    public Random random;
    public Map<String,courseEngagement> engagementMap;
    
    public Student(String countryCode,int yearEnrolled,int ageEnrolled,String gender,boolean programmingExperience){
        this.studentId=i++;
        this.countryCode=countryCode;
        this.yearEnrolled=yearEnrolled;
        this.ageEnrolled=ageEnrolled;
        this.gender=gender;
        this.programmingExperience=programmingExperience;
        engagementMap=new HashMap<>();
        random = new Random();
        
    }
    public void addCourse(Course course){
        addCourse(course,LocalDate.now());

    }
    public int getAge(){
        return ageEnrolled+LocalDate.now().getYear()-yearEnrolled;
    }
    public void addCourse(Course course ,LocalDate enrollmentDate){
        engagementMap.put(course.getCode(),new courseEngagement(course,enrollmentDate,"enrollement",0,enrollmentDate));
    }
    public static Student getRandomStudent(Course... courses) {
    Random random = new Random();
    String[] countries = {"IN", "AUS", "PAK", "UK", "USA"};
    String[] genders = {"Male", "Female", "Other"};

    Student newStudent = new Student(
        countries[random.nextInt(countries.length)],
        2015 + random.nextInt(35),
        20 + random.nextInt(35),
        genders[random.nextInt(genders.length)],
        random.nextBoolean()
    );

    int count = 1 + random.nextInt(Math.min(3, courses.length));
    List<Course> courseList = Arrays.asList(courses);
    Collections.shuffle(courseList);
    for (int i = 0; i < count; i++) {
        newStudent.addCourse(courseList.get(i));
    }

    return newStudent;
}
@Override
public String toString() {
    return String.format(
        "Student [ID: %d, Country: %s, Enrolled Year: %d, Age Enrolled: %d, Gender: %s, Programming Experience: %s]\n%s",
        studentId,
        countryCode,
        yearEnrolled,
        ageEnrolled,
        gender,
        programmingExperience ? "Yes" : "No","-".repeat(50)
    );
}
public String getGender(){
    return gender;
}



}
