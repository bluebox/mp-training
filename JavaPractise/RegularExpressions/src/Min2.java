import java.util.Scanner;

public class Min2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the sentence for pattern checking:");
		String sentence=sc.nextLine();
		String pattern="[A-Z][a-zA-Z\\s]*\\.";
		
		if(sentence.matches(pattern)) {
			System.out.println("Accepted");
		}
		else {
			System.out.println("Not Accepted");
		}
		System.out.println("Enter the Phone number to check :");
		String phoneNo=sc.nextLine();
		String pat="[8-9][0-9]{9}";
		if(phoneNo.matches(pat)) {
			System.out.println("Matched");
		}
		else {
			System.out.println("Not Matched");
		}
	}

}
