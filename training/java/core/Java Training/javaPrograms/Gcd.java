package javaPrograms;
import java.util.*;
public class Gcd {
	
	public static int getGCD(int x,int y) {
		if(x<10 || y<10) {
			return -1;
		}
		int z=(x<y)?x:y;
		int sol=-1;
		for(int i=z;i>0;i--) {
			if(x%i==0 && y%i==0) {
				sol=i;
				break;
			}
		}
		return sol;
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the first and second numbers : ");
		int x=sc.nextInt();
		int y=sc.nextInt();
		int ans=getGCD(x,y);
		System.out.println("GCD of "+x+" and "+y+" is : "+ans);
		sc.close();
	}
}
