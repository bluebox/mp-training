package terminaloperations;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTerminal {
public static void main(String[]args)
{
	List<Students>students=Stream.generate(Students::generateRandomStudent)
			
		.limit(100).collect(Collectors.toList());
	DoubleSummaryStatistics ageStats=students.stream().mapToDouble(Students::getAge).summaryStatistics();
	System.out.println("Avg Age: "+ageStats.getAverage());
	System.out.println("Min Age: "+ageStats.getMin());
	System.out.println("Max Age: "+ageStats.getMax());
	System.out.println("\nCountry codes:");
    students.stream().map(Students::getCode).distinct().forEach(System.out::println);
    boolean hasLongActive = students.stream().anyMatch(s -> s.isActive() && s.getYear() > 7);
    System.out.println("\nAny active > 7 years: " + hasLongActive);
    System.out.println("\nAny 5 students:");
    students.stream().limit(5).forEach(System.out::println);
    long maleCount = students.stream().filter(s -> s.getGender().equals("Male")).count();
    long femaleCount = students.stream().filter(s -> s.getGender().equals("Female")).count();
    System.out.println("\nMale: " + maleCount + ", Female: " + femaleCount);
    long below30 = students.stream().filter(s -> s.getAge() < 30).count();
    long between30And60 = students.stream().filter(s -> s.getAge() >= 30 && s.getAge() <= 60).count();
    long above60 = students.stream().filter(s -> s.getAge() > 60).count();
    System.out.println("\nAge < 30: " + below30);
    System.out.println("Age 30-60: " + between30And60);
    System.out.println("Age > 60: " + above60);
	
		
	
}
}
