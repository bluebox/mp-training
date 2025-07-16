package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindingFirst {

	public static void main(String[] args) {
		List<String> fruits=Arrays.asList("mango","banana","papaya","orange");
		Optional<String> firstFruit=fruits.stream().findFirst();
		firstFruit.ifPresent(fruit->System.out.println(fruit));
	}

}
