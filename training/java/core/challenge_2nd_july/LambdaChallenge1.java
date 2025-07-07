package challenge_2nd_july;

interface StringConsumer{
	void accept(String s);
}
public class LambdaChallenge1 {

	public static void main(String[] args) {
		StringConsumer c1=(s)->{
			String[] parts=s.split(" ");
			for(String part:parts)
			{
				System.out.println(part);
			}
		};
		c1.accept("Hello world java");

	}

}