package Day8_04_06_StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class Challenge3 {
	    public static void main(String[] args) {
	        Course java = new Course("JMC", "Java Masterclass");
	        Course python = new Course("PYMC", "Python Masterclass");

	        List<Student> students = new ArrayList<>();
	        for (int i = 0; i < 1000; i++) {
	            students.add(Student.getRandomStudent(java, python));
	        }

	        int ausCount = 0;
	        int youngCount = 0;
	        for (Student s : students) {
	            if (s.getCountryCode().equals("IN")) ausCount++;
	            if (s.getAgeEnrolled() < 30) youngCount++;
	        }

	        System.out.println("Indian Students: " + ausCount);
	        System.out.println("Under 30 Students: " + youngCount);
	    }
	}

