package june2_challenges;
import java.util.function.Consumer;
import java.util.Scanner;
public class MainLambda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<String> printTheParts=(sentence)->{
			String[] parts=sentence.split(" ");
			for(String part:parts) {
				System.out.println(part);
			}
		};
		System.out.println("Enter the Sentence");
		Scanner sc=new Scanner(System.in);
		String sentence=sc.nextLine();
		System.out.println("Lamba experssion calling!");
		printTheParts.accept(sentence);
	}

}
