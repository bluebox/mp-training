import java.util.Scanner;

public class MinToYear {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		int min=sc.nextInt();
		System.out.print(min+" min = "+(min/525600)+" y and "+((min/1440)%365)+" d");
	}
}
