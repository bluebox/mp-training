package Challenges;
import java.util.Scanner;
public class ValidParanthesis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter the paranthesis pattern : ");
		String p=scanner.nextLine();
		System.out.println(p);
		String a1="<h1>";
		String a2="</h1>";
		p.replaceAll("<",a1);
		p.replaceAll(">",a2);
		System.out.println(p);
	}

}
