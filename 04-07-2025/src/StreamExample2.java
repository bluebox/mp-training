import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;
public class StreamExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<List<String>> names=Arrays.asList(
				Arrays.asList("Tarun","sai","santosh"),
				Arrays.asList("Prabhas","Gopi","Hari"),
				Arrays.asList("sai","Ravi","Tarun"));
		
		HashSet<String> h=new HashSet<>();
		
		Stream <String> result=names.stream()
			.flatMap(List::stream)
//			.filter(n->n.startsWith("s"))
			.map(String::toUpperCase)
			.peek(s->h.add(s));
//			.distinct();
				
			result.forEach(n->System.out.println(n));
			System.out.println(h);
				
				

	}

}
