package Day8_04_06_StudentCourse;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Python Masterclass");
        Course jmc=new Course("JMC","Java Masterclass");
        int mcount=0;
        int fcount=0;
//        Consumer<Student> con=s->{
//        		Collectors.groupingBy(s.getGender()),
//        		
//        };
        Map<String,Long> studentsdata=Stream.generate(()->Student.getRandomStudent(jmc,pymc))
        		.limit(10)
        		.collect(Collectors.groupingBy(
        					(s)->{
        						if(s.getGender() == "F")
        							return "Female";
        						else if(s.getGender()=="M")
        							return "Male";
        						else
        							return "Unkown";
        					},
        					Collectors.counting()
        		));
        
        //studentsdata.forEach(i->con.accept(i));
        for(var ite : studentsdata.entrySet()) {
        		System.out.println(ite.getKey()+" : " +ite.getValue() );
        }
    }
}