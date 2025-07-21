package Project;
import java.util.function.Consumer;

public class MiniChallenge1 {
	public void printAllWords(String sentence) {
		String [] parts=sentence.split(" ");
		Consumer<String[]> printPart=(strs) -> {
			for(String str:strs) {
				System.out.println(str);
			}
		};
		printPart.accept(parts);
	}
}
