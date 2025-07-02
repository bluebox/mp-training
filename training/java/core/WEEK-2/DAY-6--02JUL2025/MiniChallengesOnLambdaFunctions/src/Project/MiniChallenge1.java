package Project;
import java.util.function.Consumer;

public class MiniChallenge1 {
	public void printAllWords(String sentence) {
		String [] parts=sentence.split(" ");
		Consumer<String> printPart=w -> System.out.println(w);
		for(String str:parts) {
			printPart.accept(str);
		}
	}
}
