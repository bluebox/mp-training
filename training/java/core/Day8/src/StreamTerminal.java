import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTerminal {
	public static void main(String[] args) {
		List<Student> students=
				Stream.generate(Student::generate)
				.limit(5000)
				.collect(Collectors.toList());
		
//		students.forEach(System.out::println);
		
		
		//Random r=new Random();
		Long males=students.stream()
				.filter(s->s.getGender().equals("Male"))
				.count();
		System.out.println("total males : "+males);
		System.out.println("total females : "+(5000-males));
		
		long lessThan30=students.stream().filter(s->s.getAge()<30).count();
		System.out.println("students aged less than 30 : "+lessThan30);
		
		long between30and60=students.stream().filter(s->s.getAge()>30 &&s.getAge()<60).count();
		System.out.println("students aged between 30 and 60 : "+between30and60);
		
		long greaterthan60=students.stream().filter(s->s.getAge()>60).count();
		System.out.println("students aged greater than 60 : "+greaterthan60);
		
		List<String> countries=students.stream().map(s->s.getCountryCode()).distinct().collect(Collectors.toList());
		System.out.println("Countries : "+countries);
		
		DoubleSummaryStatistics stats=students.stream().mapToDouble(s->s.getAge()).summaryStatistics();
		System.out.println("stats on age : \naverage age : "+Math.ceil(stats.getAverage()));
		
		List<Student> expStu=students.stream().filter(s->2025-s.getYear()>7 && s.getIsActive())
				.sorted((s1,s2)->s1.getCountryCode().compareTo(s2.getCountryCode())).collect(Collectors.toList());
		System.out.println("printing 5 memebers who have more than 7 years on platform and are active  : ");
		for(int i=0;i<5;i++) {
			System.out.println(expStu.get(i));
		}
		
				
	}
}
