package MiniChallenges;

import java.util.function.Consumer;

public class Mini1 {
	public void printAllWords(String sentence) {
		String [] parts=sentence.split(" ");
		Consumer<String[]> printPart=(strs) -> {
			for(String str:strs) {
				System.out.println(str);
			}
		};
//		for(String str:strs) {
//			System.out.println(str);
//		}
		printPart.accept(parts);
	}
}
