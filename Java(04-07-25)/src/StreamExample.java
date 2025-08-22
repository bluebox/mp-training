import java.util.Arrays;
public class StreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] strings={"sreeja","chandana","hemanth","shiva","vignesh"};
		Arrays.stream(strings)
		.sorted()
		.map(s->s.toUpperCase())
		.forEach(s->System.out.println(s));
		String[] strings2= {"one","two","three"};
		Arrays.stream(strings2)
		.sorted()
		.forEach(p->System.out.println(p));
	}

}
