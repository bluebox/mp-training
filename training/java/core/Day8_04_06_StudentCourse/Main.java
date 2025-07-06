package Day8_04_06_StudentCourse;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Python Masterclass");
        Course jmc=new Course("JMC","Java Masterclass");
        Stream.generate(()->Student.getRandomStudent(jmc,pymc)).limit(10).forEach(System.out::println);
    }
}