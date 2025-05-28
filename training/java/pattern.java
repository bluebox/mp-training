import java.util.*;
public class pattern {
	
	public static void printSquareStar(int n) {
		if(n<5) {
			System.out.print("Invalid Value");
					 return;
		}
		int midSpace=n-4,space=0;
		midSpace=midSpace*2;
		for(int i=0;i<n/2;i++) {
			if(i==0) {
				System.out.print("* ".repeat(n));
			}
			else {
				System.out.print("* "+" ".repeat(space)+"* "+" ".repeat(midSpace)+"* "+" ".repeat(space)+"*");
				space+=2;
				midSpace-=4;
			}	
			System.out.println();
		}
		if(n%2!=0) {
			System.out.print("* "+" ".repeat((n-3))+"* "+" ".repeat((n-3))+"*");
			System.out.println();
		}
		space-=2;
		midSpace+=4;
		for(int i=n/2+1;i<n;i++) {
			if(i==n-1) {
				System.out.print("* ".repeat(n));
			}
			else {
				System.out.print("* "+" ".repeat(space)+"* "+" ".repeat(midSpace)+"* "+" ".repeat(space)+"*");
				space-=2;
				midSpace+=4;
			}System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		printSquareStar(num);
	}

}
