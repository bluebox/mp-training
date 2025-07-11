package dev.tulasidhar.july8.studentStream1;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");
        Course fsh = new Course("FSH", "Fishing tutorials");
        
        Stream<Student> stream = Stream.generate(() -> Student.getRandomStudent(jmc, pymc,fsh));
        
        //How many student have 1, 2 ,3 courses
        Map<Integer,Long> map =stream
        	.limit(100)
        	.collect(Collectors.groupingBy(s->s.getEngagementMap().size() , Collectors.counting()));
        
        for(var pair : map.entrySet()) {
        	System.out.println(pair);
        }
        
        System.out.println("_".repeat(10));
        
        //how many student are engaged in each course
         Map<String,Long> map1 = Stream.generate(()->Student.getRandomStudent(jmc,pymc,fsh))
        					.limit(20)
        					.flatMap((s)->s.getEngagementMap().keySet().stream())
        					.collect(Collectors.groupingBy(s->s,Collectors.counting()));
         
         for(var pair : map1.entrySet()) {
         	System.out.println(pair);
         }
         System.out.println("_".repeat(10));
         
        //each courses average course completion
         Map<Object,Double> map2 = Stream.generate(()->Student.getRandomStudent(jmc,pymc,fsh))
					.limit(20)
					.flatMap((s)->s.getEngagementMap().values().stream())
					.collect(Collectors.groupingBy(s->s.getCourseCode(),Collectors.averagingDouble((e)-> e.getPercentComplete())));
         
         for(var pair : map2.entrySet()) {
         	System.out.println(pair);
         }
         System.out.println("_".repeat(10));
        //get activity by year
         Map<Object,Long> map3 = Stream.generate(()->Student.getRandomStudent(jmc,pymc,fsh))
					.limit(20)
					.flatMap((s)->s.getEngagementMap().values().stream())
					.collect(Collectors.groupingBy(s->s.getLastActivityYear(),Collectors.counting()));
      
      for(var pair : map3.entrySet()) {
      	System.out.println(pair);
      }
        
    }
}
