package dev.tulasidhar.july4.studentstream;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

        
        Stream<Student> stream = Stream.generate(() -> Student.getRandomStudent(jmc, pymc));
        
        //group by genders
        Map<String,Long> genderCount = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(20)
                .collect(Collectors.groupingBy( 
                		s->s.getGender(),
                		Collectors.counting()) 
                );
        
        //group by age range
        Map<String,Long> AgeRanges = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(20)
                .collect(Collectors.groupingBy( 
                		(s)->{
                			if(s.getAge()<30) return "Under 30";
                			else if(s.getAge()<60) return "Between 30 and 60";
                			else return "above 60";
                		},
                		Collectors.counting()) 
                		);
        
        
        //age summary stats
        var statsStream = Stream.generate(() -> Student.getRandomStudent(jmc, pymc)).limit(20);
        
        System.out.println(statsStream.mapToInt(i->i.getAge()).summaryStatistics());
        
        
        //distinct countries
        var contriesStream = Stream.generate(() -> Student.getRandomStudent(jmc, pymc)).limit(20);
        
        Set<String> countries = contriesStream
        								.map(s->s.getCountryCode())
        								.collect(Collectors.toSet());
        
        System.out.println(countries);
        
        for(var ite : AgeRanges.entrySet()) {
        	System.out.println(ite.getKey() + " : " + ite.getValue());
        }
        
    }
}
