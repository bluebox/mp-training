import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExample {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Saketh", "naga");

		Optional<String> found = names.stream()
				.filter(i -> i.startsWith("S"))
				.findFirst();
		String returnOne = found.orElseGet(() -> method("saketh"));//if there is a value in found the method is not called
		String returnTwo = found.orElse(method("naga"));//irrespective of value in found method is called

	}

	public static String method(String message ) {
		System.out.println("in the method"+message);
		return "anil";
	}
}