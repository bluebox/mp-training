package July4.TerminalOperations;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Student {
	
	Map<Course, Integer> mp = new HashMap <>();
	
	public Student(Course... courses) {
        Random random = new Random();
        for (Course c : courses) {
            mp.put(c, random.nextInt(c.lectureCount + 1)); 
        }
    }

    public double getPercentComplete(Course course) {
        return mp.containsKey(course)
            ? (mp.get(course) / (double) course.lectureCount) * 100
            : 0.0;
    }

    public int getMinLectureAttended() {
        return mp.values().stream().min(Integer::compare).orElse(0);
    }

    public void addCourse(Course course) {
        mp.put(course, 0);
    }
    
     
    @Override
    public String toString() {
        return "Student{" + "attendance = " + mp + '}';
    }

}
