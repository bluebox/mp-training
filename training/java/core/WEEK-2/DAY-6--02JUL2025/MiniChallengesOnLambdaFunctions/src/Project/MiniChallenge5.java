package Project;
import java.util.function.Supplier;

public class MiniChallenge5 {
	public String getString() {
		Supplier<String> sup=() -> "I love java";
		return sup.get();
	}
}
