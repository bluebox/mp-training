import java.util.*;
import java.util.stream.Collectors;

public class StreamsPractice {
	public static void main(String[] args)
	{
		List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
		System.out.println(strings);
		
		strings.stream().filter(s->s.isEmpty()).count();
		
		strings.stream().filter(s->s.length()==3).count();
		
		strings.stream().filter(s->!s.isEmpty()).collect(Collectors.toList());
		
		
		strings.stream().filter(s->!s.isEmpty()).collect(Collectors.joining(", "));
		
		
		
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		
		List<Integer> squaresList=numbers.stream().map(i->i*i).distinct().collect(Collectors.toList());
		
		
		
		List<Integer> integers = Arrays.asList(1,2,13,4,15,6,17,8,19);
		
		IntSummaryStatistics stats=integers.stream().mapToInt((x)->x).summaryStatistics();
		
		System.out.println("Highest number in List : " + stats.getMax());
	      System.out.println("Lowest number in List : " + stats.getMin());
	      System.out.println("Sum of all numbers : " + stats.getSum());
	      System.out.println("Average of all numbers : " + stats.getAverage());
	      System.out.println("Random Numbers: ");
			
	      Random random = new Random();
	      random.ints(1,100).limit(10).sorted().forEach(System.out::println);
			
	      
	      long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
	      System.out.println("Empty Strings: " + count);
		
		
		
	
	}
}
