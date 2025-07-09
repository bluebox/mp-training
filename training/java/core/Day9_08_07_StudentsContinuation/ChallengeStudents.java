package Day9_08_07_StudentsContinuation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Day8_04_06_StudentCourse.*;
public class ChallengeStudents {
	public static void main(String[] args) {
		 Course pymc=new Course("PYMC","Python Masterclass");
	        Course jmc=new Course("JMC","Java Masterclass");
	       

	        List<Student> students = new ArrayList<>();

	        for (int i = 0; i < 40; i++) {
	            double rand = Math.random();
	            Student student;
	            if (rand < 0.33) {
	                student = Student.getRandomStudent(pymc);  
	            } else if (rand < 0.66) {
	                student = Student.getRandomStudent(jmc);   
	            } else {
	                student = Student.getRandomStudent(jmc, pymc); 
	            }

	            students.add(student);
	        }
	        
	        Map<String, Long> enrollCount = students.stream()
	        	    .flatMap(s -> s.getEngagementMap().keySet().stream())
	        	    .collect(Collectors.groupingBy(
	        	        Function.identity(), // courseCode (String)
	        	        Collectors.counting()
	        	    ));
	        System.out.println(enrollCount);
	        Map<String,Long> counter=new HashMap<>();
	       //students.stream().forEach(s->System.out.println(s.getEngagementMap().keySet()));
//	        System.out.println(jmcCount);
//	        students.stream()
//	        .forEach(s->counter
//	        		.put(s.getEngagementMap().keySet().forEach(c->counter.put(c,counter.getOrDefault(c, (long) 0) + 1));
	       students.stream().forEach(s->s.getEngagementMap().keySet().forEach(c->counter.put(c.toString(), counter.getOrDefault(c.toString(), (long) 0) + 1)));
	        
	        System.out.println(counter);
	        
	}
}
