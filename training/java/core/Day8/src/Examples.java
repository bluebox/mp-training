import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Examples {
	public static void main(String[] args) {
		List<Integer> nums=Arrays.asList(1,2,3,4,15,2,1,2);
		OptionalDouble avg=nums.stream().mapToDouble(Integer::doubleValue).average();
		System.out.println(avg);
		
		List<String> strs=Arrays.asList("babu","ravi","zandu","anu","baskar","balu","bharath","rahul");
		List<String> ans=strs.stream().map(s->s.toUpperCase()).sorted().collect(Collectors.toList());
		ans.forEach(System.out::println);
		
		int evesum=nums.stream().filter(n->n%2==0).mapToInt(Integer::intValue).sum();
		
		List<Integer> uniq=nums.stream().distinct().collect(Collectors.toList());
		//uniq.forEach(System.out::println);
		
		long count=strs.stream().filter(s->s.startsWith("r")).count();
		System.out.println(count);
		
		List<String> ans1=strs.stream().sorted().collect(Collectors.toList());
		System.out.println(ans1);
		
		List<String> ans2=strs.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(ans2);
		
		System.out.println(nums.stream().mapToInt(Integer::intValue).min());
		
		System.out.println(nums.stream().distinct().sorted((s1,s2)->s1.compareTo(s2)).skip(1).findFirst());


	}
}
