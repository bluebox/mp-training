package TerminaloperationCh;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Student {
    private Map<Course, Integer> attendance = new HashMap<>();
    
    public Student(Course... courses) {
        Random rand = new Random();
        for (Course c : courses) {
            attendance.put(c, rand.nextInt(c.lcount + 1)); 
        }
    }

    public double getPercentComplete(Course course) {
        return attendance.containsKey(course)
            ? (attendance.get(course) / (double) course.lcount) * 100
            : 0.0;
    }

    public int getMinLectureAttended() {
        return attendance.values().stream().min(Integer::compare).orElse(0);
    }

    public void addCourse(Course course) {
        attendance.put(course, 0);
    }

    @Override
    public String toString() {
        return "Student{" + "attendance=" + attendance + '}';
    }
}
