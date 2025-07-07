import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BingoBall {
	public static void main(String[] args) {
		List<String> B=new ArrayList<>();
		for(int i=1;i<=15;i++) {
			B.add("B"+i);
		}
		List<String> I=new ArrayList<>();
		for(int i=16;i<=30;i++) {
			I.add("I"+i);
		}
		List<String> N=new ArrayList<>();
		for(int i=31;i<=45;i++) {
			I.add("N"+i);
		}
		List<String> G=new ArrayList<>();
		for(int i=46;i<=60;i++) {
			I.add("G"+i);
		}
		List<String> O=new ArrayList<>();
		for(int i=61;i<=75;i++) {
			I.add("O"+i);
		}
		Stream.concat(B.stream(),Stream.concat(I.stream(),Stream.concat(N.stream(), Stream.concat(G.stream(), O.stream())))).forEach(System.out::println);
		
	}
}
