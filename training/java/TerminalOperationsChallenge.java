import java.Course;
import java.Student;
import java.util.Random;
import java.util.stream.Stream;


public class TerminalOperationsChallenge{
    public static void main(String[] args){
        Random random = new Random();
        Course java=new Course("J","JAVA");
        Course py=new Course("PY","PYTHON",50);
        Course dbms=new Course("DBMS","DATA BASE",65);
        Stream.generate(() -> Student.getRandomStudent(java,py,dbms))
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


