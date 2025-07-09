package challenges_4th_july;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.*;

public class TerminalOpsChallenge {
    public static void main(String[] args) {

        List<Student> students = Stream.generate(Student::getRandomStudent)
                                       .limit(100)
                                       .collect(Collectors.toList());
        
        //Gender count
        
        long maleCount=students.stream().filter(s->s.getGender()=="male").count();
        
        System.out.println("maleCount= "+maleCount);
        
        long femaleCount=students.stream().filter(s->s.getGender()=="female").count();
        
        System.out.println("femaleCount= "+femaleCount);
    

        
        //ages 
        long lessThan30 = students.stream().filter(s -> s.getAge() < 30).count();
        long between30And60 = students.stream().filter(s -> s.getAge() >= 30 && s.getAge() <= 60).count();
        long over60 = students.stream().filter(s -> s.getAge() > 60).count();
        System.out.println("below 30: " + lessThan30);
        System.out.println("between 30-60: " + between30And60);
        System.out.println("above 60: " + over60);
        
        
        //stats on age
        IntSummaryStatistics stats = students.stream()
                                             .mapToInt(Student::getAge)
                                             .summaryStatistics();
        System.out.println(stats);

        //country codes
        List<String> countries = students.stream()
                                         .map(Student::getCountryCode)
                                         .distinct()
                                         .collect(Collectors.toList());
        System.out.println(countries);

        // student active and enrolled > 7 years?
        boolean match = students.stream()
                                .anyMatch(s -> s.isActive() && s.getYearsEnrolled() > 7);
        System.out.println("active >7 years: " + match);

        //print info
        students.stream()
                .limit(5)
                .forEach(s -> System.out.println(
                    s.getName() + "-" + s.getAge() + "-" + s.getGender() + " " +
                    s.getCountryCode() + " " + s.isActive() + " " + s.getYearsEnrolled()));

  

   
       
    }
}