import java.util.function.Consumer;

public class MiniChallenge1 {
	static Consumer<String> printTheParts = sentence -> {
		String parts[] = sentence.split(" ");
		for (String part : parts) {
			System.out.println(part);
		}
	};
}