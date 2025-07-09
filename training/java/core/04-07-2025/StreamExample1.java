import java.util.*;
import java.util.stream.Stream;
public class StreamExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list=Arrays.asList(1,2,3,4,5,6);
//		Stream<Integer> data=list.stream();
//		data.forEach(n->System.out.println(n));
		
		list.stream()
		.map(n->n*2)
		.sorted()
		.forEach(n->System.out.println(n));

	}

}
