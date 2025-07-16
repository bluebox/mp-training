package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;

public class SumOfNumbers {

	public static void main(String[] args) {
		List<Integer> myList=Arrays.asList(1,2,3,4,5,6,7,8,9);
		Integer sum=myList.stream().reduce(0,Integer::sum);
		System.out.println("Sum of the elements in the given list is:"+sum);
	}

}
