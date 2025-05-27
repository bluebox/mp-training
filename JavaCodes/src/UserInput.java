import java.util.*;
public class UserInput {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int integer=sc.nextInt();
		double doubles=sc.nextDouble();
		long longs=sc.nextLong();
		boolean bool=sc.nextBoolean();
		String s=sc.nextLine();
		System.out.print(integer+" "+doubles+" "+longs+" "+bool+" "+s);
	}

}
