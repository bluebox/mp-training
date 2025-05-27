import java.util.*;
public class MinMaxAndException {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int small=Integer.MAX_VALUE,large=Integer.MIN_VALUE;
		while(true) {
			try {
				int Num=sc.nextInt();
				small=Math.min(small, Num);
				large=Math.max(large, Num);
				System.out.println(small+" "+large);
			}
			catch(Exception e) {
				System.out.print("breaking from loop");
				break;
			}
		}
		
	}

}
