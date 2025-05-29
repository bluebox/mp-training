import java.util.Scanner;

public class Bob {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double x=sc.nextDouble();
		double y=sc.nextDouble();
		double a=sc.nextDouble();
		int b=sc.nextInt();
		if((x<=0)&&(y<=0)&&(a<=0)){
			System.out.println(-1);
		}
		else if(b<0) {
			System.out.println(-1);
		}
		else {
			System.out.println((int)(Math.ceil((x*y)/a)-b));
		}
		sc.close();
	}
}
    