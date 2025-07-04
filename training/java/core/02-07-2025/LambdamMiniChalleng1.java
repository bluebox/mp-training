interface StringConsumer{
	void accept(String s);
}
public class LambdamMiniChalleng1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringConsumer c1=(s)->{
			String[] parts=s.split(" ");
			for(String part:parts)
			{
				System.out.println(part);
			}
		};
		c1.accept("Hello My Name is Tarun");

	}

}
