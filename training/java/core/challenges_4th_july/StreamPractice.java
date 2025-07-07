package challenges_4th_july;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamPractice {
	
	public static void main(String args[]) {
		List<String>names= Arrays.asList("adheesh","tharun","venkat");
		
		names.stream().filter(x->x.length()<=6).forEach(System.out::println);
		
		List<Integer> numbers=Arrays.asList(5,2,9,1,6);
		
		// squaring and sorting
		System.out.println(numbers.stream().map(x->x*x).sorted().toList());
		
		//summing values
		List<Integer>nums=Arrays.asList(1,2,3,4,5);
		
	int val=	nums.stream().reduce((x,y)->x+y).get();
	System.out.println(val);
	
	String sentence="Hello world";
	//count occurence of a character l in the sentence
	
	
	System.out.println(sentence.chars().filter(x->x=='l').count());
	
	}
}
