import java.util.Scanner;

public class WhileUsingFor {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		for(int i=sc.nextInt();i<10;i=sc.nextInt()) {
			System.out.println(i);
		}
	}
}
