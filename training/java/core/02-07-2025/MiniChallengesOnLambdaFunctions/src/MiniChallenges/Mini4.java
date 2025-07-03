package MiniChallenges;

import java.util.function.Supplier;

public class Mini4 {
	public String getString() {
		Supplier<String> sup=() -> "Ilovejava";
		return sup.get();
	}
}
