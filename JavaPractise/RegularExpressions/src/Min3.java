import java.util.Scanner;

public class Min3 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the sentence for pattern checking:");
		String sentence=sc.nextLine();
		String pattern="[A-Z][a-zA-Z]+[a-zA-Z\\s]*[.?!]";
		
		if(sentence.matches(pattern)) {
			System.out.println("Accepted");
		}
		else {
			System.out.println("Not Accepted");
		}
	}

}
