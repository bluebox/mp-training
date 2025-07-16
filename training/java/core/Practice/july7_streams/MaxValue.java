package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MaxValue {

	public static void main(String[] args) {
		List<Integer> myList=Arrays.asList(0,1,2,3,4,5,6);
		Optional<Integer> maxElement=myList.stream().max(Integer::compare);
		maxElement.ifPresent(element->System.out.println(element));
	}

}
